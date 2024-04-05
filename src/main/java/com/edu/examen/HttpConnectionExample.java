package com.edu.examen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class HttpConnectionExample {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final List<String> GET_URL = new ArrayList<>();
    private static int currentserver = 0;

    public static String connection(String path,String valuelist,String value) throws IOException {

        URL obj = new URL(GET_URL.get(currentserver) + path + "?list=" + valuelist + "&value=" + value);
        rotateRoundRobinServer();
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        StringBuffer response = new StringBuffer();
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
            return response.toString();
        } else {
            System.out.println("GET request not worked");
        }
        System.out.println("GET DONE");
        return response.toString();
    }

    private static void rotateRoundRobinServer() {
        currentserver = (currentserver + 1) % GET_URL.size();
    }
    public static void setURLS(String... args) {
        for (String url : args) {
            GET_URL.add("http://" + url + "/");
        }
    }

} 
