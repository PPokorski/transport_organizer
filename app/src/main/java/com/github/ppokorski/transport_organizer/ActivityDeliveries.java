package com.github.ppokorski.transport_organizer;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ListView;

import com.github.ppokorski.transport_organizer.models.Transport;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class ActivityDeliveries extends AppCompatActivity {

    ArrayList<Transport> transports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliveries);

        transports = new ArrayList<>();
        ArrayList image_details = getListData();
        ListView list = (ListView) findViewById(R.id.list_deliveries);
        list.setAdapter(new ListAdapterDelivery(this, image_details));

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Intent start_edit = new Intent(ActivityDeliveries.this, ActivityDelivery.class);
                start_edit.putExtra("transport", transports.get(position));
                startActivity(start_edit);
            }
        });
    }

    private ArrayList getListData() {
        Transport transport = new Transport();
        transport.setName("5 kg karpia!!");
        transport.setPickupAddress("Ul. Marszałkowska 52");
        transport.setTargetAddress("Ul. Bokserska 53");

        transports.add(transport);
        transports.add(transport);
        transports.add(transport);
        transports.add(transport);

        return transports;
    }

    public void addNewDelivery(View view){
        Intent start_edit = new Intent(ActivityDeliveries.this, ActivityDeliveryEdit.class);
        start_edit.putExtra("volunteer", (Parcelable) null);
        startActivity(start_edit);
    }
}
