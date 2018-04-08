package com.example.mailson.tcc.Classes;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.mailson.tcc.MainActivity;
import com.example.mailson.tcc.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class Notificacao extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //Pegar a notificação.
        Log.i("teste", remoteMessage.getNotification().getBody());
        mostrarNotificacao(remoteMessage.getNotification().getBody());
    }

    @Override
    public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter) {
        return super.registerReceiver(receiver, filter);
    }

    @Override
    public void onMessageSent(String s) {
        //Enviar notificação
        super.onMessageSent(s);
    }

    public void mostrarNotificacao(String message){
        PendingIntent pi = PendingIntent.getActivity(this,0,new Intent(this, MainActivity.class),0);
        Notification notification = new NotificationCompat.Builder(this).setSmallIcon(R.drawable.ic_stat_ic_notification)
                .setContentTitle("TESTE")
                .setContentText(message)
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0,notification);



    }
}
