package com.example.mailson.tcc;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mailson.tcc.DTO.jsonVisionPost;
import com.example.mailson.tcc.classes.Dados;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URI;

import dmax.dialog.SpotsDialog;

public class CadCNHActivity extends AppCompatActivity {

    private ImageView imageView;
    private Bitmap imagemCNh;
    private Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_cnh2);

        Button btnEnviar = (Button)findViewById(R.id.btnEnviarCNHFoto);
        Button btnTirarFoto = (Button)findViewById(R.id.btnTirarFotoCNH);
        imageView = (ImageView)findViewById(R.id.imgCNHFoto);



        btnTirarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);

               /*  File file = new File(Environment.getExternalStorageDirectory(), "/your_name_folder/a" + "/photo_" + 44 + ".png");
                imageUri = Uri.fromFile(file);

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, PICTURE_RESULT);
                */

            }
        });

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(imagemCNh != null){
                    AlertDialog dialog = new SpotsDialog(CadCNHActivity.this, R.style.CustomDialog);
                    dialog.show();
                    new Thread(new Runnable()
                    {
                        public void run() {
                            jsonVisionPost obj = new jsonVisionPost();
                            obj = Dados.EnviarCNH(imagemCNh);

                            if (obj.isSucesso()) {
                                Intent intent = new Intent(getApplicationContext(), CadDocActivity.class);

                                intent.putExtra("Objeto", obj);
                               startActivity(intent);
                            }
                        }
                    }).start();

                }
                else{
                    //
                    Toast.makeText(CadCNHActivity.this, "Necessário tira foto da CNH.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri selectedImageUri = data.getData();
        InputStream inputStream = null;

       // Bundle extra = new Bundle();
       // extra.putString("imageFile", selectedImageUri.toString());
        //Bitmap srcBmp = BitmapFactory.decodeStream(getApplicationContext().getContentResolver().openInputStream(uri), null, null);

        //imagemCNh = (Bitmap)data.getExtras().get("data");

        try {
            inputStream = getContentResolver().openInputStream(selectedImageUri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            imagemCNh = bitmap;
            imageView.setImageBitmap(bitmap);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
