package com.example.myapplication; // Replace with your actual package name

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private TextView welcomeTextView;
    private EditText nameEditText;
    private Button saveExitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        welcomeTextView = findViewById(R.id.welcomeTextView);
        nameEditText = findViewById(R.id.nameEditText);
        saveExitButton = findViewById(R.id.saveExitButton);

        // Get email from incoming Intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(LoginActivity.EXTRA_EMAIL)) {
            String email = intent.getStringExtra(LoginActivity.EXTRA_EMAIL);
            // Set welcome text using string resource with placeholder
            welcomeTextView.setText(getString(R.string.profile_welcome, email));
        } else {
            // Handle case where email is missing (optional)
            welcomeTextView.setText(getString(R.string.profile_welcome, "User")); // Default
        }

        // --- Setup Save and Exit Button Click ---
        saveExitButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString().trim();

            if (name.isEmpty()) {
                // Basic validation
                Toast.makeText(ProfileActivity.this, R.string.empty_name_alert, Toast.LENGTH_SHORT).show();
                // Or show error on EditText: nameEditText.setError(getString(R.string.empty_name_alert));
                return;
            }

            // Create result Intent and add the name
            Intent resultIntent = new Intent();
            resultIntent.putExtra(LoginActivity.EXTRA_NAME, name);

            // Set result and finish
            setResult(Activity.RESULT_OK, resultIntent);
            finish(); // Closes this activity and returns to LoginActivity
        });
    }
}