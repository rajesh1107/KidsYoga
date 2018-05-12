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

public class SimpleActivity extends AppCompatActivity implements View.OnClickListener {

    AutoScrollViewPager mViewPager;
    ArrayList<Integer> imageArray;
    ArrayList<Integer> soundsArray;
    SimpleViewPagerAdapterAdapter mSimpleViewPagerAdapterAdapter;
    Audio audio;
    WebView webView;
    ImageView mHome, mNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        setVolumeControlStream(5);

        audio = new Audio(this);

        initialise();
        getImages();
        getSounds();
        setViewPagerAdapter();
        setupGesture();
    }


    private void setViewPagerAdapter() {
        mSimpleViewPagerAdapterAdapter = new SimpleViewPagerAdapterAdapter(SimpleActivity.this, imageArray);
        mViewPager.setAdapter(mSimpleViewPagerAdapterAdapter);
        new Handler().postDelayed(new AudioPlay(0), Utils.AUDIO_PLAY_INTERVAL);
    }

    private void initialise() {
        mViewPager = (AutoScrollViewPager) findViewById(R.id.viewpager);
        mViewPager.setAutoScrollDurationFactor(5);
        mViewPager.setOverScrollMode(1);
        mViewPager.setOffscreenPageLimit(4);

        webView = (WebView) findViewById(R.id.web);
        mViewPager.addOnPageChangeListener(new PageChange());

        mHome = (ImageView) findViewById(R.id.home);
        mHome.setOnClickListener(this);
        mNext = (ImageView) findViewById(R.id.next);
        mNext.setOnClickListener(this);
    }

    private class PageChange implements ViewPager.OnPageChangeListener {

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
//            if (position != imageArray.size() - 1) {
//                new Handler().postDelayed(new AudioPlay(position), Utils.AUDIO_PLAY_INTERVAL);
//            } else {
//                audio.stopAudio();
//            }
        }


        @Override
        public void onPageScrollStateChanged(int state) {
            audio.stopAudio();

            if (state == 0)
                if (mViewPager.getCurrentItem() <= imageArray.size() - 1) {
                    new Handler().postDelayed(new AudioPlay(mViewPager.getCurrentItem()), Utils.AUDIO_PLAY_INTERVAL_SHORT);
                } else {
                    audio.stopAudio();
                }
        }


    }

    class AudioPlay implements Runnable {
        int pos;

        public AudioPlay(int position) {
            pos = position;
        }

        public void run() {
            audio.stopAudio();
            audio.loadAudio(soundsArray.get(pos));
            audio.startAudio();
        }
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

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.home:
                finish();
                AnimUtil.slideFromLeftAnim(SimpleActivity.this);
                break;
            case R.id.next:
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
                break;
        }

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
            Utils.showProgress(SimpleActivity.this, "Loading...");
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            Utils.dismissProgress();

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

    private void getSounds() {

        soundsArray = new ArrayList<>();

        soundsArray.add(R.raw.s1);
        soundsArray.add(R.raw.s2);
        soundsArray.add(R.raw.s3);
        soundsArray.add(R.raw.s4);
        soundsArray.add(R.raw.s5);
        soundsArray.add(R.raw.s6);
        soundsArray.add(R.raw.s7);
        soundsArray.add(R.raw.s8);
        soundsArray.add(R.raw.s9);
        soundsArray.add(R.raw.s10);
        soundsArray.add(R.raw.s11);
        soundsArray.add(R.raw.s12);
        soundsArray.add(R.raw.s13);
        soundsArray.add(R.raw.s14);
        soundsArray.add(R.raw.s15);
        soundsArray.add(R.raw.s16);
        soundsArray.add(R.raw.s17);
        soundsArray.add(R.raw.s18);
        soundsArray.add(R.raw.s19);
        soundsArray.add(R.raw.s20);
        soundsArray.add(R.raw.s21);
        soundsArray.add(R.raw.s22);
        soundsArray.add(R.raw.s23);
        soundsArray.add(R.raw.s24);
        soundsArray.add(R.raw.s25);
        soundsArray.add(R.raw.s26);
        soundsArray.add(R.raw.s27);
        soundsArray.add(R.raw.s28);
        soundsArray.add(R.raw.s29);
        soundsArray.add(R.raw.s30);
        soundsArray.add(R.raw.s31);
        soundsArray.add(R.raw.song);

    }

    @Override
    public void onBackPressed() {

//        Utils.showAlertDialogWithoutIcon(this, Utils.APP_TITLE, "Return to table of contents?");

        super.onBackPressed();
        AnimUtil.slideFromLeftAnim(SimpleActivity.this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        audio.stopAudio();
    }
}
