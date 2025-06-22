package com.s23010658.doit;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UploadAssignmentActivity extends AppCompatActivity {

    private static final int PICK_PDF_REQUEST = 1;
    private Uri pdfUri;

    EditText editSubject, editDescription, editAmount, editDeadline;
    Button btnSelectPdf, btnSubmit;
    TextView pdfPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_assignment);

        editSubject = findViewById(R.id.editSubject);
        editDescription = findViewById(R.id.editDescription);
        editAmount = findViewById(R.id.editAmount);
        editDeadline = findViewById(R.id.editDeadline);
        btnSelectPdf = findViewById(R.id.btnSelectPdf);
        btnSubmit = findViewById(R.id.btnSubmit);
        pdfPreview = findViewById(R.id.pdfPreview);

        btnSelectPdf.setOnClickListener(v -> openPdfPicker());

        btnSubmit.setOnClickListener(v -> {
            if (validateInputs()) {
                Toast.makeText(this, "Assignment Submitted!", Toast.LENGTH_SHORT).show();
                // Handle actual upload logic here
            }
        });
    }

    private void openPdfPicker() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("application/pdf");
        startActivityForResult(intent, PICK_PDF_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_PDF_REQUEST && resultCode == RESULT_OK && data != null) {
            pdfUri = data.getData();
            String fileName = pdfUri.getLastPathSegment();
            pdfPreview.setText("Selected PDF: " + fileName);
        }
    }

    private boolean validateInputs() {
        if (editSubject.getText().toString().isEmpty() ||
                editDescription.getText().toString().isEmpty() ||
                editAmount.getText().toString().isEmpty() ||
                editDeadline.getText().toString().isEmpty() ||
                pdfUri == null) {
            Toast.makeText(this, "Please fill all fields and select a PDF", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
