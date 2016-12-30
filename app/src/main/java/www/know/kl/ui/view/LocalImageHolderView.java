package www.retrofit.com.retrofitrxjavademo.ui.view;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.facebook.drawee.view.SimpleDraweeView;

import www.retrofit.com.retrofitrxjavademo.R;
import www.retrofit.com.retrofitrxjavademo.mvp.main.model.MainNewsBean;

/**
 * Created by kui.liu on 2016/11/28.
 */
public class LocalImageHolderView implements Holder<MainNewsBean.TopStoriesBean> {
    private SimpleDraweeView mMainDraweeView;
    private TextView         mTvTitle;

    @Override
    public View createView(Context context) {
        View bannerView = LayoutInflater.from(context).inflate(R.layout.main_banner, null);
        mMainDraweeView = (SimpleDraweeView) bannerView.findViewById(R.id.main_banner_draweeview);
        mTvTitle = (TextView) bannerView.findViewById(R.id.main_tv_title);
        return bannerView;
    }

    @Override
    public void UpdateUI(Context context, final int position, MainNewsBean.TopStoriesBean data) {
        mMainDraweeView.setImageURI(Uri.parse(data.getImage()));
        mTvTitle.setText(data.getTitle());
    }
}