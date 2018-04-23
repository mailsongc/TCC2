package com.example.mailson.tcc.classes;

import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;

import com.example.mailson.tcc.DTO.jsonVisionPost;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Dados  {

private static String urlApiVison = "https://vision.googleapis.com/v1/images:annotate?key=AIzaSyDnf8Abt-kO8-beUGhkwLKiMrLc6TuGEXg";

private static jsonVisionPost EnviarImagemApi(Bitmap imagem){

    jsonVisionPost obj = new jsonVisionPost();
    try {
        String json = "{\"requests\": [{\"image\":{\"content\":\""+ ConvertBitmapToString(imagem)+"\"},\"features\":[{\"type\":\"TEXT_DETECTION\"}]}]}";



        final RequestBody body = RequestBody
                .create(MediaType.parse("application/json"), json);
        final Request request = new Request.Builder()
                .url(urlApiVison)
                .post(body)
                .addHeader("Accept", "application/json")
                .build();
        final OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();
        String resStr = response.body().string().toString();

        Pattern pattern = Pattern.compile("[0-9]{3}.?[0-9]{3}.?[0-9]{3}-?[0-9]{2}");

        Matcher m = pattern.matcher(resStr);

        if(m.find()) {
           obj.setCpf(m.group());
        }

        obj.setSucesso(true);
        JSONObject jsonReturn = new JSONObject(resStr);


        return  obj;
    } catch (Exception e) {
        Log.e("Your tag", "Error", e);
        return  obj;
    }
}


    public  static  jsonVisionPost EnviarCNH(Bitmap imagem){

            return  EnviarImagemApi(imagem);

        //return false;
        /*
        try {
            URL url = new URL(urlApiVison);
            String json = "{\"requests\": [{\"image\":{\"content\":\""+ ConvertBitmapToString(imagem)+"\"},\"features\":[{\"type\":\"TEXT_DETECTION\"}]}]}";

           // Request request = Request.Buil


            String type = "application/json";
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", type);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
                // httpURLConnection.connect();


            Log.e("JSON", json);
            OutputStream outputStream  = httpURLConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            writer.write(json);
            writer.flush();
            writer.close();
            httpURLConnection.disconnect();


        } catch (MalformedURLException e) {
         Log.e("teste", e.getMessage());
        } catch (ProtocolException e) {
            Log.e("teste2", e.getMessage());
        } catch (IOException e) {
            Log.e("teste3", e.getMessage());
        }
*/

    }


    public  static  jsonVisionPost EnviarDocumento(Bitmap imagem){

        return EnviarImagemApi(imagem);

    }


    private static String ConvertBitmapToString(Bitmap imagem){

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        imagem.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byt = stream.toByteArray();
        String encoreImg = Base64.encodeToString(stream.toByteArray(), Base64.DEFAULT);

        return encoreImg;
    }
/*
    @Override
    protected Boolean doInBackground(Bitmap... bitmaps) {
        try {
            String json = "{\"requests\": [{\"image\":{\"content\":\""+ ConvertBitmapToString(imagem)+"\"},\"features\":[{\"type\":\"TEXT_DETECTION\"}]}]}";
            final RequestBody body = RequestBody
                    .create(MediaType.parse("application/json"), json);
            final Request request = new Request.Builder()
                    .url(urlApiVison)
                    .post(body)
                    .addHeader("Accept", "application/json")
                    .build();
            final OkHttpClient client = new OkHttpClient();
            final Response response = client.newCall(request).execute();
            String teste =  response.body().string();

            return  true;
        } catch (Exception e) {
            Log.e("Your tag", "Error", e);
        }
        return false;
    }
    */
}
