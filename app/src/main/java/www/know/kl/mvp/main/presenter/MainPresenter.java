package www.retrofit.com.retrofitrxjavademo.mvp.main.presenter;

import www.retrofit.com.retrofitrxjavademo.api.ApiCallback;
import www.retrofit.com.retrofitrxjavademo.api.ApiClient;
import www.retrofit.com.retrofitrxjavademo.api.ApiService;
import www.retrofit.com.retrofitrxjavademo.api.HostType;
import www.retrofit.com.retrofitrxjavademo.base.BasePresenter;
import www.retrofit.com.retrofitrxjavademo.mvp.main.model.MainNewsBean;
import www.retrofit.com.retrofitrxjavademo.mvp.splash.view.SplashView;
import www.retrofit.com.retrofitrxjavademo.util.LogUtils;

/**
 * Created by kui.liu on 2016/11/28.
 */

public class MainPresenter extends BasePresenter<SplashView> {
    @Override
    public ApiService getApiSerVice() {
        return ApiClient.getApiService(HostType.ZHRB_NEWS);
    }

    public MainPresenter(SplashView view) {
        attachView(view);
    }

    public void loadMainNews(String path) {
        mvpView.showLoading();
        addSubscription(apiService.getLastNews(path),
                new ApiCallback<MainNewsBean>() {
                    @Override
                    public void onSuccess(MainNewsBean model) {
                        LogUtils.d("AAAA", "成功");
                        mvpView.getDataSuccess(model);
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }
                });
    }
}
