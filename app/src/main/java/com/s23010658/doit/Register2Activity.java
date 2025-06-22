package com.s23010658.doit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class Register2Activity extends AppCompatActivity {

    EditText etUniversity, etFaculty, etDegree, etSkills;
    Button btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        etUniversity = findViewById(R.id.etUniversity);
        etFaculty = findViewById(R.id.etFaculty);
        etDegree = findViewById(R.id.etDegree);
        etSkills = findViewById(R.id.etSkills);
        btnDone = findViewById(R.id.btnDone);

        btnDone.setOnClickListener(v -> {
            String university = etUniversity.getText().toString().trim();
            String faculty = etFaculty.getText().toString().trim();
            String degree = etDegree.getText().toString().trim();
            String skills = etSkills.getText().toString().trim();

            if (university.isEmpty() || faculty.isEmpty() || degree.isEmpty() || skills.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Registration complete!", Toast.LENGTH_LONG).show();

                // Navigate to HomeActivity
                Intent intent = new Intent(Register2Activity.this, HomeActivity.class);
                startActivity(intent);
                finish(); // Optional: close the registration screen
            }

        });
    }
}
