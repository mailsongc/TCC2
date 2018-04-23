package com.example.mailson.tcc;

import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.example.mailson.tcc.classes.HistoricoClass;
import com.example.mailson.tcc.classes.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class HistoricoActivity extends AppCompatActivity {

    private GestureDetectorCompat gestureObject;
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private List<HistoricoClass> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);
        gestureObject = new GestureDetectorCompat(this, new LearnGesture());

        recyclerView = (RecyclerView) findViewById(R.id.rcvHistorico);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        HistoricoClass obj2  = new HistoricoClass();
        obj2.setAcao("Porta Aberta");
        obj2.setHorario("19/03/2018 08:30");
        obj2.setIcone("ic_luz_acessa");
        list.add(obj2);


        HistoricoClass obj  = new HistoricoClass();
        obj.setAcao("Luz Acesa");
        obj.setHorario("21/03/2018 18:30");
        obj.setIcone("ic_luz_acessa");
        list.add(obj);

        adapter = new RecyclerAdapter(list);
        recyclerView.setAdapter(adapter);

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

            }
            else
            if(event2.getX() < event1.getX()){
                //de direita para a esquerda
                //TransitionManager.beginDelayedTransition(root, new Slide());
                Intent intent = new Intent(HistoricoActivity.this, AcoesActivity.class);
                finish();
                startActivity(intent);
            }

            return  true;
        }
    }
}
