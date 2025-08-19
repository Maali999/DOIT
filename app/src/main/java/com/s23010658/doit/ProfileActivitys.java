package com.s23010658.doit;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;

public class ProfileActivitys extends AppCompatActivity {

    EditText etProfileName;
    TextView tvUniversity, tvSkills, tvEmail, tvLocation;
    Button btnUpdateProfile;
    DatabaseHelper dbHelper;

    String username, password; // Needed to fetch user details

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_activitys);

        etProfileName = findViewById(R.id.etProfileName);
        tvUniversity = findViewById(R.id.tvProfileUniversity);
        tvSkills = findViewById(R.id.tvProfileSkills);
        tvEmail = findViewById(R.id.tvProfileEmail);
        tvLocation = findViewById(R.id.tvProfileLocation);
        btnUpdateProfile = findViewById(R.id.btnUpdateProfile);

        dbHelper = new DatabaseHelper(this);

        // Get username and password from Intent
        username = getIntent().getStringExtra("username");
        password = getIntent().getStringExtra("password");

        loadUserDetails();

        btnUpdateProfile.setOnClickListener(v -> {
            String newName = etProfileName.getText().toString().trim();
            if(newName.isEmpty()){
                Toast.makeText(this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
            } else {
                boolean updated = dbHelper.updateUserName(username, password, newName);
                if(updated){
                    Toast.makeText(this, "Name updated successfully", Toast.LENGTH_SHORT).show();
                    username = newName; // Update local variable
                } else {
                    Toast.makeText(this, "Update failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadUserDetails(){
        Cursor cursor = dbHelper.getUserDetails(username, password);
        if(cursor.moveToFirst()){
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_NAME));
            String university = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_UNIVERSITY));
            String skills = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_SKILLS));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_EMAIL));
            String location = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_LOCATION));

            etProfileName.setText(name);
            tvUniversity.setText(university);
            tvSkills.setText(skills);
            tvEmail.setText(email);
            tvLocation.setText(location);
        }
        cursor.close();
    }
}
