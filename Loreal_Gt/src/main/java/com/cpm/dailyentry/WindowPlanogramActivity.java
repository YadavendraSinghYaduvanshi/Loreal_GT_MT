package com.cpm.dailyentry;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.cpm.Constants.CommonString;
import com.cpm.capitalfoods.R;

public class WindowPlanogramActivity extends AppCompatActivity {

    WebView webView;

    String window_name="", planogram_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_planogram);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        window_name = getIntent().getStringExtra(CommonString.KEY_WINDOW);
        planogram_url = getIntent().getStringExtra(CommonString.KEY_WINDOW_IMAGE);

        getSupportActionBar().setTitle(window_name);

        webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new MyWebViewClient());

        //String url = CommonString1.URL_Notice_Board;
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);

        if(planogram_url!=null || !planogram_url.equals("")){

            webView.loadUrl(planogram_url);
        }

      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                *//*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*//*
                finish();
            }
        });*/
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
           /* progress.setVisibility(View.GONE);
            WebViewActivity.this.progress.setProgress(100);*/
            //webView.setVisibility(View.VISIBLE);
            super.onPageFinished(view, url);
            view.clearCache(true);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
           /* progress.setVisibility(View.VISIBLE);
            WebViewActivity.this.progress.setProgress(0);*/
            super.onPageStarted(view, url, favicon);
        }


    }

}
