package com.signity.kidsyoga.audio;

import android.content.Context;
import android.media.MediaPlayer;

import com.signity.kidsyoga.listeners.UpdateViewPager;

/**
 * Created by rajesh on 8/6/17.
 */

public class Audio {

    MediaPlayer mp;
    Context mContext;
    int mAudio;


    public Audio(Context context) {

        mContext = context;

    }

    public void stopAudio() {
        try {
            if (mp != null) {
                mp.stop();
                mp.reset();
                mp.release();
                mp = null;
            }
        } catch (Exception e) {
        }
    }


    public void loadAudio(int audio) {
        mAudio = audio;
        mp = MediaPlayer.create(mContext, mAudio);
        mp.setOnCompletionListener(new AudioListner());

    }

    public void startAudio() {
        mp.seekTo(0);
        mp.start();
    }

    class AudioListner implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mp) {
            UpdateViewPager.getInstance().notifyy(true);
        }
    }
}
