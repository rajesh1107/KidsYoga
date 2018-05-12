package com.signity.kidsyoga.Utilities;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.signity.kidsyoga.custompager.AutoScrollViewPager;


/**
 * Created by dev on 12/5/17.
 */

public class InterceptTouchListener implements View.OnTouchListener {
    private boolean enableAutoScroll;
    private GestureDetector detector;
    private OnItemClickListener listener;
    private float touchX;
    private float touchY;
    private int touchSlop = 5;
    private boolean isInSwipeMode;
    private boolean shouldSwipe;

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    public InterceptTouchListener() {
        listener = null;
        enableAutoScroll = false;
    }

    public InterceptTouchListener(boolean enableAutoScroll) {
        listener = null;
        this.enableAutoScroll = enableAutoScroll;
    }

    public InterceptTouchListener(OnItemClickListener l) {
        listener = l;
        enableAutoScroll = false;
    }

    public InterceptTouchListener(OnItemClickListener l, boolean enableAutoScroll) {
        listener = l;
        this.enableAutoScroll = enableAutoScroll;
    }

    public boolean isEnableAutoScroll() {
        return enableAutoScroll;
    }

    public void setEnableAutoScroll(boolean enableAutoScroll) {
        this.enableAutoScroll = enableAutoScroll;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (detector == null)
            detector = new GestureDetector(v.getContext(), new DetectorListener(v));
        detector.onTouchEvent(event);
        Log.e("motion event", "is " + event.getAction());
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isInSwipeMode = false;
                shouldSwipe = true;
                touchX = event.getX();
                touchY = event.getY();
                if (v instanceof AutoScrollViewPager)
                    ((AutoScrollViewPager) v).requestDisallowInterceptTouchEvent(true);
                else
                if (v instanceof RecyclerView)
                    ((RecyclerView) v).requestDisallowInterceptTouchEvent(true);
                v.getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = (int) (event.getX() - touchX);
                int deltaY = (int) (event.getY() - touchY);
                if (!isInSwipeMode && (Math.abs(deltaX) > touchSlop || Math.abs(deltaY) > touchSlop)) {
                    isInSwipeMode = true;
                    if (Math.abs(deltaX) > touchSlop) {
                        shouldSwipe = true;
                        if (v instanceof AutoScrollViewPager && enableAutoScroll)
                            ((AutoScrollViewPager) v).stopAutoScroll();
                    } else {
                        shouldSwipe = false;
                    }
                }
                if (isInSwipeMode) {
                    if (v instanceof AutoScrollViewPager)
                        ((AutoScrollViewPager) v).requestDisallowInterceptTouchEvent(shouldSwipe);
                    else
                    if (v instanceof RecyclerView)
                        ((RecyclerView) v).requestDisallowInterceptTouchEvent(shouldSwipe);

                    v.getParent().requestDisallowInterceptTouchEvent(shouldSwipe);
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                isInSwipeMode = false;
                shouldSwipe = false;
                if (v instanceof AutoScrollViewPager && enableAutoScroll)
//                    ((AutoScrollViewPager) v).startAutoScroll(Constants.PAGER_SCROLL_DELAY);
                if (v instanceof AutoScrollViewPager)
                    ((AutoScrollViewPager) v).requestDisallowInterceptTouchEvent(false);
                else if (v instanceof RecyclerView)
                    ((RecyclerView) v).requestDisallowInterceptTouchEvent(false);
                v.getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }
        return false;
    }

    private class DetectorListener extends GestureDetector.SimpleOnGestureListener {
        private View v;

        public DetectorListener(View v) {
            this.v = v;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            if (listener == null)
                return false;
            if (v instanceof AutoScrollViewPager) {
                int pos = ((AutoScrollViewPager) v).getCurrentItem();
                if (pos != -1)
                    listener.onItemClick(v, pos);
            } else if (v instanceof RecyclerView) {
                int pos = ((RecyclerView) v).getChildAdapterPosition(((RecyclerView) v).findChildViewUnder(e.getX(), e.getY()));
                if (pos != -1)
                    listener.onItemClick(v, pos);
            }
            return true;
        }
    }
}
