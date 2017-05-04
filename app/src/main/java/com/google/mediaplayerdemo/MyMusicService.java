package com.google.mediaplayerdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import java.io.IOException;

public class MyMusicService extends Service {
    String songToPlay;
    MediaPlayer mediaPlayer;

    NotificationManager notificationManager;
    Notification notification;
    NotificationCompat.Builder builder;

    @Override
    public void onCreate()
    {
        super.onCreate();
        Toast.makeText(this,"MyMusicService Created",Toast.LENGTH_LONG).show();
        mediaPlayer = new MediaPlayer();
    }

    @Override
    public int onStartCommand(Intent intent,  int flags, int startId)
    {
        songToPlay=intent.getStringExtra("keySong");
        String path= Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+songToPlay;

        builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.music);
        builder.setContentTitle("Song is Playing");
        builder.setContentText("Song: "+songToPlay);

        builder.setDefaults(Notification.DEFAULT_ALL);
        builder.setStyle(new NotificationCompat.BigTextStyle());

        builder.addAction(android.R.drawable.ic_menu_add,"Add",null);
        builder.addAction(android.R.drawable.ic_delete,"Delete",null);

        notification = builder.build();

        try {
            mediaPlayer.setDataSource(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

            mediaPlayer.start();
        notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(101,notification);

        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"MyMusicService Destroyed",Toast.LENGTH_LONG).show();

        mediaPlayer.stop();
        mediaPlayer.release();
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
