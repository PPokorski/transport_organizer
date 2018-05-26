package com.github.ppokorski.transport_organizer;


import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.github.ppokorski.transport_organizer.models.Car;
import com.github.ppokorski.transport_organizer.models.Volunteer;
import com.google.api.client.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ListAdapterVolunteer extends BaseAdapter {
    private ArrayList<Volunteer> list_data;
    private LayoutInflater layout_inflater;

    public ListAdapterVolunteer(Context aContext, ArrayList<Volunteer> list_data) {
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
            convertView = layout_inflater.inflate(R.layout.volunteers_list_row, null);
            holder = new ViewHolder();
            holder.txt_name = (TextView) convertView.findViewById(R.id.name);
            holder.txt_surname = (TextView) convertView.findViewById(R.id.surname);
            holder.txt_phone_number = (TextView) convertView.findViewById(R.id.phone_number);
            holder.txt_email = (TextView) convertView.findViewById(R.id.email);
            holder.txt_cars = (TextView) convertView.findViewById(R.id.cars);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txt_name.setText(list_data.get(position).getName());
        holder.txt_surname.setText(list_data.get(position).getSurname());
        holder.txt_phone_number.setText(list_data.get(position).getPhoneNumber());
        holder.txt_email.setText(list_data.get(position).getEmail());
        List<String> cars = new ArrayList<>();
        for (Car car : list_data.get(position).getCars())
        {
            cars.add(car.getName());
        }
        holder.txt_cars.setText(TextUtils.join(", ", cars));
        return convertView;
    }

    static class ViewHolder {
        TextView txt_name;
        TextView txt_surname;
        TextView txt_phone_number;
        TextView txt_email;
        TextView txt_cars;
    }
}
