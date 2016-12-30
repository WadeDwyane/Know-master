package www.retrofit.com.retrofitrxjavademo.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.retrofit.com.retrofitrxjavademo.R;
import www.retrofit.com.retrofitrxjavademo.api.ApiConstants;
import www.retrofit.com.retrofitrxjavademo.mvp.MvpActivity;
import www.retrofit.com.retrofitrxjavademo.mvp.splash.model.LastVersionBean;
import www.retrofit.com.retrofitrxjavademo.mvp.splash.model.StartImageBean;
import www.retrofit.com.retrofitrxjavademo.mvp.splash.presenter.SplashPresenter;
import www.retrofit.com.retrofitrxjavademo.mvp.splash.view.SplashView;
import www.retrofit.com.retrofitrxjavademo.util.AppUtil;
import www.retrofit.com.retrofitrxjavademo.util.IntentUtils;

/**
 * Created by kui.liu on 2016/11/23.
 */

public class SplashActivity extends MvpActivity<SplashPresenter> implements SplashView {

    @Bind(R.id.splash_sdv_pic)
    SimpleDraweeView mSplashSdvPic;
    @Bind(R.id.splash_tv_info)
    TextView         mSplashTvInfo;

    @Override
    protected SplashPresenter createPresenter() {
        return new SplashPresenter(this);
    }

    /*@Override
    public ApiService getApiService() {
        return ApiClient.getApiService(HostType.ZHRB_NEWS);
    }*/

    @Override
    public void getDataSuccess(Object model) {
        if (model instanceof StartImageBean) {

            StartImageBean bean = (StartImageBean) model;
            mSplashTvInfo.setText(bean.getText());
            mSplashSdvPic.setImageURI(bean.getImg());
        } else if (model instanceof LastVersionBean) {
            LastVersionBean lastVersionBean = (LastVersionBean) model;
            int status = lastVersionBean.getStatus();
            if (status == 0) {//最新版本
                go2Main();
            } else {//较老版本
                new AlertDialog.Builder(SplashActivity.this).setTitle("有新版本了,确认更新吗?")//设置对话框标题
                        .setMessage(lastVersionBean.getMsg())//设置显示的内容
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加确定按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//下载最新版本
                                SystemClock.sleep(1000);
                                ProgressBar bar = new ProgressBar(SplashActivity.this);
                                bar.setMax(100);
                                bar.setProgress(2);
                                go2Main();
                            }

                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {//添加返回按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//响应事件
                        go2Main();
                    }
                }).show();//在按键响应事件中显示此对话框
            }
        }
    }

    private void go2Main() {
        IntentUtils.startIntent(this, MainActivity.class);
    }

    @Override
    public void getDataFail(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        mvpPresenter.loadStartImage(ApiConstants.WIDHT_HEIGHT);
        SystemClock.sleep(1000);
        //获取当前版本号.
        String version = AppUtil.getVersion(this);
        mvpPresenter.loadVersionInfo(version);
    }
}
