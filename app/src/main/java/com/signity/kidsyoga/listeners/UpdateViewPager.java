package com.signity.kidsyoga.listeners;

/**
 * Created by rajesh on 8/6/17.
 */

public class UpdateViewPager {

    public static UpdateViewPager updateViewPager;
    private UpdatePagerInfo listener;

    private UpdateViewPager() {
    }

    public interface UpdatePagerInfo {

        public void updateCallBack(boolean update);

    }

    public static UpdateViewPager getInstance() {
        if (updateViewPager == null) {
            updateViewPager = new UpdateViewPager();
        }
        return updateViewPager;

    }

    public void setListener(UpdatePagerInfo listener) {
        this.listener = listener;

    }

    public void notifyy(boolean status) {
        if (listener != null)
            listener.updateCallBack(status);
    }

}
