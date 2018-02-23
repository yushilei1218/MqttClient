package com.shileiyu.mqttclient.common.adapter;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shileiyu.mqttclient.R;
import com.shileiyu.mqttclient.ui.third.msg.IMMessage;


import java.util.List;

/**
 * @author shilei.yu
 * @since on 2018/2/23.
 */

public class MsgAdapter extends BaseAdapter {
    private List<IMMessage> data;

    public void update(List<IMMessage> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_msg, parent, false);
            convertView.setTag(new VH(convertView));
        }
        VH vh = (VH) convertView.getTag();
        IMMessage imMessage = data.get(position);

        vh.left.setVisibility(imMessage.isMe ? View.VISIBLE : View.GONE);
        vh.right.setVisibility(imMessage.isMe ? View.GONE : View.VISIBLE);
        vh.mTv.setGravity(imMessage.isMe ? Gravity.LEFT : Gravity.RIGHT);
        vh.mTv.setText(new String(imMessage.getPayload()));
        return convertView;
    }

    private final class VH {
        private View convert;
        final View left;
        final View right;
        final TextView mTv;

        VH(View convert) {
            this.convert = convert;
            left = convert.findViewById(R.id.left);
            right = convert.findViewById(R.id.right);
            mTv = (TextView) convert.findViewById(R.id.content);
        }
    }
}
