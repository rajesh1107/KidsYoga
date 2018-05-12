package com.signity.kidsyoga.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.signity.kidsyoga.R;
import com.signity.kidsyoga.main.AutoActivity;

import java.util.ArrayList;

/**
 * Created by rajesh on 8/6/17.
 */

public class AutoPageAdapter extends PagerAdapter {

    private LayoutInflater inflater;
    ArrayList<Integer> mImages = new ArrayList<>();
    Context mContext;
    boolean checkFlag = false;

    public AutoPageAdapter(Context mContext, ArrayList<Integer> imageArray) {

        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mContext = mContext;
        this.mImages = imageArray;
        checkFlag = false;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return mImages.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.simple_view_pager_row, view, false);
        ImageView mImageview = (ImageView) imageLayout.findViewById(R.id.pagerImageView);


        ImageLoader.getInstance().displayImage("drawable://" + mImages.get(position), mImageview, getImageDisplayOptions(), new ImageLoadingListener() {
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

        mImageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkFlag) {
                    AutoActivity autoActivity = (AutoActivity) mContext;
                    autoActivity.openWebView();
                }

            }
        });


        view.addView(imageLayout, 0);
        return imageLayout;
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    public void updateCHeckFlag(boolean value) {
        checkFlag = value;
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

}
