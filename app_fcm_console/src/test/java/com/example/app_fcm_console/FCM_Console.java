package com.example.app_fcm_console;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;

import org.junit.Test;

import java.io.FileInputStream;
import java.util.Random;

// 生成一個新密鑰(json)
// https://console.firebase.google.com/

public class FCM_Console {
    @Test
    public void main() throws Exception {

        FileInputStream serviceAccount =
                new FileInputStream("src/test/java/com/example/app_fcm_console/firebase-adminsdk.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                //.setDatabaseUrl("https://iot-pcschool.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);

        // 要推播裝置的 token
        String regToken = "c4IiI4fIQQKn8Oj0TPi_zk:APA91bGKyZIPtjLc3TD9GtZvYlZPRp8nudnMLJYNfrmf00kEL3Tt8DovFTaT4_0j3pUC7p9eFcsYB8jZYzjHekPK2RT7SBAmQm1FLPe92ZM68QMVl4cEKyWMK_FXsY2oUaoA5Zv4BBEM";
        Message message = Message.builder()
                .putData("title", "Java 爪哇")
                .putData("body", "Android " + new Random().nextInt(1000))
                .setToken(regToken)
                .build();

        String response = FirebaseMessaging.getInstance().send(message);
        System.out.println("Successfully sent message: " + response);


    }
}
