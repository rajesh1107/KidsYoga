package com.signity.kidsyoga.main;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.signity.kidsyoga.R;
import com.signity.kidsyoga.Utilities.AnimUtil;
import com.signity.kidsyoga.Utilities.Utils;

import java.util.ArrayList;

public class AboutUs extends AppCompatActivity implements View.OnClickListener {

    ImageView imageView;
    WebView webView;
    ArrayList d = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        imageView = (ImageView) findViewById(R.id.pagerImageView);
        webView = (WebView) findViewById(R.id.web);
        d.add("a");
        d.add(new Integer(100));
        imageView.setOnClickListener(this);

        ImageLoader.getInstance().displayImage("drawable://" + R.drawable.bc, imageView, getImageDisplayOptions(), new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {

            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                Log.v("", "");
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                Log.v("", "");
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.pagerImageView:

                openWebView();
                break;

            case R.id.web:

                break;
        }
    }

    private void openWebView() {
        imageView.setVisibility(View.GONE);
        webView.setVisibility(View.VISIBLE);
        webView.setWebViewClient(new Callback());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setBuiltInZoomControls(true);

        webView.loadUrl(Utils.WEB_URL);

    }


    private class Callback extends WebViewClient {


        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onLoadResource(WebView view, String url) {

        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            showProgress("Loading...");
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            dismissProgress();

        }
    }

    ProgressDialog mDialog;

    public void showProgress(String title) {
        try {
            if (mDialog == null) {
                mDialog = ProgressDialog.show(this, "", title, false, false);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dismissProgress() {
        if (mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }
    }

    DisplayImageOptions.Builder displayImageOptions;

    public DisplayImageOptions getImageDisplayOptions() {
        if (displayImageOptions == null) {
            displayImageOptions = new DisplayImageOptions.Builder();
        }
//        displayImageOptions.showImageOnLoading(R.drawable.lfty_you_tube_plc);
//        displayImageOptions.showImageOnFail(R.drawable.lfty_you_tube_plc);
        displayImageOptions.resetViewBeforeLoading(true);
        displayImageOptions.cacheOnDisk(true);
        displayImageOptions.cacheInMemory(true);
        displayImageOptions.bitmapConfig(Bitmap.Config.RGB_565);
        displayImageOptions.considerExifParams(true);
        return displayImageOptions.build();


    }

    @Override
    public void onBackPressed() {

//        if (webView.canGoBack()) {
//            webView.goBack();
//        } else {
//            Utils.showAlertDialogWithoutIcon(this, Utils.APP_TITLE, "Return to table of contents?");
//        }

//        Utils.showAlertDialogWithoutIcon(this, Utils.APP_TITLE, "Return to table of contents?");

        super.onBackPressed();
        AnimUtil.slideFromLeftAnim(AboutUs.this);
    }
}
