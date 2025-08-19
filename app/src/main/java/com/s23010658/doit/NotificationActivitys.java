package com.s23010658.doit;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class NotificationActivitys extends AppCompatActivity {

    ListView listView;
    DatabaseHelper dbHelper;
    ArrayList<Notification> notificationList;
    NotificationAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_activitys);

        listView = findViewById(R.id.listViewNotifications);
        dbHelper = new DatabaseHelper(this);
        notificationList = new ArrayList<>();

        // Fetch all notifications from DB
        Cursor cursor = dbHelper.getAllNotifications();
        if(cursor.moveToFirst()){
            do {
                String message = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_MESSAGE));
                String timestamp = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_TIMESTAMP));
                notificationList.add(new Notification(message, timestamp));
            } while (cursor.moveToNext());
        }
        cursor.close();

        // Set adapter
        adapter = new NotificationAdapter(this, notificationList);
        listView.setAdapter(adapter);
    }
}
