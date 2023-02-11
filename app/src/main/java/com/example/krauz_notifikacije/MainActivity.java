package com.example.krauz_notifikacije;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import  android.R.drawable;
import android.os.CountDownTimer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditText et1 = (EditText) findViewById(R.id.editTextTextPersonName);
        Button bt1 = (Button) findViewById(R.id.button);
        Button bt2 = (Button) findViewById(R.id.button2);
        createNotificationChannel();
        NotificationCompat.Builder builder1 = new NotificationCompat.Builder(this, "51")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Upisani Tekst:")
                .setContentText(et1.getText())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationCompat.Builder builder2 = new NotificationCompat.Builder(this, "51")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Upisani Tekst:")
                .setContentText("Cobivena aplikacija nakon 10s!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);


        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                if (ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[] { Manifest.permission.POST_NOTIFICATIONS },
                            10);
                }
                notificationManager.notify(51, builder1.build());
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                new CountDownTimer(10000, 1000) {

                    public void onTick(long millisUntilFinished)
                    {

                    }


                    public void onFinish()
                    {
                        if (ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED)
                        {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[] { Manifest.permission.POST_NOTIFICATIONS },
                                    10);
                        }
                    notificationManager.notify(51,builder2.build());
                    }
                }.start();



            }
        });
    }
    private void createNotificationChannel()
    {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            CharSequence name = "Notf_1";
            String description = "Notc_ch_1";

            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel("51", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}

