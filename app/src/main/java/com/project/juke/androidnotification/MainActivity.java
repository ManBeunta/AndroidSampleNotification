package com.project.juke.androidnotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    // Deklarasi Widget
    Button BtnSend;
    EditText EdJudul, EdDeskripsi;

    // deklarasi kode request
    public static final int notifikasi = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // installisasi widget-widget yang ada di activity_main.xml
        BtnSend = findViewById(R.id.BtnSend);
        EdJudul = findViewById(R.id.EdJudul);
        EdDeskripsi = findViewById(R.id.EdDeskripsi);

        // action ketika tombol di klik
        BtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Membuat komponen intent
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                // buat method untung menampilkan notifikasi dengan
                // mengirimkan data yang dikirim dari widget EditText
                munculkanNotifikasi(EdJudul.getText().toString(), EdDeskripsi.getText().toString(), intent);
            }
        });
    }

    private void munculkanNotifikasi(String s, String s1, Intent intent) {
        // membuat komponen peding intent
        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this
                , notifikasi, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // membuat komponen notifikasi
        Notification.Builder builder = new Notification.Builder(MainActivity.this);
        Notification notification;
        notification = builder.setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setContentTitle(s)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(MainActivity.this.getResources()
                        , R.mipmap.ic_launcher))
                .setContentText(s1)
                .build();

        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        NotificationManager notificationManager = (NotificationManager) MainActivity.this
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notifikasi, notification);
    }
}
