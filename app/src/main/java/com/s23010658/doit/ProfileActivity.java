package com.s23010658.doit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button btnAddress = findViewById(R.id.btnAddress);
        TextView tvAddress = findViewById(R.id.tv_address);

        btnAddress.setOnClickListener(v -> {
            String location = tvAddress.getText().toString().trim();
            Intent intent = new Intent(ProfileActivity.this, MapActivity.class);
            intent.putExtra("location", location);
            startActivity(intent);
        });
    }
}
