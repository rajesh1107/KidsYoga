package com.signity.kidsyoga.Utilities;

import android.app.Activity;

import com.signity.kidsyoga.R;


public class AnimUtil {

    public static void slideFromLeftAnim(Activity context) {
        context.overridePendingTransition(R.anim.left_to_center_slide,
                R.anim.center_to_right_slide);
    }

    public static void slideFromRightAnim(Activity context) {
        context.overridePendingTransition(R.anim.right_to_center_slide,
                R.anim.center_to_left_slide);
    }

}
