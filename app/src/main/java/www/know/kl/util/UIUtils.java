package www.retrofit.com.retrofitrxjavademo.util;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;

import www.retrofit.com.retrofitrxjavademo.app.MyApplication;

public class UIUtils {
    /**
     * 得到上下文
     */
    public static Context getContext() {
        return MyApplication.getContext();
    }

    /**
     * 得到Resource对象
     */
    public static Resources getResources() {
        return getContext().getResources();
    }

    /**
     * 得到string.xml中的字符
     */
    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    /**
     * 得到string.xml中的字符数组
     */
    public static String[] getStringArr(int resId) {
        return getResources().getStringArray(resId);
    }

    /**
     * 得到color.xml中的颜色信息
     */
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    /**
     * 得到包名
     *
     * @return
     */
    public static String getPackageName() {
        return getContext().getPackageName();

    }

    /**
     * @param dip-->px
     * @return
     */
    public static int dp2Px(int dip) {
        //        dp<-->px
        //1. px/dp = density
        //2. px / (ppi/160) = dp;

        float density = UIUtils.getResources().getDisplayMetrics().density;
        int px = (int) (dip * density + .5f);
        return px;
    }

    /**
     * px-->dip
     *
     * @param px
     * @return
     */
    public static int px2Dip(int px) {
        //1. px/dp = density
        float density = UIUtils.getResources().getDisplayMetrics().density;
        int dp = (int) (px / density + .5f);
        return dp;
    }

    public static View getView(View view, int resId) {
        return view.findViewById(resId);
    }
}
