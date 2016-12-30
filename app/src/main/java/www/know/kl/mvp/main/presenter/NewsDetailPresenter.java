package www.retrofit.com.retrofitrxjavademo.mvp.main.presenter;

import www.retrofit.com.retrofitrxjavademo.api.ApiCallback;
import www.retrofit.com.retrofitrxjavademo.api.ApiClient;
import www.retrofit.com.retrofitrxjavademo.api.ApiService;
import www.retrofit.com.retrofitrxjavademo.api.HostType;
import www.retrofit.com.retrofitrxjavademo.base.BasePresenter;
import www.retrofit.com.retrofitrxjavademo.mvp.main.model.NewsDetailBean;
import www.retrofit.com.retrofitrxjavademo.mvp.splash.view.SplashView;

/**
 * Created by kui.liu on 2016/12/14.
 */

public class NewsDetailPresenter extends BasePresenter<SplashView> {
    @Override
    public ApiService getApiSerVice() {
        return ApiClient.getApiService(HostType.ZHRB_NEWS);
    }

    public NewsDetailPresenter(SplashView view) {
        
        attachView(view);
    }

    public void loadNewsDetail(String id) {
        mvpView.showLoading();
        addSubscription(apiService.getNewsDeatil(id), new ApiCallback<NewsDetailBean>() {
            @Override
            public void onSuccess(NewsDetailBean model) {
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
