package com.signity.kidsyoga.Utilities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.util.Log;

/**
 * Created by rajesh on 5/6/17.
 */

public class Utils {

    public static String APP_TITLE = "Kids Yoga";
    public static String WEB_URL = "http://www.yogishanti.com";
    public static int AUDIO_PLAY_INTERVAL = 1000;
    public static int AUDIO_PLAY_INTERVAL_SHORT = 500;

    public static void showAlertDialogWithoutIcon(final Context context, String title,
                                                  String message) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        // set title
        alertDialogBuilder.setTitle(title);

        // set dialog message
        alertDialogBuilder.setMessage(message).setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity
                        Activity activity = (Activity) context;
                        activity.finish();
                        dialog.cancel();

                    }
                });

        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }


    static ProgressDialog mDialog;

    public static void showProgress(Context context, String title) {
        try {
            if (mDialog == null) {
                mDialog = ProgressDialog.show(context, "", title, false, false);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void dismissProgress() {
        if (mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }
    }


    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static Typeface setCustomFont(Context ctx, String font) {
        Typeface tf = null;
        try {
            tf = FontUtil.getTypeface(ctx, font);

        } catch (Exception e) {
            Log.e("KIDS YOGA", "Could not get typeface: " + e.getMessage());
            return tf;
        }

        return tf;
    }
}
