package com.example.zakatgoldcalculator;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    TextView tvGithub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        tvGithub = findViewById(R.id.tvGithub);

        tvGithub.setOnClickListener(v -> {
            try {
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://github.com/yourusername/zakatgoldcalculator")
                );
                startActivity(browserIntent);
            } catch (Exception e) {
                Toast.makeText(this,
                        "Cannot open link",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}