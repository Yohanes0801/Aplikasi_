package com.uas.lokerapps;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView jobRecyclerView;
    private JobAdapter jobAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        jobRecyclerView = findViewById(R.id.jobRecyclerView);

        // Mengatur layout manager untuk RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        jobRecyclerView.setLayoutManager(layoutManager);

        // Buat daftar pekerjaan dan tambahkan item
        List<Job> jobList = createJobList();

        // Inisialisasi adapter dan atur ke RecyclerView
        jobAdapter = new JobAdapter(jobList);
        jobRecyclerView.setAdapter(jobAdapter);
    }

    private List<Job> createJobList() {
        List<Job> jobList = new ArrayList<>();

        // Tambahkan item pekerjaan ke daftar
        jobList.add(new Job(R.drawable.logo_mayora, "Software Engineer", "Google", "Lorem ipsum dolor sit amet."));
        jobList.add(new Job(R.drawable.logo_mayora, "Product Manager", "Facebook", "Lorem ipsum dolor sit amet."));
        jobList.add(new Job(R.drawable.logo_mayora, "UI/UX Designer", "Apple", "Lorem ipsum dolor sit amet."));

        // Tambahkan lebih banyak item pekerjaan sesuai kebutuhan

        return jobList;
    }
}
