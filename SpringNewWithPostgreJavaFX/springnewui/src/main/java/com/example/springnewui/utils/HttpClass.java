package com.example.springnewui.utils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

//Заменяет часть кода в StageInitializer
public class HttpClass {
    public static String getRequest(String urlString){
        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();

            StringBuilder sb = new StringBuilder();
            InputStream is = new BufferedInputStream(conn.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String inputLine = "";

            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }

            return sb.toString();

        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
