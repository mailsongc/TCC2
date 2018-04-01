package com.example.mailson.tcc;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DadosActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados);

        Button btnConfirma = findViewById(R.id.btnConfirmar);

        btnConfirma.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {

            case R.id.btnConfirmar:

                Intent intent = new Intent(this, AcoesActivity.class);
                startActivity(intent);



                break;
        }
    }
}
