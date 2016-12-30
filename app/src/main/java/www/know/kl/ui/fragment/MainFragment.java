package www.retrofit.com.retrofitrxjavademo.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;
import www.retrofit.com.retrofitrxjavademo.R;
import www.retrofit.com.retrofitrxjavademo.api.ApiConstants;
import www.retrofit.com.retrofitrxjavademo.mvp.MvpFragment;
import www.retrofit.com.retrofitrxjavademo.mvp.main.model.MainNewsBean;
import www.retrofit.com.retrofitrxjavademo.mvp.main.model.NewsBean;
import www.retrofit.com.retrofitrxjavademo.mvp.main.presenter.MainPresenter;
import www.retrofit.com.retrofitrxjavademo.mvp.splash.view.SplashView;
import www.retrofit.com.retrofitrxjavademo.ui.activity.NewsDetailActivity;
import www.retrofit.com.retrofitrxjavademo.ui.adapter.HomeNewsAdapter;
import www.retrofit.com.retrofitrxjavademo.ui.view.LocalImageHolderView;
import www.retrofit.com.retrofitrxjavademo.util.Constant;
import www.retrofit.com.retrofitrxjavademo.util.LogUtils;

/**
 * Created by kui.liu on 2016/11/24.
 */

public class MainFragment extends MvpFragment<MainPresenter> implements OnItemClickListener, SplashView, AdapterView.OnItemClickListener {
    private static final java.lang.String TAG = "MainFragment";
    private ConvenientBanner mConvenientBanner;
    private List<MainNewsBean.TopStoriesBean> mTop_stories = new ArrayList<>();
    private List<NewsBean>                    mStories     = new ArrayList<>();
    private HomeNewsAdapter           mAdapter;
    private StickyListHeadersListView mListView;

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setContentView(R.layout.fragment_main);
        mvpPresenter = new MainPresenter(this);
        mvpPresenter.loadMainNews(ApiConstants.LAST);
        mListView = (StickyListHeadersListView)
                getActivity().findViewById(R.id.main_listview);
        initBanner();
        initListView();
        initListener();
    }

    private void initListener() {
        mListView.setOnItemClickListener(this);
    }

    private void initBanner() {

        //手动New并且添加到ListView Header的例子
        mConvenientBanner = new ConvenientBanner(getContext());
        mConvenientBanner.setMinimumHeight(400);

//        mConvenientBanner = (ConvenientBanner) getActivity().findViewById(R.id.convenientBanner);

        //自定义你的Holder，实现更多复杂的界面，不一定是图片翻页，其他任何控件翻页亦可。
        mConvenientBanner.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, mTop_stories)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                //设置翻页的效果，不需要翻页效果可用不设
                //.setPageTransformer(Single.Transformer.DefaultTransformer);   // 集成特效之后会有白屏现象，新版已经分离，如果要集成特效的例子可以看Demo的点击响应。
                //convenientBanner.setManualPageable(false);//设置不能手动影响
                .setOnItemClickListener(this);
        mListView.addHeaderView(mConvenientBanner);
    }

    private void initListView() {
        mAdapter = new HomeNewsAdapter(getActivity(), mStories);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    // 开始自动翻页
    @Override
    public void onResume() {
        super.onResume();
        mConvenientBanner.startTurning(5000);
    }

    // 停止自动翻页
    @Override
    public void onPause() {
        super.onPause();
        mConvenientBanner.stopTurning();
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void getDataSuccess(Object model) {
        MainNewsBean bean = (MainNewsBean) model;
        mTop_stories.addAll(bean.getTop_stories());

        ArrayList<NewsBean> list = new ArrayList<>();
        for (int i = 0; i < bean.getStories().size(); i++) {
            MainNewsBean.StoriesBean storiesBean = bean.getStories().get(i);
            NewsBean newsBean = new NewsBean(Parcel.obtain());
            newsBean.setStoriesBean(storiesBean);
            newsBean.setDate(bean.getDate());
            list.add(newsBean);
        }
        mConvenientBanner.notifyDataSetChanged();
        mAdapter.restore(list);
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        /*MainActivity activity = (MainActivity) getActivity();
        NewsDetailFragment fragment = new NewsDetailFragment();
        activity.addFragment(android.R.id.content, fragment);*/
        NewsBean newsBean = mStories.get(position - 1);
        LogUtils.d(TAG, "position = " + position + ", date = " + newsBean.getDate() +
                ", id = " + newsBean.getStoriesBean().getId() + ", Title = " + newsBean.getStoriesBean().getTitle());

        Bundle bundle = new Bundle();
        bundle.putParcelable(Constant.NEWSBEAN, newsBean);
        Intent intent = new Intent(getContext(), NewsDetailActivity.class);
        intent.putExtra(Constant.BUNDLE, bundle);
        startActivity(intent);
    }
}
