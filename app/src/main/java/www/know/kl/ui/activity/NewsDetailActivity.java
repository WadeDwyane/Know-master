package www.retrofit.com.retrofitrxjavademo.ui.activity;

import android.os.Bundle;

import www.retrofit.com.retrofitrxjavademo.R;
import www.retrofit.com.retrofitrxjavademo.base.BasePresenter;
import www.retrofit.com.retrofitrxjavademo.mvp.MvpActivity;
import www.retrofit.com.retrofitrxjavademo.ui.fragment.NewsDetailFragment;
import www.retrofit.com.retrofitrxjavademo.util.Constant;

/**
 * Created by kui.liu on 2016/12/5.
 */
public class NewsDetailActivity extends MvpActivity {
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_news_detail);
        Bundle bundle = getIntent().getBundleExtra(Constant.BUNDLE);
        NewsDetailFragment fragment = new NewsDetailFragment();
        fragment.setArguments(bundle);
        addFragment(android.R.id.content, fragment);

    }
}
