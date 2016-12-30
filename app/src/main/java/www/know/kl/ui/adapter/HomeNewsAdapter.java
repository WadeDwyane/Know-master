package www.retrofit.com.retrofitrxjavademo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import www.retrofit.com.retrofitrxjavademo.R;
import www.retrofit.com.retrofitrxjavademo.mvp.main.model.MainNewsBean;
import www.retrofit.com.retrofitrxjavademo.mvp.main.model.NewsBean;

/**
 * Created by kui.liu on 2016/11/28.
 */
public class HomeNewsAdapter extends BaseAdapter implements StickyListHeadersAdapter, SectionIndexer {
    private Context        context;
    private List<NewsBean> list;
    private int[]          mSectionIndices;
    private String[]       mSectionDates;

    public HomeNewsAdapter(Context context, List<NewsBean> list) {
        this.context = context;
        this.list = list;
        mSectionIndices = getSectionIndices();
        mSectionDates = getSectionDates();
    }

    public String[] getSectionDates() {
        if (list.size() == 0) {
            return new String[0];
        } else {
            String[] dates = new String[mSectionIndices.length];
            for (int i = 0; i < mSectionIndices.length; i++) {
                dates[i] = list.get(mSectionIndices[i]).getDate();
            }
            return dates;
        }
    }

    private int[] getSectionIndices() {
        if (list.size() == 0) {
            return new int[0];
        } else {
            ArrayList<Integer> sectionIndices = new ArrayList<>();
            String date = list.get(0).getDate();
            sectionIndices.add(0);
            for (int i = 1; i < list.size(); i++) {
                if (!date.equals(list.get(i).getDate())) {
                    date = list.get(i).getDate();
                    sectionIndices.add(i);
                }
            }
            int[] sections = new int[sectionIndices.size()];
            for (int i = 0; i < sectionIndices.size(); i++) {
                sections[i] = sectionIndices.get(i);
            }
            return sections;
        }
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {

        HeaderViewHolder headerViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_main_header, parent, false);
            headerViewHolder = new HeaderViewHolder(convertView);
            convertView.setTag(headerViewHolder);
        } else {
            headerViewHolder = (HeaderViewHolder) convertView.getTag();
        }

        if (position < mSectionDates.length) {
            String date = mSectionDates[position];
            headerViewHolder.tvHeader.setText(date);
        }
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        return list.get(position).getDate().substring(0, 1).charAt(0);
    }

    @Override
    public int getCount() {
        if (null != list) {
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (null != list) {
            return list.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_main_news, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (list.size() > 0) {
            MainNewsBean.StoriesBean storiesBean = list.get(position).getStoriesBean();
            holder.mSimpleDraweeView.setImageURI(storiesBean.getImages().get(0));
            holder.mTextView.setText(storiesBean.getTitle());
        }
        return convertView;
    }

    @Override
    public Object[] getSections() {
        return mSectionDates;
    }

    /**
     * 获得第setion个header对应的position
     *
     * @param section
     * @return
     */
    @Override
    public int getPositionForSection(int section) {

        if (mSectionIndices.length == 0) {
            return 0;
        }

        if (section >= mSectionIndices.length) {
            section = mSectionIndices.length - 1;
        } else if (section < 0) {
            section = 0;
        }
        return mSectionIndices[section];
    }

    /**
     * 获得第position位置的section具体有多少个
     *
     * @param position
     * @return
     */
    @Override
    public int getSectionForPosition(int position) {
        for (int i = 0; i < mSectionIndices.length; i++) {
            if (position < mSectionIndices[i]) {
                return i - 1;
            }
        }
        return mSectionIndices.length - 1;
    }

    class ViewHolder {
        public SimpleDraweeView mSimpleDraweeView;
        public TextView         mTextView;

        public ViewHolder(View v) {
            mSimpleDraweeView = (SimpleDraweeView) v.findViewById(R.id.item_sdv_icon);
            mTextView = (TextView) v.findViewById(R.id.item_tv_title);
        }
    }

    class HeaderViewHolder {
        public TextView tvHeader;

        public HeaderViewHolder(View v) {
            tvHeader = (TextView) v.findViewById(R.id.item_tv_header);
        }
    }

    public void clear() {
        list.clear();
        mSectionIndices = new int[0];
        mSectionDates = new String[0];
        notifyDataSetChanged();
    }

    public void restore(List<NewsBean> list1) {
        list.addAll(list1);
        mSectionIndices = getSectionIndices();
        mSectionDates = getSectionDates();
        notifyDataSetChanged();
    }
}

