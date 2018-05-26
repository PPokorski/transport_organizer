package com.github.ppokorski.transport_organizer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.github.ppokorski.transport_organizer.models.Transport;

import java.util.ArrayList;



public class ListAdapterDelivery extends BaseAdapter {
    private ArrayList<Transport> list_data;
    private LayoutInflater layout_inflater;

    public ListAdapterDelivery(Context aContext, ArrayList<Transport> list_data) {
        this.list_data = list_data;
        layout_inflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return list_data.size();
    }

    @Override
    public Object getItem(int position) {
        return list_data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layout_inflater.inflate(R.layout.deliveries_list_row, null);
            holder = new ViewHolder();
            holder.txt_name = (TextView) convertView.findViewById(R.id.name);
            holder.txt_status = (TextView) convertView.findViewById(R.id.status);
            holder.txt_pickup_address = (TextView) convertView.findViewById(R.id.pickup_addres);
            holder.txt_target_address = (TextView) convertView.findViewById(R.id.target_address);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txt_name.setText(list_data.get(position).getName());
        holder.txt_status.setText("Unassigned");
        holder.txt_pickup_address.setText(list_data.get(position).getPickupAddress());
        holder.txt_target_address.setText(list_data.get(position).getTargetAddress());

        return convertView;
    }

    static class ViewHolder {
        TextView txt_name;
        TextView txt_status;
        TextView txt_pickup_address;
        TextView txt_target_address;
    }
}
