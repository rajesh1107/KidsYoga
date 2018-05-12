package com.signity.kidsyoga.main;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.signity.kidsyoga.R;
import com.signity.kidsyoga.Utilities.AnimUtil;
import com.signity.kidsyoga.Utilities.Utils;
import com.signity.kidsyoga.adapters.StrikePoseGameAdapter;
import com.signity.kidsyoga.audio.Audio;
import com.signity.kidsyoga.custompager.AutoScrollViewPager;

import java.util.ArrayList;
import java.util.Collections;

public class StrikePoseGameActivity extends AppCompatActivity implements View.OnClickListener {

    AutoScrollViewPager mViewPager;
    ArrayList<Integer> imageArray;
    StrikePoseGameAdapter strikePoseGameAdapter;
    Audio audio;
    ImageView mHome, mNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strike_pose_game);
        setVolumeControlStream(5);

        audio = new Audio(this);
        initialise();
        getImages();
        setViewPagerAdapter();
    }

    private void setViewPagerAdapter() {
        Collections.shuffle(imageArray);
        strikePoseGameAdapter = new StrikePoseGameAdapter(StrikePoseGameActivity.this, imageArray);
        mViewPager.setAdapter(strikePoseGameAdapter);
        new Handler().postDelayed(new AudioPlay(), Utils.AUDIO_PLAY_INTERVAL_SHORT);
//        mViewPager.setCurrentItem(500);
    }

    private void initialise() {
        mViewPager = (AutoScrollViewPager) findViewById(R.id.viewpager);
        mViewPager.setAutoScrollDurationFactor(5);
        mViewPager.setOverScrollMode(1);
        mViewPager.setOffscreenPageLimit(4);

        mViewPager.addOnPageChangeListener(new PageChange());

        mHome = (ImageView) findViewById(R.id.home);
        mHome.setOnClickListener(this);
        mNext = (ImageView) findViewById(R.id.next);
        mNext.setOnClickListener(this);

    }


    public void getImages() {
        imageArray = new ArrayList<>();

        imageArray.add(R.drawable.a1);
        imageArray.add(R.drawable.a3);
        imageArray.add(R.drawable.a5);
        imageArray.add(R.drawable.a6);
        imageArray.add(R.drawable.a7);

        imageArray.add(R.drawable.a10);
        imageArray.add(R.drawable.a11);
        imageArray.add(R.drawable.a12);
        imageArray.add(R.drawable.a13);
        imageArray.add(R.drawable.a14);
        imageArray.add(R.drawable.a15);
        imageArray.add(R.drawable.a16);
        imageArray.add(R.drawable.a17);
        imageArray.add(R.drawable.a18);
        imageArray.add(R.drawable.a19);
        imageArray.add(R.drawable.a20);
        imageArray.add(R.drawable.a21);
        imageArray.add(R.drawable.a22);

    }

    private class PageChange implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            new Handler().postDelayed(new AudioPlay(), Utils.AUDIO_PLAY_INTERVAL_SHORT);
        }


        @Override
        public void onPageScrollStateChanged(int state) {
//            if (state == 0)
//                new Handler().postDelayed(new AudioPlay(), Utils.AUDIO_PLAY_INTERVAL_SHORT);

        }
    }

    class AudioPlay implements Runnable {
        AudioPlay() {
        }

        public void run() {
            audio.stopAudio();
            audio.loadAudio(R.raw.s1);
            audio.startAudio();
        }
    }

    @Override
    public void onBackPressed() {

//        Utils.showAlertDialogWithoutIcon(this, Utils.APP_TITLE, "Return to table of contents?");

        super.onBackPressed();
        AnimUtil.slideFromLeftAnim(StrikePoseGameActivity.this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        audio.stopAudio();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.home:
                finish();
                AnimUtil.slideFromLeftAnim(StrikePoseGameActivity.this);
                break;
            case R.id.next:
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
                break;
        }

    }
}
