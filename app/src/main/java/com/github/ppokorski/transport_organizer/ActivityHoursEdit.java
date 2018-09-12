package com.github.ppokorski.transport_organizer;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.github.ppokorski.transport_organizer.models.AvailableHours;

import java.util.ArrayList;

public class ActivityHoursEdit extends AppCompatActivity {

    ArrayList<AvailableHours> hours;
    ListAdapterHour adapter_hours;
    Intent result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hours_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton button_add_hour = (FloatingActionButton) findViewById(R.id.button_add_hour);
        button_add_hour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hours.add(new AvailableHours());
                adapter_hours.notifyDataSetChanged();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        hours = new ArrayList<>();
        // TODO Adapt to ObjectBox

//        hours = getIntent().getExtras().getParcelableArrayList("hours");
        ListView list = (ListView) findViewById(R.id.list_hours);
        adapter_hours = new ListAdapterHour(this, hours);
        list.setAdapter(adapter_hours);

        result = new Intent();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Save changes?")
                .setMessage("Do you want to save the changes?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Adapt to ObjectBox
//                        result.putParcelableArrayListExtra("hours", hours);
                        setResult(RESULT_OK, result);
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setResult(RESULT_CANCELED, result);
                        finish();
                    }
                })
                .show();
    }
}
