package com.github.ppokorski.transport_organizer;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapterVolunteer extends BaseAdapter {
    private ArrayList<Volunteer> listData;
    private LayoutInflater layoutInflater;

    public ListAdapterVolunteer(Context aContext, ArrayList<Volunteer> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.volunteers_list_row, null);
            holder = new ViewHolder();
            holder.imieView = (TextView) convertView.findViewById(R.id.imie);
            holder.nazwiskoView = (TextView) convertView.findViewById(R.id.nazwisko);
            holder.kontaktView = (TextView) convertView.findViewById(R.id.kontakt);
            holder.dostepnoscView = (TextView) convertView.findViewById(R.id.dostepnosc);
            holder.samochodView = (TextView) convertView.findViewById(R.id.samochod);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.imieView.setText(listData.get(position).getImie());
        holder.nazwiskoView.setText(" "+listData.get(position).getNazwisko());
        holder.kontaktView.setText(listData.get(position).getKontakt());
        holder.dostepnoscView.setText(" , " +listData.get(position).getDostepnosc());
        holder.samochodView.setText(" , " +listData.get(position).getSamochod());
        return convertView;
    }

    static class ViewHolder {
        TextView imieView;
        TextView nazwiskoView;
        TextView kontaktView;
        TextView dostepnoscView;
        TextView samochodView;
    }
}
