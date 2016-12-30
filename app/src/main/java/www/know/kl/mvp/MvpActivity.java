package www.retrofit.com.retrofitrxjavademo.mvp;

import android.os.Bundle;

import www.retrofit.com.retrofitrxjavademo.base.BaseActivity;
import www.retrofit.com.retrofitrxjavademo.base.BasePresenter;

public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity {
    protected P mvpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mvpPresenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }
}
