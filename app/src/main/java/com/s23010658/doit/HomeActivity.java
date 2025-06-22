package com.s23010658.doit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    Button btnUpload, btnRequests, btnNotifications, btnStatus, btnProfile;
    ImageView imageView3; // 🔹 Logout image

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // 🔹 Find Views by ID
        btnUpload = findViewById(R.id.btnUpload);
        btnRequests = findViewById(R.id.btnRequests);
        btnNotifications = findViewById(R.id.btnNotifications);
        btnStatus = findViewById(R.id.btnStatus);
        btnProfile = findViewById(R.id.btnProfile);
        imageView3 = findViewById(R.id.imageView3); // 🔹 Logout icon

        // 🔹 Upload Assignment Button
        btnUpload.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, UploadAssignmentActivity.class);
            startActivity(intent);
        });

        // 🔹 View Requests Button
        btnRequests.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ViewRequestsActivity.class);
            startActivity(intent);
        });

        // 🔹 Notifications Button
        btnNotifications.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, NotificationsActivity.class);
            startActivity(intent);
        });

        // 🔹 Status Button
        btnStatus.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, StatusActivity.class);
            startActivity(intent);
        });

        // 🔹 Profile Button
        btnProfile.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
            startActivity(intent);
        });

        // ✅ Logout Button (imageView3)
        imageView3.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Clear backstack
            startActivity(intent);
            finish(); // Close HomeActivity
        });
    }

    // Optional toast utility
    private void showToast(String message) {
        android.widget.Toast.makeText(HomeActivity.this, message, android.widget.Toast.LENGTH_SHORT).show();
    }
}
