package com.signity.kidsyoga.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.signity.kidsyoga.R;
import com.signity.kidsyoga.Utilities.AnimUtil;

public class IndexActivity extends AppCompatActivity implements View.OnClickListener {


    Animation mButtonFlickAnimation;
    Button mEnterButton;
    Animation mFadeinAnimation;
    Animation mFadeoutAnimation;
    boolean mPaused;
    View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        mView = findViewById(R.id.main);
        mButtonFlickAnimation = AnimationUtils.loadAnimation(this, R.anim.button_flicker);
        mFadeinAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        mFadeoutAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        mEnterButton = (Button) findViewById(R.id.enterBT);
        mEnterButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (!mPaused) {
            mPaused = true;
            Intent intent = new Intent(this, SplashActivity.class);
            intent.putExtra("option", 7);
            v.startAnimation(mButtonFlickAnimation);
            mButtonFlickAnimation.setAnimationListener(new StartActivityAfterAnimation(intent));
//            mView.startAnimation(mFadeoutAnimation);
//            mView.startAnimation(mFadeoutAnimation);
        }
    }

    protected void onResume() {
        super.onResume();
        this.mPaused = false;
//        mView.clearAnimation();
        mEnterButton.setVisibility(View.VISIBLE);
        mEnterButton.clearAnimation();
        mEnterButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.button_slide));
    }

    protected class StartActivityAfterAnimation implements Animation.AnimationListener {
        private Intent mIntent;

        StartActivityAfterAnimation(Intent intent) {
            this.mIntent = intent;
        }

        public void onAnimationEnd(Animation animation) {
            mEnterButton.setVisibility(View.INVISIBLE);
            mEnterButton.clearAnimation();
            startActivity(this.mIntent);
            AnimUtil.slideFromRightAnim(IndexActivity.this);
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
        }
    }
}
