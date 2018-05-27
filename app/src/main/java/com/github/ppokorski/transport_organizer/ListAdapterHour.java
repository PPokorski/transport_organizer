package com.github.ppokorski.transport_organizer;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.datetimepicker.date.DatePickerDialog;
import com.android.datetimepicker.time.RadialPickerLayout;
import com.android.datetimepicker.time.TimePickerDialog;
import com.github.ppokorski.transport_organizer.models.AvailableHours;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class ListAdapterHour extends BaseAdapter {
    private static final String TIME_PATTERN = "HH:mm";

    private ArrayList<AvailableHours> list_hours;
    private Context context;

    private DateFormat date_format;
    private SimpleDateFormat time_format;

    public ListAdapterHour(Context context, ArrayList<AvailableHours> list_hours) {
        this.list_hours = list_hours;
        this.context = context;

        date_format = DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault());
        time_format = new SimpleDateFormat(TIME_PATTERN, Locale.getDefault());
    }

    @Override
    public int getCount() {
        return list_hours.size();
    }

    @Override
    public Object getItem(int position) {
        return list_hours.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convert_view, ViewGroup parent) {
        final ViewHolder holder;
        if (convert_view == null) {
           convert_view = LayoutInflater.from(context).inflate(R.layout.hours_list_row, null);
           holder = new ViewHolder();

           holder.delete_buton = (ImageButton) convert_view.findViewById(R.id.delete_button_hour);

           holder.txt_from_date = (TextView) convert_view.findViewById(R.id.from_date);
           holder.txt_from_hour = (TextView) convert_view.findViewById(R.id.from_hour);
           holder.txt_to_date = (TextView) convert_view.findViewById(R.id.to_date);
           holder.txt_to_hour = (TextView) convert_view.findViewById(R.id.to_hour);

           holder.calendar_from = Calendar.getInstance();
           holder.calendar_to = Calendar.getInstance();

            convert_view.setTag(holder);
        } else {
            holder = (ViewHolder) convert_view.getTag();
        }

        holder.delete_buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list_hours.remove(position);
                notifyDataSetChanged();
            }
        });

        holder.txt_from_date.setText(date_format.format(list_hours.get(position).getBegin()));
        holder.txt_from_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.newInstance(holder.from_date_listener,
                        holder.calendar_from.get(Calendar.YEAR),
                        holder.calendar_from.get(Calendar.MONTH),
                        holder.calendar_from.get(Calendar.DAY_OF_MONTH))
                        .show(((Activity) context).getFragmentManager(), "datePicker");
            }
        });
        holder.txt_from_hour.setText(time_format.format(list_hours.get(position).getBegin()));
        holder.txt_from_hour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog.newInstance(holder.from_time_listener,
                        holder.calendar_from.get(Calendar.HOUR_OF_DAY),
                        holder.calendar_from.get(Calendar.MINUTE), true)
                        .show(((Activity) context).getFragmentManager(), "timePicker");
            }
        });
        holder.txt_to_date.setText(date_format.format(list_hours.get(position).getEnd()));
        holder.txt_to_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.newInstance(holder.to_date_listener,
                        holder.calendar_to.get(Calendar.YEAR),
                        holder.calendar_to.get(Calendar.MONTH),
                        holder.calendar_to.get(Calendar.DAY_OF_MONTH))
                        .show(((Activity) context).getFragmentManager(), "datePicker");
            }
        });
        holder.txt_to_hour.setText(time_format.format(list_hours.get(position).getEnd()));
        holder.txt_to_hour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog.newInstance(holder.to_time_listener,
                        holder.calendar_to.get(Calendar.HOUR_OF_DAY),
                        holder.calendar_to.get(Calendar.MINUTE), true)
                        .show(((Activity) context).getFragmentManager(), "timePicker");
            }
        });

        holder.calendar_from.setTime(list_hours.get(position).getBegin());
        holder.calendar_to.setTime(list_hours.get(position).getEnd());

        holder.from_date_listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePickerDialog dialog, int year, int monthOfYear, int dayOfMonth) {
                holder.calendar_from.set(year, monthOfYear, dayOfMonth);
                list_hours.get(position).setBegin(holder.calendar_from.getTime());
                notifyDataSetChanged();
            }
        };

        holder.to_date_listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePickerDialog dialog, int year, int monthOfYear, int dayOfMonth) {
                holder.calendar_to.set(year, monthOfYear, dayOfMonth);
                list_hours.get(position).setEnd(holder.calendar_to.getTime());
                notifyDataSetChanged();
            }
        };

        holder.from_time_listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
                holder.calendar_from.set(Calendar.HOUR_OF_DAY, hourOfDay);
                holder.calendar_from.set(Calendar.MINUTE, minute);
                list_hours.get(position).setBegin(holder.calendar_from.getTime());
                notifyDataSetChanged();
            }
        };

        holder.to_time_listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
                holder.calendar_to.set(Calendar.HOUR_OF_DAY, hourOfDay);
                holder.calendar_to.set(Calendar.MINUTE, minute);
                list_hours.get(position).setEnd(holder.calendar_to.getTime());
                notifyDataSetChanged();
            }
        };

        return convert_view;
    }

    static class ViewHolder {
        ImageButton delete_buton;
        TextView txt_from_date, txt_from_hour;
        TextView txt_to_date, txt_to_hour;

        Calendar calendar_from, calendar_to;

        DatePickerDialog.OnDateSetListener from_date_listener, to_date_listener;
        TimePickerDialog.OnTimeSetListener from_time_listener, to_time_listener;
    }
}
