//Add this code in your service file, if you are using any other app from the app given in tutorial, then you need to change the package name to your app's package name.

package com.cjcreations.tutorial.foregroundservice2;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import androidx.core.app.NotificationCompat;
import android.widget.*;
import java.util.*;
import android.os.*;
import android.content.*;
import android.app.*;

public class ForegroundService extends Service {
	
    public static final String CHANNEL_ID = "ForegroundServiceChannel";
    
    @Override
    public void onCreate() {
        super.onCreate();
    }
	
	Notification notification;
	
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String input = intent.getStringExtra("inputExtra");
		Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0, notificationIntent, 0);
        
        //It will check if the android version is greater then OREO and create a channel for the notification.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
			
        createNotificationChannel();
        												
        notification = new NotificationCompat.Builder(this, CHANNEL_ID)
			.setContentTitle("Foreground Service")
			.setContentText(input)
		    .setSmallIcon(R.drawable.app_icon)
			.setContentIntent(pendingIntent)
			.build();
      
      //If the android version is greater then LOLLIPOP then no need to create a channel for notification.
    	} else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			notification = new NotificationCompat.Builder(this, CHANNEL_ID)
				.setContentTitle("Foreground Service")
				.setContentText(input)
			    .setSmallIcon(R.drawable.app_icon)
				.setContentIntent(pendingIntent)
				.build();
		}
    
    //This will start a foreground service with a notification.
		startForeground(1, notification);
	
        return START_NOT_STICKY;
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
				CHANNEL_ID,
				"Foreground Service Channel",
				NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }
}
