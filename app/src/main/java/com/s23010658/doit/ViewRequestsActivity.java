package com.s23010658.doit;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.s23010658.doit.adapters.RequestAdapter;
import com.s23010658.doit.models.RequestModel;

import java.util.ArrayList;
import java.util.List;

public class ViewRequestsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RequestAdapter adapter;
    private List<RequestModel> requestList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_requests);

        recyclerView = findViewById(R.id.recyclerRequests);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        requestList = new ArrayList<>();
        requestList.add(new RequestModel("Maths", "Deadline: June 25", "Rs.2000", R.drawable.man3));
        requestList.add(new RequestModel("Physics", "Deadline: June 27", "Rs.1800", R.drawable.man3));
        requestList.add(new RequestModel("Chemistry", "Deadline: July 1", "Rs.2100", R.drawable.man3));

        adapter = new RequestAdapter(requestList);
        recyclerView.setAdapter(adapter);
    }
}
