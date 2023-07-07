package com.uas.lokerapps;

// SettingActivity.java

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SettingActivity extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_PICK = 2;

    private Button logoutButton;
    private BottomNavigationView bottomNavigationView;
    private EditText fullNameEditText;
    private EditText birthDateEditText;
    private EditText educationEditText;
    private EditText interestEditText;
    private Button submitButton;
    private ImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        // Arahkan ke HomeActivity
                        Intent homeIntent = new Intent(SettingActivity.this, HomeActivity.class);
                        startActivity(homeIntent);
                        return true;
                    case R.id.action_setting:
                        // Arahkan ke SettingActivity
                        Intent settingIntent = new Intent(SettingActivity.this, SettingActivity.class);
                        startActivity(settingIntent);
                        return true;
                }

                return false;
            }
        });


        logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Menghapus shared preference
                SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();

                // Kembali ke MainActivity
                Intent intent = new Intent(SettingActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        fullNameEditText = findViewById(R.id.fullNameEditText);
        birthDateEditText = findViewById(R.id.birthDateEditText);
        educationEditText = findViewById(R.id.educationEditText);
        interestEditText = findViewById(R.id.interestEditText);
        submitButton = findViewById(R.id.submitButton);
        profileImage = findViewById(R.id.profileImage);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = fullNameEditText.getText().toString();
                String birthDate = birthDateEditText.getText().toString();
                String education = educationEditText.getText().toString();
                String interest = interestEditText.getText().toString();

                String message = "Nama Lengkap: " + fullName +
                        "\nTanggal Lahir: " + birthDate +
                        "\nPendidikan Terakhir: " + education +
                        "\nHobi: " + interest;

                Toast.makeText(SettingActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void openCamera(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            profileImage.setImageBitmap(imageBitmap);
        }
    }
}
