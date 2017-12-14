package com.example.shivani.jsonparsing;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HTTPHandler {
    private static  final String TAG=HTTPHandler.class.getSimpleName();
    public HTTPHandler(){}
    public String makeServiceCall(String reqUrl){
        String response=null;

        try {
            URL url=new URL(reqUrl);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = new BufferedInputStream(connection.getInputStream());
            response=convertStreamToString(inputStream);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;  
    }

    private String convertStreamToString(InputStream inputStream) {
        BufferedReader buff=new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb=new StringBuilder();
        String line;
        try {
            while((line=buff.readLine())!=null){
            sb.append(line).append("\n");}
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
