package com.example.myapplication; // Replace with your actual package name

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BrowserLauncherActivity extends AppCompatActivity {

    private EditText urlEditText;
    private Button openBrowserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser_launcher); // Use your layout file name

        urlEditText = findViewById(R.id.urlEditText);
        openBrowserButton = findViewById(R.id.openBrowserButton);

        openBrowserButton.setOnClickListener(v -> {
            String urlString = urlEditText.getText().toString().trim();

            if (urlString.isEmpty()) {
                Toast.makeText(this, R.string.url_empty_error, Toast.LENGTH_SHORT).show();
                return;
            }

            // Add "http://" if missing (basic check)
            if (!urlString.startsWith("http://") && !urlString.startsWith("https://")) {
                urlString = "http://" + urlString;
            }

            // Validate the URL format more strictly (optional but recommended)
            if (!Patterns.WEB_URL.matcher(urlString).matches()) {
                Toast.makeText(this, R.string.invalid_url_error, Toast.LENGTH_SHORT).show();
                return;
            }


            // Try to open the URL
            try {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
                startActivity(browserIntent);
            } catch (ActivityNotFoundException e) {
                // Handle case where no browser application is installed
                Toast.makeText(this, R.string.no_browser_error, Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                // Catch other potential errors during parsing or intent creation
                Toast.makeText(this, R.string.invalid_url_error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}