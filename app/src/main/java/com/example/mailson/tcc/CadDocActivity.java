package com.example.mailson.tcc;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mailson.tcc.DTO.jsonVisionPost;
import com.example.mailson.tcc.classes.Dados;

import java.io.FileNotFoundException;
import java.io.InputStream;

import dmax.dialog.SpotsDialog;


public class CadDocActivity extends AppCompatActivity {

       private ImageView imageView;
    private Bitmap imagemDoc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_doc);

         Button btnEnviar = (Button)findViewById(R.id.btnEnviarDocFoto);
        Button btnTirarFoto = (Button)findViewById(R.id.btnTirarFotoDoc);
        imageView = (ImageView)findViewById(R.id.imgDocFoto);

        jsonVisionPost obj = (jsonVisionPost) getIntent().getSerializableExtra("Objeto");


        btnTirarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);

            }
        });

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(imagemDoc != null){
                    AlertDialog dialog = new SpotsDialog(CadDocActivity.this, R.style.CustomDialogDoc);
                    dialog.show();
                    new Thread(new Runnable()
                    {
                        public void run() {
                            jsonVisionPost obj = Dados.EnviarCNH(imagemDoc);
                            if (obj.isSucesso()) {
                                Intent intent = new Intent(getApplicationContext(), DadosActivity.class);
                                intent.putExtra("Objeto", obj);
                                startActivity(intent);
                            }
                            else{//não fez cadastro


                            }
                        }
                    }).start();


                }
                else{
                    Toast.makeText(CadDocActivity.this, "Necessário tirar foto do documento do carro.", Toast.LENGTH_SHORT).show();
                }
                    
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri selectedImageUri = data.getData();
        InputStream inputStream = null;
        try {
            inputStream = getContentResolver().openInputStream(selectedImageUri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            imagemDoc = bitmap;
            imageView.setImageBitmap(bitmap);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
