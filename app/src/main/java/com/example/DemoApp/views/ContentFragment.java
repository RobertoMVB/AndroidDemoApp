package com.example.DemoApp.views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.example.DemoApp.R;
import com.example.DemoApp.models.FRAGMENT_ACTIONS;

public class ContentFragment extends Fragment implements View.OnClickListener {

    private static final String WEB_PARAM = "WEB";
    public static final String FRAGMENT_ACTION = "FRAGMENT_ACTION";
    private FRAGMENT_ACTIONS fragment_actions;
    private AlertDialog mAlertDialog;

    private String webParam;
    private WebView mWebView;

    private void setupGoogleContentView(View v) {
        if (webParam != null) {

            mWebView = (WebView) v.findViewById(R.id.webview);
            if (Build.VERSION.SDK_INT >= 19) {
                mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            }
            mWebView.getSettings().setBlockNetworkLoads(false);
            mWebView.loadUrl(webParam);
            WebSettings webSettings = mWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            mWebView.setWebViewClient(new WebViewClient());
        }
    }

    private void setupButtonsContentView(View v) {
        Button toastButton = (Button) v.findViewById(R.id.button_toast);
        toastButton.setOnClickListener(this);

        Button alertButton = (Button) v.findViewById(R.id.button_alert);
        toastButton.setOnClickListener(this);

        mAlertDialog = new AlertDialog.Builder(v.getContext()).create();
        mAlertDialog.setTitle("Demo App Alert");
        mAlertDialog.setMessage("Alert message to be shown");
        mAlertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
    }

    private void setupKotlinProjectsContentView(View v) {

    }

    public ContentFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            fragment_actions = (FRAGMENT_ACTIONS) getArguments().getSerializable(FRAGMENT_ACTION);
            webParam = getArguments().getString(WEB_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = null;
        if (fragment_actions != null) {
            switch (fragment_actions){
                case OPEN_GOOGLE:
                    v = inflater.inflate(R.layout.fragment_web, container, false);
                    setupGoogleContentView(v);
                    break;
                case OPEN_BUTTONS_VIEW:
                    v = inflater.inflate(R.layout.fragment_buttons, container, false);
                    setupButtonsContentView(v);
                    break;
                case OPEN_KOTLIN_PROJECTS:
                    setupKotlinProjectsContentView(v);
                    break;
            }
        }

        if (v == null) {
            return inflater.inflate(R.layout.fragment_welcome, container, false);
        }

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_alert:
                mAlertDialog.show();
                break;
            case R.id.button_toast:
                Toast.makeText(getActivity(), R.string.app_name,
                        Toast.LENGTH_SHORT).show();
                break;
        }
    }
}