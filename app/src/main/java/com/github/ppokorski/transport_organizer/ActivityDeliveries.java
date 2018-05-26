package com.github.ppokorski.transport_organizer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ListView;

import com.github.ppokorski.transport_organizer.models.Transport;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class ActivityDeliveries extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliveries);

        ArrayList image_details = getListData();
        ListView list = (ListView) findViewById(R.id.list_deliveries);
        list.setAdapter(new ListAdapterDelivery(this, image_details));

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Intent start_edit = new Intent(ActivityDeliveries.this, ActivityDelivery.class);
                start_edit.putExtra("number_on_del_list", position);
                startActivity(start_edit);
            }
        });
    }

    private ArrayList getListData() {
        ArrayList<Transport> results = new ArrayList<Transport>();
        /*Delivery opisKolumn = new Delivery();
        opisKolumn.setSkad("SKAD");
        opisKolumn.setNazwa("NAZWA");
        opisKolumn.setIlosc("ILOSC");
        opisKolumn.setKiedy_mozna("KIEDY MOZNA");
        opisKolumn.setStatus("STATUS");
        results.add(opisKolumn);*/

        Transport transport = new Transport();
        transport.setName("5 kg karpia!!");
        transport.setPickupAddress("Ul. Marszałkowska 52");
        transport.setTargetAddress("Ul. Bokserska 53");

        results.add(transport);
        results.add(transport);
        results.add(transport);
        results.add(transport);


//        Delivery pozycja1 = new Delivery();
//        pozycja1.setNazwa("Karp");
//        pozycja1.setIlosc("5,5kg");
//        pozycja1.setStatus("brak");
//        pozycja1.setSkad("ul. Postępu 2, Mokotów");
//        pozycja1.setKiedy_mozna("czwartek 7-15, piątek 10-12");
//        results.add(pozycja1);
//        Delivery pozycja2 = new Delivery();
//        pozycja2.setNazwa("Karp");
//        pozycja2.setIlosc("5,5kg");
//        pozycja2.setStatus("brak");
//        pozycja2.setSkad("ul. Postępu 2, Mokotów");
//        pozycja2.setKiedy_mozna("czwartek 7-15, piątek 10-12");
//        results.add(pozycja2);
//        results.add(pozycja1);
//        results.add(pozycja1);
//        results.add(pozycja1);
//        results.add(pozycja1);
//        results.add(pozycja1);
//        results.add(pozycja1);
//        results.add(pozycja1);

        // TUTAJ DODAWAC WIERSZE DO LISTY

        return results;
    }

    public void addNewDelivery(View view){
        Intent start_edit = new Intent(ActivityDeliveries.this, ActivityDeliveryEdit.class);
        start_edit.putExtra("number_on_vol_list", -1);
        start_edit.putExtra("new_del", true);
        startActivity(start_edit);
    }
}
