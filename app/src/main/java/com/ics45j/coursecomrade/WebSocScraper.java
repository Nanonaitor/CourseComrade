package com.ics45j.coursecomrade;

import android.os.AsyncTask;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class WebSocScraper{
    private final String homePage = "https://www.reg.uci.edu/perl/WebSoc/";

    public WebSocScraper(){

    }

    public void initDatabase(){
        ConnectDocument home = new ConnectDocument(homePage);
        Document homeDoc = home.getDocument();
    }



    private class ConnectDocument extends AsyncTask<Void, Void, Void> {
        private String url;
        private Document document;

        public ConnectDocument(String url){
            this.url = url;
        }

        public Document getDocument(){
            return document;
        }

        private void retreiveDoc() {
            try {
                document = Jsoup.connect(url).get();
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
                Document doc = Jsoup.connect("https://www.reg.uci.edu/perl/WebSoc/").get();
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


