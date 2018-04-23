package com.example.mailson.tcc;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mailson.tcc.classes.ConnectionWS;
import com.example.mailson.tcc.classes.Notificacao;
import com.google.firebase.iid.FirebaseInstanceId;

import java.net.ProtocolException;
import java.sql.Connection;

import dmax.dialog.SpotsDialog;

public class LogarActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnEntrar;
    EditText edtSenha;
    EditText edtPlaca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logar);

        String teste = Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);

        String teste2  = FirebaseInstanceId.getInstance().getToken();
         if(getIntent().getExtras() != null){
             if(getIntent().getExtras().getBoolean("Login")){
                 //se a thead acabar e não enviar pra tela de ações o login esta incorreto
                 Toast.makeText(this, "Senha ou placa Inválidos.", Toast.LENGTH_SHORT).show();
             }
         }

        btnEntrar = findViewById(R.id.btnLogar);
        edtSenha = findViewById(R.id.edtSenha);
        edtPlaca = findViewById(R.id.edtPlaca);
        btnEntrar.setOnClickListener(this);

        ImageView imageView = findViewById(R.id.imgDocFoto);



    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {

            case R.id.btnLogar:

                ProgressDialog dialog = new ProgressDialog(LogarActivity.this);
                dialog.setTitle("Login.");
                dialog.setMessage("Aguarde...");
                dialog.show();

                     new Thread(new Runnable()
                    {
                        public void run() {
                            try {
                                if (ConnectionWS.Login(edtPlaca.getText().toString(), edtSenha.getText().toString())) {

                                    //start thread de login
                                    //quando logar salvar no slqlite.
                                    Intent intent = new Intent(getApplicationContext(), AcoesActivity.class);
                                    startActivity(intent);
                                }
                                else{
                                    Intent intent = new Intent(getApplicationContext(), LogarActivity.class);
                                    intent.putExtra("Login", true);
                                    startActivity(intent);
                                }
                              } catch (ProtocolException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                    //thread.start();
                    //thread.join();
                break;
        }
    }
}
