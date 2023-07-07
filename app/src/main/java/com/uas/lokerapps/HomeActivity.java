package com.uas.lokerapps;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) item -> {
            switch (item.getItemId()) {
                case R.id.action_home:
                    // Arahkan ke HomeActivity
                    Intent homeIntent = new Intent(HomeActivity.this, HomeActivity.class);
                    startActivity(homeIntent);
                    return true;
                case R.id.action_setting:
                    // Arahkan ke SettingActivity
                    Intent settingIntent = new Intent(HomeActivity.this, SettingActivity.class);
                    startActivity(settingIntent);
                    return true;
            }

            return false;
        });

        RecyclerView jobRecyclerView = findViewById(R.id.jobRecyclerView);

        // Mengatur layout manager untuk RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        jobRecyclerView.setLayoutManager(layoutManager);

        // Buat daftar pekerjaan dan tambahkan item dari JSON
        List<Job> jobList = createJobListFromJson();

        // Inisialisasi adapter dan atur ke RecyclerView
        JobAdapter jobAdapter = new JobAdapter(jobList);
        jobRecyclerView.setAdapter(jobAdapter);
    }

    private List<Job> createJobListFromJson() {
        List<Job> jobList = new ArrayList<>();

        try {
            // Membaca file JSON dari folder assets
            String json = loadJSONFromAsset();

            // Parsing data JSON menjadi objek
            JSONArray jsonArray = new JSONArray(json);

            // Iterasi melalui setiap objek dalam array JSON
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                // Mendapatkan nilai dari setiap kunci JSON
                int logo = getResourceId(jsonObject.getString("logo"));
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

    private String loadJSONFromAsset() {
        String json;
        try {
            // Membaca file JSON dari folder assets
            InputStream inputStream = getAssets().open("jobs.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }

    @SuppressLint("DiscouragedApi")
    private int getResourceId(String resourceName) {
        return getResources().getIdentifier(resourceName, "drawable", getPackageName());
    }

    @Override
    public void onBackPressed() {
        // Menutup aplikasi dan memindahkan ke latar belakang
        moveTaskToBack(true);
    }

}

