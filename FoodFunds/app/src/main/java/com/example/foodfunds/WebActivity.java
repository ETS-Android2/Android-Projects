package com.example.foodfunds;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivity extends AppCompatActivity {
WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        getSupportActionBar().hide();
        Bundle bundle = getIntent().getExtras();
        webView = findViewById(R.id.appWV);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(bundle.getString("url"));
    }
}