package com.signity.kidsyoga.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.signity.kidsyoga.R;
import com.signity.kidsyoga.Utilities.AnimUtil;

public class SplashActivity extends AppCompatActivity {

    int option = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        option = getIntent().getExtras().getInt("option");

        switch (option) {
            case (byte) 1:
                setContentView(R.layout.splash2);
                new Handler().postDelayed(new AllAboutYoga(), 3000);
                break;
            case (byte) 2:
                setContentView(R.layout.splash2);
                new Handler().postDelayed(new Sequence(), 3000);
                break;
            case (byte) 3:
                setContentView(R.layout.splash2);
                new Handler().postDelayed(new Result(), 3000);
                break;
            case (byte) 4:
                setContentView(R.layout.splash2);
                new Handler().postDelayed(new Simple(), 3000);
                break;
            case (byte) 5:
                setContentView(R.layout.splash1);
                new Handler().postDelayed(new Auto(), 2500);
                break;
            case (byte) 6:
                setContentView(R.layout.splash1);
                new Handler().postDelayed(new StrikePoseGame(), 1500);
                break;
            case (byte) 7:
                setContentView(R.layout.splash1);
                new Handler().postDelayed(new TOC(), 2500);
                break;
            default:
        }
    }


    class AllAboutYoga implements Runnable {
        AllAboutYoga() {
        }

        public void run() {
            startActivity(new Intent(SplashActivity.this, AllAboutYogaActivity.class));
            finish();
        }
    }

    class Sequence implements Runnable {
        Sequence() {
        }

        public void run() {
            startActivity(new Intent(SplashActivity.this, SequenceActivity.class));
            finish();
        }
    }

    class Result implements Runnable {
        Result() {
        }

        public void run() {
            startActivity(new Intent(SplashActivity.this, ResultActivity.class));
            SplashActivity.this.finish();
        }
    }

    class Simple implements Runnable {
        Simple() {
        }

        public void run() {
            startActivity(new Intent(SplashActivity.this, SimpleActivity.class));
            finish();
        }
    }

    class Auto implements Runnable {
        Auto() {
        }

        public void run() {
            startActivity(new Intent(SplashActivity.this, AutoActivity.class));
            SplashActivity.this.finish();
        }
    }

    class StrikePoseGame implements Runnable {
        StrikePoseGame() {
        }

        public void run() {
            startActivity(new Intent(SplashActivity.this, StrikePoseGameActivity.class));
            finish();
        }
    }

    class TOC implements Runnable {
        TOC() {
        }

        public void run() {
            startActivity(new Intent(SplashActivity.this, TOCActivity.class));
            AnimUtil.slideFromRightAnim(SplashActivity.this);
            finish();
        }
    }

}
