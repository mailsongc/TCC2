package com.example.mailson.tcc.Classes;

import android.app.DownloadManager;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;


import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Dados  {

private static String urlApiVison = "https://vision.googleapis.com/v1/images:annotate?key=AIzaSyDnf8Abt-kO8-beUGhkwLKiMrLc6TuGEXg";


    public  static  boolean EnviarCNH(Bitmap imagem){

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


    public  static  boolean EnviarDocumento(){

        return  true;
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
