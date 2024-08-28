package org.example;

import org.apache.http.cookie.Cookie;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

import static org.example.LoginService.getLogin;

public class Main {
    public static void main(String[] args) {
        try {

            Connection connection = Jsoup.connect("https://www.linkedin.com/jobs/search/?currentJobId=3943726449&distance=25&f_E=4&f_WT=2&geoId=92000000&keywords=Full%20Stack%20Engineer&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD")
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");

            List<Cookie> cookies = getLogin();
            for (Cookie cookie : cookies) {
                connection.cookie(cookie.getName(), cookie.getValue());
            }
            Document doc = connection.get();
            Elements jobElements = doc.select(".");
            for (Element link : jobElements) {
                System.out.println(link.attr("href"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}