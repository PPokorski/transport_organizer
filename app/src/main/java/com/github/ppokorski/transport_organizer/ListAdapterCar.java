package com.github.ppokorski.transport_organizer;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.ppokorski.transport_organizer.models.Car;
import com.github.ppokorski.transport_organizer.models.Size;

import java.util.ArrayList;

public class ListAdapterCar extends BaseAdapter {
    private ArrayList<Car> list_cars;
    private LayoutInflater layout_inflater;

    public ListAdapterCar(Context context, ArrayList<Car> list_cars) {
        this.list_cars = list_cars;
        layout_inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list_cars.size();
    }

    @Override
    public Object getItem(int position) {
        return list_cars.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convert_view, ViewGroup parent) {
        final ViewHolder holder;
        if (convert_view == null) {
            convert_view = layout_inflater.inflate(R.layout.cars_list_row, null);
            holder = new ViewHolder();

            holder.txt_name = (TextView) convert_view.findViewById(R.id.name);
            holder.spin_size = (Spinner) convert_view.findViewById(R.id.size);
            holder.spin_size.setAdapter(new ArrayAdapter<Size>(layout_inflater.getContext(), android.R.layout.simple_spinner_item, Size.values()));
            convert_view.setTag(holder);
            holder.delete_button = (ImageButton) convert_view.findViewById(R.id.delete_button_car);
        } else {
            holder = (ViewHolder) convert_view.getTag();
        }

        holder.txt_name.setText(list_cars.get(position).getName());
        holder.spin_size.setSelection(list_cars.get(position).getSize().getKey());

        holder.txt_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                list_cars.get(position).setName(editable.toString());
            }
        });

        holder.spin_size.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int spinner_position, long id) {
                list_cars.get(position).setSize(Size.values()[spinner_position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        holder.delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list_cars.remove(position);
                notifyDataSetChanged();
            }
        });

        return convert_view;
    }

    static class ViewHolder {
        TextView txt_name;
        Spinner spin_size;
        ImageButton delete_button;
    }
}
