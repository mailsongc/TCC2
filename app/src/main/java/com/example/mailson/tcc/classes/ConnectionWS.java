package com.example.mailson.tcc.classes;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ConnectionWS {


    public  static boolean Cadastrar(){

        return true;
    }


        public static  boolean Login(String placa, String senha) throws ProtocolException {

        try
        {
            String urlPost = "http://www.henrique-silveira.com/WebServiceAlertCar/ws/pwd.php?pwd="+senha+"&placa="+ placa;
            URL url = new URL(urlPost);

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();

            String resStr = response.body().string().toString();
            JSONObject json = new JSONObject(resStr);

            return json.getBoolean("valido");
        }
         catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

            return  false;
        }
}
