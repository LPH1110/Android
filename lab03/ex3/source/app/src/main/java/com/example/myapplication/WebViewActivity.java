package com.example.myapplication; // Thay bằng package của bạn

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView = findViewById(R.id.webView);


        webView.getSettings().setJavaScriptEnabled(true);


        webView.setWebViewClient(new WebViewClient());


        Intent intent = getIntent();
        if (intent != null && intent.getAction() != null && intent.getAction().equals(Intent.ACTION_VIEW)) {
            Uri dataUri = intent.getData();
            if (dataUri != null) {
                String url = dataUri.toString();
                setTitle(url);
                webView.loadUrl(url);
            } else {
                handleError("Invalid URL data received.");
            }
        } else {
            handleError("Activity started without a valid VIEW intent.");
        }
    }


    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    private void handleError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}