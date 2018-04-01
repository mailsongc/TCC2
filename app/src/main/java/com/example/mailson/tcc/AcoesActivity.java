package com.example.mailson.tcc;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class AcoesActivity extends AppCompatActivity {

    private GestureDetectorCompat gestureObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acoes);

        gestureObject = new GestureDetectorCompat(this, new LearnGesture());


    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.gestureObject.onTouchEvent(event);
        return super.onTouchEvent(event);
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

            }

            return  true;
        }
    }
}
