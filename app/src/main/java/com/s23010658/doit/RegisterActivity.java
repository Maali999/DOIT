package com.s23010658.doit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText etName, etEmail, etLocation, etPassword, etRetypePassword;
    Button btnDone;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etLocation = findViewById(R.id.etLocation);
        etPassword = findViewById(R.id.etPassword);
        etRetypePassword = findViewById(R.id.etRetypePassword);
        btnDone = findViewById(R.id.btnNext);

        dbHelper = new DatabaseHelper(this);

        btnDone.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String location = etLocation.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String confirmPassword = etRetypePassword.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty() || location.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            // Insert user
            boolean inserted = dbHelper.insertUser(name, email, location, password);
            if (inserted) {
                Toast.makeText(this, "Registered successfully!", Toast.LENGTH_SHORT).show();

                // Pass username and password to Register2Activity
                Intent intent = new Intent(RegisterActivity.this, Register2Activity.class);
                intent.putExtra("username", name);
                intent.putExtra("password", password);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Registration failed. Try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
