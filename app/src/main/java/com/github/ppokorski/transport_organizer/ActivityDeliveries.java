package com.github.ppokorski.transport_organizer;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ListView;

import com.github.ppokorski.transport_organizer.models.Transport;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import io.objectbox.Box;

public class ActivityDeliveries extends AppCompatActivity {
    ArrayList<Transport> transports;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliveries);
        list = (ListView) findViewById(R.id.list_deliveries);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Intent intent = new Intent(ActivityDeliveries.this, ActivityDelivery.class);
                intent.putExtra("transport", transports.get(position).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateList();
    }

    public void addNewDelivery(View view){
        Intent intent = new Intent(ActivityDeliveries.this, ActivityDeliveryEdit.class);
        intent.putExtra("volunteer", 0);
        startActivity(intent);
    }

    private void updateList() {
        transports = new ArrayList<>(DatabaseHelper.getInstance(this).getAllObjects(Transport.class));
        list.setAdapter(new ListAdapterDelivery(this, transports));
    }
}
