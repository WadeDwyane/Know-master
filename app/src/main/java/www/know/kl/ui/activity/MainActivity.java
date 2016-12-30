package www.retrofit.com.retrofitrxjavademo.ui.activity;

import android.os.Bundle;

import www.retrofit.com.retrofitrxjavademo.R;
import www.retrofit.com.retrofitrxjavademo.base.BasePresenter;
import www.retrofit.com.retrofitrxjavademo.mvp.MvpActivity;
import www.retrofit.com.retrofitrxjavademo.ui.fragment.MainFragment;

/**
 * Created by kui.liu on 2016/11/24.
 */
public class MainActivity extends MvpActivity {

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainFragment fragment = new MainFragment();
        addFragment(android.R.id.content, fragment);
    }
}
