package org.example;


import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginService {
    private static final String LOGIN_URL = "https://www.linkedin.com/uas/login-submit";

    public static List<Cookie> getLogin(){
        try {
            // Create a cookie store to hold the session cookies
            CookieStore cookieStore = new BasicCookieStore();

            // Create an HttpClient with redirect strategy and cookie store
            HttpClient client = HttpClients.custom()
                    .setRedirectStrategy(new LaxRedirectStrategy())
                    .setDefaultCookieStore(cookieStore)
                    .build();

            // Create a POST request to login
            HttpPost loginPost = new HttpPost(LOGIN_URL);
            List<BasicNameValuePair> formParams = new ArrayList<>();
            formParams.add(new BasicNameValuePair("session_key", "baldovaliev_b@outlook.com"));
            formParams.add(new BasicNameValuePair("session_password", ""));
            loginPost.setEntity(new UrlEncodedFormEntity(formParams));

            // Execute the login request
            HttpClientContext context = HttpClientContext.create();
            HttpResponse response = client.execute(loginPost, context);

            // Extract cookies
           return cookieStore.getCookies();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return List.of();
    }


}
