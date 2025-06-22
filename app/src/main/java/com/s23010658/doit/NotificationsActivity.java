package com.s23010658.doit;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NotificationsActivity extends AppCompatActivity {

    Button btnViewAssignment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        btnViewAssignment = findViewById(R.id.btnViewAssignment);

        btnViewAssignment.setOnClickListener(view ->
                Toast.makeText(this, "Opening Assignment...", Toast.LENGTH_SHORT).show()
        );
    }
}
