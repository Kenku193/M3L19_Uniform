package com.example.m3l19_uniform;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class Solution {
    public static void main(String[] args) throws IOException {

        for (int i = 0; i < 2; i++) {
            new Thread(() ->{
                try {
                    URLConnection urlConnection =
                            new URL("http://localhost:8080/M3L19_Uniform_war_exploded/hello-servlet")
                                    .openConnection();
                    urlConnection.getInputStream();
                }
                catch (IOException e){
                    throw new RuntimeException(e);
                }
            }).start();

        }

    }

}
