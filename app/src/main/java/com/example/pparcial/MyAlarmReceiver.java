package com.example.pparcial;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import java.util.Calendar;


public class MyAlarmReceiver extends BroadcastReceiver {

    public static final int REQUEST_CODE = 12345;
    private NotificationManager notificationManager;
    private final int NOTIFICATION_ID = 1010;
    private AdminSQLiteOpenHelper admin;
    private Cursor fila;
    private SQLiteDatabase bd;
    private String alarma,descripcion,titulo;



    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, MyTestService.class);
        context.startService(i);
        Calendar calendario = Calendar.getInstance();
        int hora, min,dia,mes,ano;
        String cadenaF, cadenaH,fecha_sistema,hora_sistema;



        dia = calendario.get(Calendar.DAY_OF_MONTH);
        mes = calendario.get(Calendar.MONTH)+1;
        ano = calendario.get(Calendar.YEAR);
        hora = calendario.get(Calendar.HOUR_OF_DAY);
        min = calendario.get(Calendar.MINUTE);
        fecha_sistema=mes+"-"+dia+"-"+ano+" ";
        hora_sistema=hora+":"+min;
        admin = new AdminSQLiteOpenHelper(context, vars.bd, null, vars.version);
        bd = admin.getWritableDatabase();






        if(bd!=null) {
            fila = bd.rawQuery("SELECT * FROM alarma WHERE fecha='"+fecha_sistema+"' AND hora= '"+hora_sistema+"'", null);
            if(fila.moveToFirst()){
                alarma=fila.getString(0);
                titulo=fila.getString(1);
                descripcion =fila.getString(2);
                triggerNotification(context,titulo+"\n"+descripcion);

                Toast.makeText(context, "Alarma", Toast.LENGTH_LONG).show();



















            }
        }
        bd.close();








    }



    private void triggerNotification(Context contexto, String t) {

        Intent notificationIntent = new Intent(contexto, MainActivity.class);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent contentIntent = PendingIntent.getActivity(contexto, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        long[] pattern = new long[]{2000, 1000, 2000};
        NotificationCompat.Builder builder = new NotificationCompat.Builder(contexto, "M_CH_ID");

        builder.setContentIntent(contentIntent)

                .setTicker("" )
                .setContentTitle("alarma")
                .setContentTitle("")
                .setContentText(t)
                .setContentInfo("Info")
                .setLargeIcon(BitmapFactory.decodeResource(contexto.getResources(), R.drawable.ic_alarm_black_24dp))
                .setSmallIcon(R.drawable.ic_alarm_black_24dp)
                .setAutoCancel(true) //Cuando se pulsa la notificación ésta desaparece
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM))
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setVibrate(new long[]{0, 300, 200, 300});


        Notification notificacion = new NotificationCompat.BigTextStyle(builder)
                .bigText(t)
                .setBigContentTitle("Recordatorio")
                .setSummaryText("Resumen de tareas")
                .build();



        NotificationManager notificationManager = (NotificationManager) contexto.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());








    }









}
