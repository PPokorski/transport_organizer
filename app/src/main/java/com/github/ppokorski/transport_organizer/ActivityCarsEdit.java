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

import com.github.ppokorski.transport_organizer.models.Car;

import java.util.ArrayList;

public class ActivityCarsEdit extends AppCompatActivity {

    ArrayList<Car> cars;
    ListAdapterCar adapter_cars;
    Intent result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton button_add_car = (FloatingActionButton) findViewById(R.id.button_add_car);
        button_add_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cars.add(new Car());
                adapter_cars.notifyDataSetChanged();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cars = new ArrayList<>();
        cars = getIntent().getExtras().getParcelableArrayList("cars");
        ListView list = (ListView) findViewById(R.id.list_cars);
        adapter_cars = new ListAdapterCar(this, cars);
        list.setAdapter(adapter_cars);

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
                        result.putParcelableArrayListExtra("cars", cars);
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
