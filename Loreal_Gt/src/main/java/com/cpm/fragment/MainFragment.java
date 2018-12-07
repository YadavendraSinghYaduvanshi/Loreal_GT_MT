package com.cpm.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.cpm.Constants.CommonString1;
import com.cpm.Loreal_GT.MainMenuActivity;
import com.cpm.capitalfoods.R;
import com.cpm.download.CompleteDownloadActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    public MainFragment() {
        // Required empty public constructor
    }

    WebView webView;
    ImageView imageView;
    SharedPreferences preferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        imageView = (ImageView) view.findViewById(R.id.img_main);
        webView = (WebView) view.findViewById(R.id.webview);

        //String noticeboard = getArguments().getString(CommonString1.KEY_NOTICE_BOARD);
        //final String quizUrl = getArguments().getString(CommonString1.KEY_QUIZ_URL);
        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        String noticeboard = preferences.getString(CommonString1.KEY_NOTICE_BOARD, "");
        final String quizUrl = preferences.getString(CommonString1.KEY_QUIZ_URL, "");

        final FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        webView.setWebViewClient(new MyWebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);

        if (noticeboard != null && !noticeboard.equalsIgnoreCase("")) {
            webView.loadUrl(noticeboard);
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quizUrl != null) {
                    webView.loadUrl(quizUrl);
                    v.setVisibility(View.INVISIBLE);
                }
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Activity activity = getActivity();
        if (activity instanceof CompleteDownloadActivity) {
            CompleteDownloadActivity myactivity = (CompleteDownloadActivity) activity;
            myactivity.getSupportActionBar().setTitle("Main Menu");
        } else {
            ((MainMenuActivity) getActivity()).getSupportActionBar().setTitle("Main Menu");
        }
    }

   /* private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }*/

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
            if (checkNetIsAvailable()) {
                imageView.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
            } else {
                imageView.setVisibility(View.VISIBLE);
                webView.setVisibility(View.GONE);
            }
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

    public boolean checkNetIsAvailable() {
        try {
            ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
            return isConnected;
        } catch (Exception ex) {
            return false;
        }
    }
}
