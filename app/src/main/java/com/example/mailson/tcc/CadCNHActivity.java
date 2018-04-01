package com.example.mailson.tcc;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class CadCNHActivity extends AppCompatActivity implements View.OnClickListener {
    final int Camera_Request_Code = 1343;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_cnh);


        Button btnEnviar = findViewById(R.id.btnEnviarCNH);

        btnEnviar.setOnClickListener(this);



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
