package com.s23010658.doit;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import java.io.File;
import java.util.ArrayList;

public class AssignmentAdapter extends ArrayAdapter<Assignment> {

    private Context context;
    private ArrayList<Assignment> assignments;
    private DatabaseHelper dbHelper;

    public AssignmentAdapter(@NonNull Context context, ArrayList<Assignment> assignments) {
        super(context, 0, assignments);
        this.context = context;
        this.assignments = assignments;
        dbHelper = new DatabaseHelper(context); // Initialize DB helper here
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.assignment_item, parent, false);
        }

        Assignment assignment = assignments.get(position);

        TextView tvInfo = convertView.findViewById(R.id.tvAssignmentInfo);
        Button btnView = convertView.findViewById(R.id.btnView);
        Button btnAccept = convertView.findViewById(R.id.btnAccept);
        Button btnDecline = convertView.findViewById(R.id.btnDecline);

        String info = "Subject: " + assignment.subject +
                "\nDescription: " + assignment.description +
                "\nAmount: " + assignment.amount +
                "\nDeadline: " + assignment.deadline;
        tvInfo.setText(info);

        btnView.setOnClickListener(v -> {
            if (assignment.pdfUri != null && !assignment.pdfUri.isEmpty()) {
                try {
                    Uri uri;

                    if (assignment.pdfUri.startsWith("http") || assignment.pdfUri.startsWith("content://")) {
                        uri = Uri.parse(assignment.pdfUri);
                    } else {
                        File file = new File(assignment.pdfUri);
                        uri = FileProvider.getUriForFile(
                                context,
                                context.getPackageName() + ".provider",
                                file
                        );
                    }

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(uri, "application/pdf");
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                    if (intent.resolveActivity(context.getPackageManager()) != null) {
                        context.startActivity(intent);
                    } else {
                        Toast.makeText(context, "No PDF viewer installed", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(context, "Cannot open PDF: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(context, "No PDF available", Toast.LENGTH_SHORT).show();
            }
        });

        // ===== Updated Accept Button: Insert notification =====
        btnAccept.setOnClickListener(v -> {
            String message = assignment.subject + " - Accepted";
            boolean inserted = dbHelper.insertNotification(message);

            if (inserted) {
                Toast.makeText(context, "Assignment accepted and notification created", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Error creating notification", Toast.LENGTH_SHORT).show();
            }
        });

        btnDecline.setOnClickListener(v ->
                Toast.makeText(context, "Declined: " + assignment.subject, Toast.LENGTH_SHORT).show()
        );

        return convertView;
    }
}
