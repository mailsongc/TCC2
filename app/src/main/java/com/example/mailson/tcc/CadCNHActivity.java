package com.example.mailson.tcc;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mailson.tcc.Classes.Dados;

public class CadCNHActivity extends AppCompatActivity {

    private ImageView imageView;
    private Bitmap imagemCNh;
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

            }
        });

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(imagemCNh != null){

                    new Thread(new Runnable()
                    {
                        public void run() {

                            if (Dados.EnviarCNH((imagemCNh))) {
                                Intent intent = new Intent(getApplicationContext(), DadosActivity.class);
                               startActivity(intent);
                            }
                        }
                    }).start();

                    //Dados.EnviarCNH(imagemCNh);
                 /* Dados enviou =  new Dados();
                  enviou.execute(imagemCNh);
                  if(enviou.getStatus().){

                  }
                    */
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        imagemCNh = (Bitmap)data.getExtras().get("data");
        imageView.setImageBitmap(imagemCNh);
    }
}
