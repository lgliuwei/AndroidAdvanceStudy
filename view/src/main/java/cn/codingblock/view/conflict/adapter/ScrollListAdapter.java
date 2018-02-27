package cn.codingblock.view.conflict.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.codingblock.view.R;

/**
 * Created by liuwei on 18/1/22.
 */

public class ScrollListAdapter extends BaseAdapter {

    Context context;
    List<String> list;

    public ScrollListAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_scroll_list, null);
            viewHolder.tv_show = convertView.findViewById(R.id.tv_show);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv_show.setText(list.get(position));

        return convertView;
    }

    class ViewHolder{
        TextView tv_show;
    }
}
