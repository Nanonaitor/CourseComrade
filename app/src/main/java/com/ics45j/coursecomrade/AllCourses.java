package com.ics45j.coursecomrade;


import java.util.Map;

public class AllCourses {
    private Map<String, String> depts;

    public AllCourses(){
        WebSocScraper scraper = new WebSocScraper();
        scraper.initAllClasses();

        depts = scraper.getDepts();
    }

}
