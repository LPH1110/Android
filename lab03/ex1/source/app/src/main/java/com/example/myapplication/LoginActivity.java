package com.example.myapplication; // Replace with your actual package name

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    // Keys for Intent Extras
    public static final String EXTRA_EMAIL = "com.example.simpleloginapp.EMAIL";
    public static final String EXTRA_NAME = "com.example.simpleloginapp.NAME";

    // Views
    private TextView loginPromptTextView;
    private EditText emailEditText;
    private Button loginButton;
    private TextView goodbyeTextView;
    private TextView userNameTextView;

    // Activity Result Launcher
    private ActivityResultLauncher<Intent> profileActivityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Find views
        loginPromptTextView = findViewById(R.id.loginPromptTextView);
        emailEditText = findViewById(R.id.emailEditText);
        loginButton = findViewById(R.id.loginButton);
        goodbyeTextView = findViewById(R.id.goodbyeTextView);
        userNameTextView = findViewById(R.id.userNameTextView);

        // --- Setup Activity Result Handling ---
        profileActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null && data.hasExtra(EXTRA_NAME)) {
                            String userName = data.getStringExtra(EXTRA_NAME);
                            updateUiForReturn(userName);
                        }
                    }
                    // Handle other result codes if needed (e.g., RESULT_CANCELED)
                });

        // --- Setup Login Button Click ---
        loginButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();

            if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                // Basic validation
                Toast.makeText(LoginActivity.this, R.string.empty_email_alert, Toast.LENGTH_SHORT).show();
                // Or show error on EditText: emailEditText.setError(getString(R.string.empty_email_alert));
                return;
            }

            // Launch ProfileActivity for result
            Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
            intent.putExtra(EXTRA_EMAIL, email);
            profileActivityResultLauncher.launch(intent);
        });
    }

    // --- Method to Update UI after returning from ProfileActivity ---
    private void updateUiForReturn(String name) {
        // Hide login elements
        loginPromptTextView.setVisibility(View.GONE);
        emailEditText.setVisibility(View.GONE);
        loginButton.setVisibility(View.GONE);

        // Show goodbye elements
        goodbyeTextView.setVisibility(View.VISIBLE);
        userNameTextView.setText(name); // Set the returned name
        userNameTextView.setVisibility(View.VISIBLE);
    }
}