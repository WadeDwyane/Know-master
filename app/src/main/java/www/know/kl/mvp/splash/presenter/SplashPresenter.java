package www.retrofit.com.retrofitrxjavademo.mvp.splash.presenter;

import www.retrofit.com.retrofitrxjavademo.api.ApiCallback;
import www.retrofit.com.retrofitrxjavademo.api.ApiClient;
import www.retrofit.com.retrofitrxjavademo.api.ApiService;
import www.retrofit.com.retrofitrxjavademo.api.HostType;
import www.retrofit.com.retrofitrxjavademo.base.BasePresenter;
import www.retrofit.com.retrofitrxjavademo.mvp.splash.model.LastVersionBean;
import www.retrofit.com.retrofitrxjavademo.mvp.splash.model.StartImageBean;
import www.retrofit.com.retrofitrxjavademo.mvp.splash.view.SplashView;

public class SplashPresenter extends BasePresenter<SplashView> {

    public SplashPresenter(SplashView view) {
        attachView(view);
    }

    public void loadStartImage(String pix) {
        mvpView.showLoading();
        addSubscription(apiService.getStartImage(pix),
                new ApiCallback<StartImageBean>() {
                    @Override
                    public void onSuccess(StartImageBean model) {
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

    public void loadVersionInfo(String path) {
        mvpView.showLoading();
        addSubscription(apiService.getLastVersion(path),
                new ApiCallback<LastVersionBean>() {
                    @Override
                    public void onSuccess(LastVersionBean model) {
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

    @Override
    public ApiService getApiSerVice() {
        return ApiClient.getApiService(HostType.ZHRB_NEWS);
    }
}
