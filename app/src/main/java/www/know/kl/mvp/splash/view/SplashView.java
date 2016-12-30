package www.retrofit.com.retrofitrxjavademo.mvp.splash.view;

public interface SplashView<T> {

    void getDataSuccess(T model);

    void getDataFail(String msg);

    void showLoading();

    void hideLoading();
}
