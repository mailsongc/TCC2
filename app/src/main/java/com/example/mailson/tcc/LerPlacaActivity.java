package com.example.mailson.tcc;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.mailson.tcc.classes.Dados;
import com.example.mailson.tcc.classes.Notificacao;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dmax.dialog.SpotsDialog;

public class LerPlacaActivity extends AppCompatActivity implements View.OnClickListener {
    final int Camera_Request_Code = 1343;
    ImageView imgImageView;
    TextView textView;
    SurfaceView surfaceView;
    TextView txtTeste;
    CameraSource cameraSource;
    final int RequestCameraId = 1001;
    String notificacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_cnh);


        Button btnEnviar = findViewById(R.id.btnEnviarCNH);
        textView = (TextView) findViewById(R.id.txtTest);

        surfaceView = (SurfaceView) findViewById(R.id.svCamera);
        txtTeste = (TextView) findViewById(R.id.txtTesteLeitura);

        notificacao = getIntent().getStringExtra("Notificacao");
        TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();

        if (!textRecognizer.isOperational()) {
            Log.w("log", "log errrooooo");
        } else {
            cameraSource = new CameraSource.Builder(getApplicationContext(), textRecognizer)
                    .setFacing(CameraSource.CAMERA_FACING_BACK)
                    .setRequestedPreviewSize(600, 600)
                    .setRequestedFps(2.0f)
                    .setAutoFocusEnabled(true)
                    .build();

            surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder surfaceHolder) {

                    try {
                        if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                        ActivityCompat.requestPermissions(   LerPlacaActivity.this, new String[]{Manifest.permission.CAMERA}, RequestCameraId);
                            return;
                        }
                        cameraSource.start(surfaceView.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

                }

                @Override
                public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                    cameraSource.stop();
                }
            });
        }

        textRecognizer.setProcessor(new Detector.Processor<TextBlock>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<TextBlock> detections) {
                    final SparseArray<TextBlock> items = detections.getDetectedItems();

                    if(items.size() != 0){
                        textView.post(new Runnable() {
                            @Override
                            public void run() {
                                StringBuilder stringBuilder = new StringBuilder();
                                 for(int i=0; i< items.size();i++){
                                     TextBlock item = items.valueAt(i);
                                     stringBuilder.append(item.getValue());
                                     stringBuilder.append("\n");
                                 }
                                Pattern pattern = Pattern.compile("[a-zA-Z]{3}\\-\\d{4}");
                                txtTeste.setText(stringBuilder.toString());
                                Matcher m = pattern.matcher(stringBuilder.toString());

                                 if(m.find()){
                                     final  String placa = m.group();
                                     ProgressDialog  dialog = new ProgressDialog(LerPlacaActivity.this);
                                     dialog.setTitle("Enviando Notificação de "+notificacao+" Para Placa:" + m.group());
                                     dialog.show();

                                     //start thread de enviar

                                     new Thread(new Runnable()
                                     {
                                         public void run() {
                                             if (Notificacao.EnviarNotificacao(placa, "")) {
                                                 Intent intent = new Intent(getApplicationContext(), AcoesActivity.class);
                                                 startActivity(intent);
                                             }
                                         }
                                     }).start();

                                 }
                            }
                        });
                    }
            }
        });

        btnEnviar.setOnClickListener(this);
        getTextFromImage();


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case RequestCameraId:{
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    try {
                        cameraSource.start(surfaceView.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
        }
    }

    public  void getTextFromImage(){

        Bitmap bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.mipmap.placa);
        TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();

    if(!textRecognizer.isOperational()){
        Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
    }
    else
    {
        Frame frame = new Frame.Builder().setBitmap(bitmap).build();
        SparseArray<TextBlock> items = textRecognizer.detect(frame);
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<items.size();i++){
            TextBlock myitem = items.valueAt(i);
            sb.append("\n");

        }

        textView.setText(sb.toString());
    }
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        switch (id) {

            case R.id.btnEnviarCNH:

                try{
                    Intent callCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    startActivityForResult(callCameraIntent, Camera_Request_Code);
                }
                catch (Exception ex){
                    Intent intent = new Intent(this, DadosActivity.class);
                    startActivity(intent);
                }



                break;
                 }
            }

            protected void  onActivityResult(int requestCode,int resultCode, Intent data){

                if(requestCode == Camera_Request_Code){
                    Intent intent = new Intent(this, DadosActivity.class);
                    startActivity(intent);
                }
            }

    }
