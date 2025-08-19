package com.s23010658.doit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class NotificationAdapter extends BaseAdapter {

    Context context;
    ArrayList<Notification> notifications;

    public NotificationAdapter(Context context, ArrayList<Notification> notifications) {
        this.context = context;
        this.notifications = notifications;
    }

    @Override
    public int getCount() {
        return notifications.size();
    }

    @Override
    public Object getItem(int position) {
        return notifications.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2, parent, false);
        }

        TextView tv1 = convertView.findViewById(android.R.id.text1);
        TextView tv2 = convertView.findViewById(android.R.id.text2);

        Notification notification = notifications.get(position);
        tv1.setText(notification.getMessage());
        tv2.setText("Time: " + notification.getTimestamp());

        return convertView;
    }
}
