package www.retrofit.com.retrofitrxjavademo.ui.fragment;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.facebook.drawee.view.SimpleDraweeView;
import com.github.clans.fab.FloatingActionButton;

import java.util.List;

import butterknife.ButterKnife;
import www.retrofit.com.retrofitrxjavademo.R;
import www.retrofit.com.retrofitrxjavademo.mvp.MvpFragment;
import www.retrofit.com.retrofitrxjavademo.mvp.main.model.NewsBean;
import www.retrofit.com.retrofitrxjavademo.mvp.main.model.NewsDetailBean;
import www.retrofit.com.retrofitrxjavademo.mvp.main.presenter.NewsDetailPresenter;
import www.retrofit.com.retrofitrxjavademo.mvp.splash.view.SplashView;
import www.retrofit.com.retrofitrxjavademo.ui.activity.NewsDetailActivity;
import www.retrofit.com.retrofitrxjavademo.util.Constant;
import www.retrofit.com.retrofitrxjavademo.util.LogUtils;
import www.retrofit.com.retrofitrxjavademo.util.ToastUtil;
import www.retrofit.com.retrofitrxjavademo.util.WebUtil;

/**
 * Created by kui.liu on 2016/12/5.
 */
public class NewsDetailFragment extends MvpFragment<NewsDetailPresenter>
        implements SplashView, View.OnClickListener {

    private static final String TAG = "NewsDetailFragment";
    WebView mWebView;
    private int                     mId;
    private SimpleDraweeView        mSimpleDraweeView;
    private CollapsingToolbarLayout mCollapsingToolbar;
    private NewsDetailActivity      mActivity;
    private FloatingActionButton    mShareQQbtn;
    private FloatingActionButton    mShareWebtn;
    private FloatingActionButton    mShareWeZonebtn;

    @Override
    protected NewsDetailPresenter createPresenter() {
        mvpPresenter = new NewsDetailPresenter(this);
        return mvpPresenter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (NewsDetailActivity) getActivity();
        mActivity.setContentView(R.layout.fragment_news_detail);
        mWebView = (WebView) mActivity.findViewById(R.id.news_webview_detail);
        final Toolbar toolbar = (Toolbar) mActivity.findViewById(R.id.toolbar);
        mActivity.setSupportActionBar(toolbar);
        mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mCollapsingToolbar = (CollapsingToolbarLayout) mActivity.findViewById(R.id.collapsing_toolbar);
        mSimpleDraweeView
                = (SimpleDraweeView) mActivity.findViewById(R.id.news_spv_detail);

        mShareQQbtn = (FloatingActionButton) mActivity.findViewById(R.id.news_fab_share_qqfriend);
        mShareWebtn = (FloatingActionButton) mActivity.findViewById(R.id.news_fab_share_wechat);
        mShareWeZonebtn = (FloatingActionButton) mActivity.findViewById(R.id.news_fab_share_wechatzone);


        NewsBean newsBean = getArguments().getParcelable(Constant.NEWSBEAN);
        mId = newsBean.getStoriesBean().getId();

        //        initWebView();//初始化WebView
        initWebSettings();//初始化WebSettings
        initWebViewClient();//初始化WebViewClient
        initWebChromeClient();//初始化WebChromeClient
        initData();
        initListener();
    }

    private void initListener() {
        mShareQQbtn.setOnClickListener(this);
        mShareWebtn.setOnClickListener(this);
        mShareWeZonebtn.setOnClickListener(this);
    }

    private void initData() {
        mvpPresenter = new NewsDetailPresenter(this);
        mvpPresenter.loadNewsDetail(mId + "");
    }

    private void initWebChromeClient() {
        mWebView.setWebChromeClient(new WebChromeClient() {
            private Bitmap mDefaultVideoPoster;

            //默认的视频展示图
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }

            @Override
            public Bitmap getDefaultVideoPoster() {
                if (mDefaultVideoPoster == null) {
                    mDefaultVideoPoster = BitmapFactory.decodeResource(
                            getResources(), R.mipmap.ic_launcher);
                    return mDefaultVideoPoster;
                }
                return super.getDefaultVideoPoster();
            }
        });
    }

    private void initWebViewClient() {
        mWebView.setWebViewClient(new WebViewClient() {
            //页面开始加载时
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            //页面完成加载时
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            //是否在WebView内加载新页面
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.toString());
                return true;
            }

            //网络错误时回调的方法
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                /**
                 * 在这里写网络错误时的逻辑,比如显示一个错误页面
                 */
            }

            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);
            }
        });
    }

    private void initWebSettings() {
        WebSettings settings = mWebView.getSettings();
        mWebView.requestFocusFromTouch(); //支持获取手势焦点
        settings.setJavaScriptEnabled(true); //支持JS
        settings.setPluginState(WebSettings.PluginState.ON); //支持插件
        settings.setUseWideViewPort(false);//设置适应屏幕
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(false); //支持缩放
        settings.setDisplayZoomControls(false); //隐藏原生的缩放控件
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//支持内容重新布局
        settings.supportMultipleWindows();
        settings.setSupportMultipleWindows(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setAppCacheEnabled(true);
        settings.setAppCachePath(mWebView.getContext().getCacheDir().getAbsolutePath()); //设置可访问文件
        settings.setAllowFileAccess(true); //当webview调用requestFocus时为webview设置节点
        settings.setNeedInitialFocus(true); //支持自动加载图片
        if (Build.VERSION.SDK_INT >= 19) {
            settings.setLoadsImagesAutomatically(true);
        } else {
            settings.setLoadsImagesAutomatically(false);
        }
        settings.setNeedInitialFocus(true); //设置编码格式
        settings.setDefaultTextEncodingName("UTF-8");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void getDataSuccess(Object model) {
        NewsDetailBean bean = (NewsDetailBean) model;
        String url = bean.getShare_url();
        String image = bean.getImage();
        String image_source = bean.getImage_source();
        boolean isEmpty = TextUtils.isEmpty(bean.getBody());
        String mBody = bean.getBody();
        List<String> scc = bean.getCss();
        mSimpleDraweeView.setImageURI(image);
        mCollapsingToolbar.setTitle(image_source);
        LogUtils.d(TAG, " imageURL = " + image + ", image_source = " + image_source);

        if (isEmpty) {
            mWebView.loadUrl(url);
        } else {
            String data = WebUtil.buildHtmlWithCss(mBody, scc, false);
            mWebView.loadDataWithBaseURL(WebUtil.BASE_URL, data,
                    WebUtil.MIME_TYPE, WebUtil.ENCODING, WebUtil.FAIL_URL);
        }


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
    public void onDestroy() {
        super.onDestroy();
        //防止webview内存泄露
        if (mWebView != null) {
            ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.news_fab_share_wechatzone:
                ToastUtil.showShort(mActivity.getResources().getString(R.string.share_friend_zone));
                break;
            case R.id.news_fab_share_qqfriend:
                ToastUtil.showShort(mActivity.getResources().getString(R.string.share_qq));
                break;
            case R.id.news_fab_share_wechat:
                ToastUtil.showShort(mActivity.getResources().getString(R.string.share_weChat));
                break;
        }
    }
}
