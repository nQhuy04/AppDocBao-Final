package com.example.appdoctintuc;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class XMLParser {
    public String getXMLFromUrl(String url){
        String xml = null;
        URL link = null;
        HttpURLConnection connection = null;

        try {
            //Tạo link từ String URL truyền vào
            link = new URL(url);
            //Tạo kết nối http
            connection = (HttpURLConnection) link.openConnection();
            //get dữ liêu từ kết nối trả về và lưu vào bộ đệm
            BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
            //Tạo string từ dữ liệu trong bộ đệm
            StringBuilder sb = new StringBuilder();
            String line;
            //đọc từng dòng trong bộ đệm cho đến khi hết dữ liệu
            while ((line = br.readLine()) != null) {
                sb.append(line).append('\n');

            }
            //Lưu trữ kết quả vào chuỗi XML để trả về
            xml = sb.toString();

        }catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }catch (IOException e){
            throw new RuntimeException(e);
        }

        /* }catch (Exception e){
            e.printStackTrace();
        } */
        Log.d("HTTP-GET", xml);
        connection.disconnect();
        return xml;
    }
}
