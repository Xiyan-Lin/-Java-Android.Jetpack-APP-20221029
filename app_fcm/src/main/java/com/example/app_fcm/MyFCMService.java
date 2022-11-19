package com.example.app_fcm;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyFCMService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        String title, body;

        if(message.getNotification() != null) {
            // 資料來自於 官方網頁
            title = message.getNotification().getTitle();
            body = message.getNotification().getBody();
        } else {
            // 資料來自於 FCM_Console
            Map<String, String> map = message.getData();
            title = map.get("title");
            body = map.get("body");
        }

        Log.i("fcm", "title: " + title);
        Log.i("fcm", "body: " + body);
        // 將訊息彈出
        final String CHANNEL_ID = "my_channel_id";
        // 設定 channel
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "my_name", NotificationManager.IMPORTANCE_HIGH);
        getSystemService(NotificationManager.class).createNotificationChannel(channel);
        // 將 resource image 轉 bitmap
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.pig);
        // 按下通知視窗可以啟動 App 的某一個 Activity
        Intent intent = new Intent(this, MainActivity.class);
        intent.setAction("myaction");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("title", title);
        intent.putExtra("body", body);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 101, intent, 0);

        NotificationCompat.Builder notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setContentIntent(pendingIntent)
                .setSmallIcon(android.R.drawable.star_on)
                .setLargeIcon(bmp)
                .setAutoCancel(true);
        NotificationManagerCompat.from(this).notify(1, notification.build());
        super.onMessageReceived(message);
    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.i("fcm", "onNewToken: " + token);
    }
}
