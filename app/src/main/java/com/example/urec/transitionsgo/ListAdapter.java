package com.example.urec.transitionsgo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by urec on 10.08.15.
 */
public class ListAdapter extends BaseAdapter {

    private final ArrayList<ListItem> items;

    public ListAdapter(ArrayList<ListItem> items) {
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public ListItem getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, null);
            holder = new Holder();
            holder.largeImage = (ImageView) view.findViewById(R.id.iv_large_image);
            holder.smallImage = (ImageView) view.findViewById(R.id.iv_small_image);
            holder.title = (TextView) view.findViewById(R.id.tv_title);
            holder.subTitle = (TextView) view.findViewById(R.id.tv_sub_title);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }

        ListItem item = getItem(i);

        Picasso.with(viewGroup.getContext()).load(item.getLargeImage()).into(holder.largeImage);
        Picasso.with(viewGroup.getContext()).load(item.getSmallImage()).into(holder.smallImage);
        holder.title.setText(item.getTitle());
        holder.subTitle.setText(item.getSubTitle());
        return view;
    }


    class Holder {
        public ImageView smallImage;
        public ImageView largeImage;
        public TextView title;
        public TextView subTitle;

    }

}
