package com.smsforwarder;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    EditText etEmail, etPassword, etForwardTo;
    Switch switchEnable;
    Button btnSave;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = getSharedPreferences("SmsForwarder", MODE_PRIVATE);

        etEmail     = findViewById(R.id.etEmail);
        etPassword  = findViewById(R.id.etPassword);
        etForwardTo = findViewById(R.id.etForwardTo);
        switchEnable = findViewById(R.id.switchEnable);
        btnSave     = findViewById(R.id.btnSave);

        // Load saved settings
        etEmail.setText(prefs.getString("gmail", ""));
        etPassword.setText(prefs.getString("pass", ""));
        etForwardTo.setText(prefs.getString("forward_to", ""));
        switchEnable.setChecked(prefs.getBoolean("enabled", false));

        btnSave.setOnClickListener(v -> {
            String gmail   = etEmail.getText().toString().trim();
            String pass    = etPassword.getText().toString().trim();
            String fwd     = etForwardTo.getText().toString().trim();
            boolean enabled = switchEnable.isChecked();

            if (gmail.isEmpty() || pass.isEmpty() || fwd.isEmpty()) {
                Toast.makeText(this, "সব তথ্য পূরণ করুন!", Toast.LENGTH_SHORT).show();
                return;
            }

            prefs.edit()
                .putString("gmail", gmail)
                .putString("pass", pass)
                .putString("forward_to", fwd)
                .putBoolean("enabled", enabled)
                .apply();

            Toast.makeText(this, "✅ সেটিং সেভ হয়েছে!", Toast.LENGTH_SHORT).show();
        });

        // Request SMS permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS}, 1);
        }
    }
}
