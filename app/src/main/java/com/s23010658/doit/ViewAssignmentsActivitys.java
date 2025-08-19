package com.s23010658.doit;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ViewAssignmentsActivitys extends AppCompatActivity {

    ListView listView;
    DatabaseHelper dbHelper;
    ArrayList<Assignment> assignmentList;
    AssignmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_assignments);

        listView = findViewById(R.id.listViewAssignments);
        dbHelper = new DatabaseHelper(this);
        assignmentList = new ArrayList<>();

        // Fetch assignments from DB
        Cursor cursor = dbHelper.getAllAssignments();
        if (cursor.moveToFirst()) {
            do {
                String subject = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_SUBJECT));
                String description = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_DESCRIPTION));
                String amount = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_AMOUNT));
                String deadline = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_DEADLINE));
                String pdfUri = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_PDFURI));

                assignmentList.add(new Assignment(subject, description, amount, deadline, pdfUri));
            } while (cursor.moveToNext());
        }
        cursor.close();

        // Set adapter
        adapter = new AssignmentAdapter(this, assignmentList);
        listView.setAdapter(adapter);
    }
}
