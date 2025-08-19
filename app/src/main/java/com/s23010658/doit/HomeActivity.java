package com.s23010658.doit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    Button btnUpload, btnRequests, btnNotifications, btnStatus, btnProfile;
    ImageView imageView3; // Logout image
    String username, password; // Needed for ProfileActivitys

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnUpload = findViewById(R.id.btnUpload);
        btnRequests = findViewById(R.id.btnRequests);
        btnNotifications = findViewById(R.id.btnNotifications);
        btnStatus = findViewById(R.id.btnStatus);
        btnProfile = findViewById(R.id.btnProfile);
        imageView3 = findViewById(R.id.imageView3);

        // Get username & password from Intent or set default to avoid crash
        username = getIntent().getStringExtra("etName");
        password = getIntent().getStringExtra("etRetypePassword");

        if(username == null || password == null){
            username = "";
            password = "";
        }

        btnUpload.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, UploadAssignmentActivity.class)));
        btnRequests.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, ViewAssignmentsActivitys.class)));
        btnNotifications.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, NotificationActivitys.class)));
        btnStatus.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, StatusActivity.class)));

        btnProfile.setOnClickListener(v -> {
            if(!username.isEmpty() && !password.isEmpty()){
                Intent intent = new Intent(HomeActivity.this, ProfileActivitys.class);
                intent.putExtra("username", username);
                intent.putExtra("password", password);
                startActivity(intent);
            } else {
                Toast.makeText(this, "User details not found!", Toast.LENGTH_SHORT).show();
            }
        });

        imageView3.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
}
