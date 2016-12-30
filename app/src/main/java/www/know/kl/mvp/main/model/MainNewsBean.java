package www.retrofit.com.retrofitrxjavademo.mvp.main.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by kui.liu on 2016/11/28.
 */

public class MainNewsBean implements Parcelable {

    /**
     * date : 20161128
     * stories : [{"ga_prefix":"112810","id":9015541,"images":["http://pic4.zhimg.com/f5a52210676e207598cd750539629847.jpg"],"title":"特朗普一直在抱怨，中国到底抢了美国多少就业岗位？","type":0},{"ga_prefix":"112809","id":9013684,"images":["http://pic2.zhimg.com/4ab0f5aa857990245fc652804ee1cc05.jpg"],"title":"变形金刚是很厉害，但不能说他们是「超级英雄」","type":0},{"ga_prefix":"112808","id":9010027,"images":["http://pic3.zhimg.com/0fa64e9df46ff4b390af7c9bfe7d3fea.jpg"],"title":"等我开会儿小差，就会更有灵感的","type":0},{"ga_prefix":"112807","id":9015499,"images":["http://pic3.zhimg.com/e28298f6f2e9ce35ab294ee2c54489ea.jpg"],"title":"一天会跑三个城市的电影路演，主创人员都要做什么？","type":0},{"ga_prefix":"112807","id":9017011,"images":["http://pic3.zhimg.com/b9ee7b139aaff079fd371ddaae1490da.jpg"],"title":"如何理解「北大四成新生认为人生没有意义」？","type":0},{"ga_prefix":"112807","id":9016757,"images":["http://pic1.zhimg.com/0b3bd5e6a670f23857efcdfc209f57c0.jpg"],"title":"别被「雾霾中发现耐药菌」吓到，先来看看原文怎么说","type":0},{"ga_prefix":"112807","id":9017121,"images":["http://pic4.zhimg.com/51317ccbc2affe5db3232b7fa1b52eb3.jpg"],"title":"读读日报 24 小时热门 TOP 5 · 支付宝的大尺度写真","type":0},{"ga_prefix":"112806","id":9013816,"images":["http://pic2.zhimg.com/8cffef7fe39b9145f64554d9fc864d25.jpg"],"title":"瞎扯 · 如何正确地吐槽","type":0}]
     * top_stories : [{"ga_prefix":"112807","id":9017011,"image":"http://pic2.zhimg.com/3a49f65c08e83baf537de924b5c2c5d9.jpg","title":"如何理解「北大四成新生认为人生没有意义」？","type":0},{"ga_prefix":"112807","id":9015499,"image":"http://pic1.zhimg.com/4806e37fa963efa9f2dcdee5831be46c.jpg","title":"一天会跑三个城市的电影路演，主创人员都要做什么？","type":0},{"ga_prefix":"112807","id":9017121,"image":"http://pic2.zhimg.com/80ce9fe9226aa15a59367233a7803991.jpg","title":"读读日报 24 小时热门 TOP 5 · 支付宝的大尺度写真","type":0},{"ga_prefix":"112807","id":9016757,"image":"http://pic4.zhimg.com/179a6e8eaa091639ca80bf17499896bf.jpg","title":"别被「雾霾中发现耐药菌」吓到，先来看看原文怎么说","type":0},{"ga_prefix":"112717","id":9016162,"image":"http://pic3.zhimg.com/a88ae13f43e085239b7d27ab9296730a.jpg","title":"知乎好问题 · 街头摄影有哪些小技巧？","type":0}]
     */

    private String               date;
    private List<StoriesBean>    stories;
    private List<TopStoriesBean> top_stories;


    protected MainNewsBean(Parcel in) {
        date = in.readString();
        stories = in.createTypedArrayList(StoriesBean.CREATOR);
        top_stories = in.createTypedArrayList(TopStoriesBean.CREATOR);
    }

    public static final Creator<MainNewsBean> CREATOR = new Creator<MainNewsBean>() {
        @Override
        public MainNewsBean createFromParcel(Parcel in) {
            return new MainNewsBean(in);
        }

        @Override
        public MainNewsBean[] newArray(int size) {
            return new MainNewsBean[size];
        }
    };

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeTypedList(stories);
        dest.writeTypedList(top_stories);
    }


    public static class StoriesBean implements Parcelable {
        /**
         * ga_prefix : 112810
         * id : 9015541
         * images : ["http://pic4.zhimg.com/f5a52210676e207598cd750539629847.jpg"]
         * title : 特朗普一直在抱怨，中国到底抢了美国多少就业岗位？
         * type : 0
         */

        private String       ga_prefix;
        private int          id;
        private String       title;
        private int          type;
        private List<String> images;

        protected StoriesBean(Parcel in) {
            ga_prefix = in.readString();
            id = in.readInt();
            title = in.readString();
            type = in.readInt();
            images = in.createStringArrayList();
        }

        public static final Creator<StoriesBean> CREATOR = new Creator<StoriesBean>() {
            @Override
            public StoriesBean createFromParcel(Parcel in) {
                return new StoriesBean(in);
            }

            @Override
            public StoriesBean[] newArray(int size) {
                return new StoriesBean[size];
            }
        };

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(ga_prefix);
            dest.writeInt(id);
            dest.writeString(title);
            dest.writeInt(type);
            dest.writeStringList(images);
        }
    }

    public static class TopStoriesBean implements Parcelable {
        /**
         * ga_prefix : 112807
         * id : 9017011
         * image : http://pic2.zhimg.com/3a49f65c08e83baf537de924b5c2c5d9.jpg
         * title : 如何理解「北大四成新生认为人生没有意义」？
         * type : 0
         */

        private String ga_prefix;
        private int    id;
        private String image;
        private String title;
        private int    type;

        protected TopStoriesBean(Parcel in) {
            ga_prefix = in.readString();
            id = in.readInt();
            image = in.readString();
            title = in.readString();
            type = in.readInt();
        }

        public static final Creator<TopStoriesBean> CREATOR = new Creator<TopStoriesBean>() {
            @Override
            public TopStoriesBean createFromParcel(Parcel in) {
                return new TopStoriesBean(in);
            }

            @Override
            public TopStoriesBean[] newArray(int size) {
                return new TopStoriesBean[size];
            }
        };

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(ga_prefix);
            dest.writeInt(id);
            dest.writeString(image);
            dest.writeString(title);
            dest.writeInt(type);
        }
    }
}
