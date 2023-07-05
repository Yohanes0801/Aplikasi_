package com.uas.lokerapps;

import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
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

        // Buat daftar pekerjaan dan tambahkan item dari JSON
        List<Job> jobList = createJobListFromJson();

        // Inisialisasi adapter dan atur ke RecyclerView
        jobAdapter = new JobAdapter(jobList);
        jobRecyclerView.setAdapter(jobAdapter);
    }

    private List<Job> createJobListFromJson() {
        List<Job> jobList = new ArrayList<>();

        try {
            // Membaca file JSON dari folder assets
            String json = loadJSONFromAsset("jobs.json");

            // Parsing data JSON menjadi objek
            JSONArray jsonArray = new JSONArray(json);

            // Iterasi melalui setiap objek dalam array JSON
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                // Mendapatkan nilai dari setiap kunci JSON
                int logo = getResourceId(jsonObject.getString("logo"), "drawable");
                String position = jsonObject.getString("position");
                String company = jsonObject.getString("company");
                String description = jsonObject.getString("description");

                // Membuat objek Job dan menambahkannya ke daftar
                Job job = new Job(logo, position, company, description);
                jobList.add(job);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jobList;
    }

    private String loadJSONFromAsset(String fileName) {
        String json;
        try {
            // Membaca file JSON dari folder assets
            InputStream inputStream = getAssets().open(fileName);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }

    private int getResourceId(String resourceName, String resourceType) {
        return getResources().getIdentifier(resourceName, resourceType, getPackageName());
    }
}

