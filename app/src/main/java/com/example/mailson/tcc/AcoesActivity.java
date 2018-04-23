package com.example.mailson.tcc;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;


public class AcoesActivity extends AppCompatActivity {

    private android.widget.GridLayout gridAcao;
    private GestureDetectorCompat gestureObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acoes);

        gestureObject = new GestureDetectorCompat(this, new LearnGesture());
        gridAcao = (android.widget.GridLayout) findViewById(R.id.gridAcoes);


        //eventos
        setSingleEvent(gridAcao);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.gestureObject.onTouchEvent(event);
        return super.onTouchEvent(event);
    }


    public void setSingleEvent(GridLayout grid) {
        for (int i=0;i<grid.getChildCount();i++){
            CardView cardView = (CardView)grid.getChildAt(i);
            final int finalI = i;
            //clique de cada icone

            switch (finalI){

                case(0) : //luz acesa
                    cardView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //  Toast.makeText(AcoesActivity.this, "Click " + finalI, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), LerPlacaActivity.class);
                            intent.putExtra("Notificacao","Luz Acessa");
                            startActivity(intent);
                        }
                    });
                    break;

                case(1) : //Porta Aberta
                    cardView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //  Toast.makeText(AcoesActivity.this, "Click " + finalI, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), LerPlacaActivity.class);
                            intent.putExtra("Notificacao","Porta Aberta");
                            startActivity(intent);
                        }
                    });
                    break;

                case(2) : //Alarme disparado
                    cardView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //  Toast.makeText(AcoesActivity.this, "Click " + finalI, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), LerPlacaActivity.class);
                            intent.putExtra("Notificacao","Alarme Disparado");
                            startActivity(intent);
                        }
                    });
                    break;

                case(3) : //luz acesa
                    cardView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //  Toast.makeText(AcoesActivity.this, "Click " + finalI, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), LerPlacaActivity.class);
                            intent.putExtra("Notificacao","Luz Acessa");
                            startActivity(intent);
                        }
                    });
                    break;

                case(4) : //luz acesa
                    cardView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //  Toast.makeText(AcoesActivity.this, "Click " + finalI, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), LerPlacaActivity.class);
                            intent.putExtra("Notificacao","Luz Acessa");
                            startActivity(intent);
                        }
                    });
                    break;

                case(5) : //luz acesa
                    cardView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //  Toast.makeText(AcoesActivity.this, "Click " + finalI, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), LerPlacaActivity.class);
                            intent.putExtra("Notificacao","Luz Acessa");
                            startActivity(intent);
                        }
                    });
                    break;
            }

        }
    }

    class  LearnGesture extends GestureDetector.SimpleOnGestureListener{

        @Override
        public  boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY){

            if(event2.getX() > event1.getX()){
                //de esquerda para a direita
                Intent intent = new Intent(AcoesActivity.this, HistoricoActivity.class);
                finish();
                startActivity(intent);
            }
            else
            if(event2.getX() < event1.getX()){
                //de direita para a esquerda
                //de esquerda para a direita


            }

            return  true;
        }
    }
}
