package projekt_java.organizator_dostaw;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;



public class DeliveriesListAdapter extends BaseAdapter {
    private ArrayList<DeliveriesRow> listData;
    private LayoutInflater layoutInflater;

    public DeliveriesListAdapter(Context aContext, ArrayList<DeliveriesRow> listData) {
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
            convertView = layoutInflater.inflate(R.layout.deliveries_list_row, null);
            holder = new ViewHolder();
            holder.skadView = (TextView) convertView.findViewById(R.id.skad);
            holder.nazwaView = (TextView) convertView.findViewById(R.id.nazwa);
            holder.iloscView = (TextView) convertView.findViewById(R.id.ilosc);
            holder.kiedy_moznaView = (TextView) convertView.findViewById(R.id.kiedy_mozna);
            holder.statusView = (TextView) convertView.findViewById(R.id.status);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.nazwaView.setText(listData.get(position).getNazwa());
        holder.iloscView.setText(listData.get(position).getIlosc());
        holder.statusView.setText("Status:" +listData.get(position).getStatus());
        holder.skadView.setText("Lokalizacja:   " +listData.get(position).getSkad());
        holder.kiedy_moznaView.setText("Dostepnosc:   " +listData.get(position).getKiedy_mozna());

        return convertView;
    }

    static class ViewHolder {
        TextView skadView;
        TextView nazwaView;
        TextView iloscView;
        TextView kiedy_moznaView;
        TextView statusView;
    }
}
