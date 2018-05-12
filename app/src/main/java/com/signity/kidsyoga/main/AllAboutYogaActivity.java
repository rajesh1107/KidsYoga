package com.signity.kidsyoga.main;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.signity.kidsyoga.R;
import com.signity.kidsyoga.Utilities.AnimUtil;
import com.signity.kidsyoga.Utilities.InterceptTouchListener;
import com.signity.kidsyoga.Utilities.Utils;
import com.signity.kidsyoga.adapters.SimpleViewPagerAdapterAdapter;
import com.signity.kidsyoga.audio.Audio;
import com.signity.kidsyoga.custompager.AutoScrollViewPager;

import java.util.ArrayList;

public class AllAboutYogaActivity extends AppCompatActivity implements View.OnClickListener {

    AutoScrollViewPager mViewPager;
    ArrayList<Integer> imageArray;
    SimpleViewPagerAdapterAdapter mSimpleViewPagerAdapterAdapter;
    WebView webView;
    ImageView mHome, mNext;
    Audio audio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_about_yoga);

        initialise();
        getImages();
        setViewPagerAdapter();
        setupGesture();
    }

    private void setViewPagerAdapter() {
        mSimpleViewPagerAdapterAdapter = new SimpleViewPagerAdapterAdapter(AllAboutYogaActivity.this, imageArray);
        mViewPager.setAdapter(mSimpleViewPagerAdapterAdapter);

    }

    private void initialise() {
        mViewPager = (AutoScrollViewPager) findViewById(R.id.viewpager);
        mViewPager.setAutoScrollDurationFactor(5);
        mViewPager.setOverScrollMode(1);
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.addOnPageChangeListener(new ViewPagerChangeListner());

        webView = (WebView) findViewById(R.id.web);

        mHome = (ImageView) findViewById(R.id.home);
        mHome.setOnClickListener(this);
        mNext = (ImageView) findViewById(R.id.next);
        mNext.setOnClickListener(this);

        audio = new Audio(this);
    }

    class ViewPagerChangeListner implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (position == imageArray.size() - 1) {
                mNext.setVisibility(View.GONE);
            } else {
                mNext.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageSelected(int position) {
            if (position == imageArray.size() - 1) {
                new Handler().postDelayed(new AudioPlay(R.raw.song), Utils.AUDIO_PLAY_INTERVAL_SHORT);
            } else {
                audio.stopAudio();
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    class AudioPlay implements Runnable {
        int mSound;

        public AudioPlay(int sound) {
            mSound = sound;
        }

        public void run() {
            audio.stopAudio();
            audio.loadAudio(mSound);
            audio.startAudio();
        }
    }

    public void getImages() {
        imageArray = new ArrayList<>();

        imageArray.add(R.drawable.a1);
        imageArray.add(R.drawable.a2);
        imageArray.add(R.drawable.a3);
        imageArray.add(R.drawable.a4);
        imageArray.add(R.drawable.a5);
        imageArray.add(R.drawable.a6);
        imageArray.add(R.drawable.a7);
        imageArray.add(R.drawable.a8);
        imageArray.add(R.drawable.a10);
        imageArray.add(R.drawable.a11);
        imageArray.add(R.drawable.a12);
        imageArray.add(R.drawable.a13);
        imageArray.add(R.drawable.a18);
        imageArray.add(R.drawable.a9);
        imageArray.add(R.drawable.a14);
        imageArray.add(R.drawable.a15);
        imageArray.add(R.drawable.a16);
        imageArray.add(R.drawable.a17);
        imageArray.add(R.drawable.a20);
        imageArray.add(R.drawable.a21);
        imageArray.add(R.drawable.a19);
        imageArray.add(R.drawable.a22);
        imageArray.add(R.drawable.a23);
        imageArray.add(R.drawable.a31);
        imageArray.add(R.drawable.a26);
        imageArray.add(R.drawable.a27);
        imageArray.add(R.drawable.a28);
        imageArray.add(R.drawable.a29);
        imageArray.add(R.drawable.a24);
        imageArray.add(R.drawable.a30);
        imageArray.add(R.drawable.a25);
        imageArray.add(R.drawable.bc);
    }


    @Override
    public void onBackPressed() {

//        Utils.showAlertDialogWithoutIcon(this, Utils.APP_TITLE, "Return to table of contents?");

        super.onBackPressed();
        AnimUtil.slideFromLeftAnim(AllAboutYogaActivity.this);
    }

    private void setupGesture() {

        mViewPager.setOnTouchListener(new InterceptTouchListener(new InterceptTouchListener.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                if (position == (imageArray.size() - 1)) {
                    audio.stopAudio();
                    openWebView();
                }
            }
        }));
    }


    private void openWebView() {
        mViewPager.setVisibility(View.GONE);
        webView.setVisibility(View.VISIBLE);
        webView.setWebViewClient(new Callback());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setBuiltInZoomControls(true);

        webView.loadUrl(Utils.WEB_URL);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.home:
                finish();
                AnimUtil.slideFromLeftAnim(AllAboutYogaActivity.this);
                break;
            case R.id.next:
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
                break;
        }

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
            Utils.showProgress(AllAboutYogaActivity.this, "Loading...");
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            Utils.dismissProgress();

        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        audio.stopAudio();
    }
}
