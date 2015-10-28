package com.example.timepay;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Sahil on 20-06-2015..
 */
public class Webview extends Activity implements View.OnClickListener {
    private WebView webView;
    Button cancel;
    TextView HeaderText;
    String URL,Text;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        initialize();
        Intent intent = getIntent();
        URL = intent.getStringExtra("URL");
        Text = intent.getStringExtra("Text");
        webView.getSettings().setJavaScriptEnabled(true);
        HeaderText.setText(Text);
        webView.loadUrl(URL);
        cancel.setOnClickListener(this);
    }

    private void initialize()
    {
        cancel = (Button)findViewById(R.id.btCancel);
        HeaderText = (TextView)findViewById(R.id.tvHeader);
        webView = (WebView) findViewById(R.id.webview);
    }

    @Override
    public void onClick(View view) {
        super.onBackPressed();
    }
}
