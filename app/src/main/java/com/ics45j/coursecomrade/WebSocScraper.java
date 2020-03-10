package com.ics45j.coursecomrade;

import android.os.AsyncTask;



import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class WebSocScraper{
    private final String homePage = "https://www.reg.uci.edu/perl/WebSoc/";
    private final String submitType = "Text";
    private final String yearTerm = "2020-14";
    private Map<String, String> depts;



    public WebSocScraper(){
        depts = new HashMap<String, String>();
    }



    public void initAllClasses() {

        Document homeDoc = runConnectDocument(homePage);

        // initialize Department list
        Elements deptElements =  homeDoc.select("form").select("select[name=Dept]").select("option[value]");
        initDepts(deptElements);

    }


    private void initDepts(Elements deptElements){
        for(Element dept: deptElements){
            String deptStr = urlEncode(dept.attr("value"));

            String deptDoc = runConnectDocument(homePage, deptStr);
            if(deptDoc != null && !deptStr.contains("ALL")){

                System.out.println(deptStr);
                System.out.println(deptDoc);
                System.out.println(homePage);
                depts.put(deptStr, deptDoc);
                break;
            }
        }
    }



    private String urlEncode(String s){
        return s.replace(" ","%20");
    }



    public Map<String, String> getDepts() {
        return depts;
    }



    private Document runConnectDocument(String url){
        ConnectDocument doc = new ConnectDocument(homePage);
        try {
            doc.execute().get();
        }
        catch(ExecutionException e){
            throw new RuntimeException(e.getCause());
        }
        catch(InterruptedException e){
            throw new RuntimeException(e.getCause());
        }
        return doc.getDocument();
    }


    private String runConnectDocument(String url, String dept){
        ConnectDocument doc = new ConnectDocument(homePage, yearTerm, dept);
        try {
            doc.execute().get();
        }
        catch(ExecutionException e){
            throw new RuntimeException(e.getCause());
        }
        catch(InterruptedException e){
            throw new RuntimeException(e.getCause());
        }
        return doc.getBody();
    }





    // Helper class
    private class ConnectDocument extends AsyncTask<Void, Void, Void> {
        private String url;
        private Document document;
        private String yearTerm = "";
        private String dept = "";
        private String body = "";
        private boolean useResponse = false;

        public ConnectDocument(String url){
            this.url = url;
        }

        public ConnectDocument(String url, String yearTerm, String dept){
            this.dept = dept;
            this.yearTerm = yearTerm;
            if (dept != ""){
                this.useResponse = true;
            }

        }

        public Document getDocument(){
            return document;
        }

        public String getBody() {
            return body;
        }

        private void retreiveDoc() {
            try {
                if(!useResponse) {
                    document = Jsoup.connect(url).get();
                }
                else {
                    Connection.Response response = Jsoup.connect("https://www.reg.uci.edu/perl/WebSoc/")
                            .data("YearTerm", "2020-14")
                            .data("Dept", "COMPSCI")
                            .execute();

                    body = response.body();
                }
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }




        @Override
        protected Void doInBackground(Void... voids) {
            retreiveDoc();
            return null;
        }

        public void test(){
            try {
                Document doc = Jsoup.connect("http://www.reg.uci.edu/perl/WebSoc/").get();
                //System.out.println(doc.body());
                //System.out.println(doc.select("form").select("select[name=YearTerm]").select("option[value]").attr("value"));
                Elements years = doc.select("form").select("select[name=YearTerm]").select("option[value]");

                Element potentialYearForm =  doc.select("form").select("select[name=YearTerm]").first();
                //FormElement yearForm = (FormElement) potentialYearForm;

                for(Element year: years){
                    System.out.println(year.attr("value"));
                }

                Connection.Response response = Jsoup.connect("https://www.reg.uci.edu/perl/WebSoc/")
                        .data("YearTerm", "2020-14")
                        .data("Dept", "COMPSCI")
                        .method(Connection.Method.POST)
                        .execute();

                System.out.println(response.body());
            }
            catch (IOException e){
                e.printStackTrace();
            }

        }


        public void test2(){
            try {
                Document doc = Jsoup.connect("https://www.reg.uci.edu/perl/WebSoc/").get();
            }
            catch (IOException e){
                e.printStackTrace();
            }

        }
    }


}


