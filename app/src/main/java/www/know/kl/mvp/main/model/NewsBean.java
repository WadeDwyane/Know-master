package www.retrofit.com.retrofitrxjavademo.mvp.main.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kui.liu on 2016/12/1.
 */
public class NewsBean implements Parcelable {
    private MainNewsBean.StoriesBean mStoriesBean;
    private String                   date;

    public NewsBean(Parcel in) {
        mStoriesBean = in.readParcelable(MainNewsBean.StoriesBean.class.getClassLoader());
        date = in.readString();
    }

    public static final Creator<NewsBean> CREATOR = new Creator<NewsBean>() {
        @Override
        public NewsBean createFromParcel(Parcel in) {
            return new NewsBean(in);
        }

        @Override
        public NewsBean[] newArray(int size) {
            return new NewsBean[size];
        }
    };

    public MainNewsBean.StoriesBean getStoriesBean() {
        return mStoriesBean;
    }

    public void setStoriesBean(MainNewsBean.StoriesBean storiesBean) {
        this.mStoriesBean = storiesBean;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mStoriesBean, flags);
        dest.writeString(date);
    }
}
