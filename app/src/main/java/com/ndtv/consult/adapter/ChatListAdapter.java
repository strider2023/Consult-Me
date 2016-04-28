package com.ndtv.consult.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ndtv.consult.R;
import com.ndtv.consult.dao.ChatData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arindamnath on 28/04/16.
 */
public class ChatListAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<ChatData> chatDatas = new ArrayList<>();

    public ChatListAdapter(Context contenxt) {
        this.context = contenxt;
        layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<ChatData> data) {
        chatDatas = data;
        notifyDataSetChanged();
    }

    public void addData(ChatData data) {
        chatDatas.add(data);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return chatDatas.size();
    }

    @Override
    public ChatData getItem(int position) {
        return chatDatas.get(position);
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
            convertView = layoutInflater.inflate(R.layout.adapter_chat, parent, false);
            holder.peerContainer = (LinearLayout) convertView.findViewById(R.id.adapter_chat_peer_container);
            holder.userContainer = (LinearLayout) convertView.findViewById(R.id.adapter_chat_user_container);
            holder.peerText = (TextView) convertView.findViewById(R.id.adapter_chat_peer_text);
            holder.peerDate = (TextView) convertView.findViewById(R.id.adapter_chat_peer_date);
            holder.userText = (TextView) convertView.findViewById(R.id.adapter_chat_user_text);
            holder.userDate = (TextView) convertView.findViewById(R.id.adapter_chat_user_date);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if(chatDatas.get(position).isSameUser()) {
            holder.peerContainer.setVisibility(View.GONE);
            holder.userText.setText(chatDatas.get(position).getChatText());
            holder.userDate.setText(chatDatas.get(position).getDate());
        } else {
            holder.userContainer.setVisibility(View.GONE);
            holder.peerText.setText(chatDatas.get(position).getChatText());
            holder.peerDate.setText(chatDatas.get(position).getDate());
        }
        return convertView;
    }

    static class ViewHolder {
        LinearLayout peerContainer, userContainer;
        TextView peerText, peerDate, userText, userDate;
    }
}
