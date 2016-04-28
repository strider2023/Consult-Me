package com.ndtv.consult.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ndtv.consult.R;
import com.ndtv.consult.dao.ConsultListData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arindamnath on 26/04/16.
 */
public class ConsultListAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<ConsultListData> consultListDatas = new ArrayList<>();

    public ConsultListAdapter(Context contenxt) {
        this.context = contenxt;
        layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<ConsultListData> data) {
        consultListDatas = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return consultListDatas.size();
    }

    @Override
    public ConsultListData getItem(int position) {
        return consultListDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.adapter_consult_list, parent, false);
            holder.name = (TextView) convertView.findViewById(R.id.adapter_consult_name);
            holder.details = (TextView) convertView.findViewById(R.id.adapter_consult_details);
            holder.tag = (TextView) convertView.findViewById(R.id.adapter_consult_tag);
            holder.date = (TextView) convertView.findViewById(R.id.adapter_consult_date);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(consultListDatas.get(position).getName());
        holder.details.setText(consultListDatas.get(position).getLastChatText());
        if(consultListDatas.get(position).isNew()) {
            holder.tag.setVisibility(View.VISIBLE);
        } else {
            holder.tag.setVisibility(View.GONE);
        }
        holder.date.setText(consultListDatas.get(position).getDate());
        return convertView;
    }

    static class ViewHolder {
        TextView name, details, tag, date;
    }
}
