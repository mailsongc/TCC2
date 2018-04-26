package com.example.mailson.tcc;

import android.app.AlertDialog;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mailson.tcc.DTO.jsonVisionPost;
import com.example.mailson.tcc.classes.ConnectionWS;
import com.example.mailson.tcc.classes.Dados;

import org.w3c.dom.Text;

import dmax.dialog.SpotsDialog;

public class DadosActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtPlaca;
    TextView txtModelo;
    TextView txtNome;
    TextView txtRenavam;
    EditText edtSenha;
    jsonVisionPost obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados);
        txtPlaca = findViewById(R.id.txtPlaca);
        txtModelo = findViewById(R.id.txtModelo);
        txtNome = findViewById(R.id.txtNome);
        txtRenavam = findViewById(R.id.txtRenavam);
        edtSenha = findViewById(R.id.edtSenha);
        
        Button btnConfirma = findViewById(R.id.btnConfirmar);
        obj = (jsonVisionPost) getIntent().getSerializableExtra("Objeto");

        txtPlaca.setText("Placa: "+obj.getPlaca());
        txtModelo.setText("Modelo: " +obj.getModela());
        txtNome.setText("Nome: " +obj.getNome());
        txtRenavam.setText("Renavam: "+obj.getRenavam());

        btnConfirma.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {

            case R.id.btnConfirmar:
                if(edtSenha.length() < 3){
                    Toast.makeText(this, "Senha tem que possuir no minímo 3 caracteres.", Toast.LENGTH_SHORT).show();
                    return;
                }
                //salvar cadastro e manter logado
                AlertDialog dialog = new SpotsDialog(DadosActivity.this, R.style.CustomDialogCadastro);
                dialog.show();
                obj.setSenha(edtSenha.getText().toString());
                new Thread(new Runnable()
                {
                    public void run() {
                        if (obj.isSucesso()) {
                            ConnectionWS.Cadastrar(obj);
                            Intent intent = new Intent(getApplicationContext(), AcoesActivity.class);
                            startActivity(intent);
                        }
                        else{//não fez cadastro


                        }
                    }
                }).start();


                break;
        }
    }
}
