package com.github.ppokorski.transport_organizer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ActivityVolunteers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteers);

        ArrayList image_details = getListData();
        final ListView list = (ListView) findViewById(R.id.list_volunteers);
        list.setAdapter(new ListAdapterVolunteer(this, image_details));

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Intent go_indirect = new Intent(ActivityVolunteers.this, ActivityVolunteer.class);
                go_indirect.putExtra("number_on_vol_list", position);
                startActivity(go_indirect);
            }
        });
    }

    private ArrayList getListData() {
        ArrayList<Volunteer> results = new ArrayList<Volunteer>();
        /*Volunteer opisKolumn = new Volunteer();
        opisKolumn.setImie("IMIE");
        opisKolumn.setNazwisko("NAZWISKO");
        opisKolumn.setKontakt("KONTAKT");
        opisKolumn.setDostepnosc("DOSTEPNOSC");
        opisKolumn.setSamochod("SAMOCHOD");
        results.add(opisKolumn);*/


        Volunteer pozycja1 = new Volunteer();
        pozycja1.setImie("Marek");
        pozycja1.setNazwisko("Kowal");
        pozycja1.setKontakt("601622121");
        pozycja1.setDostepnosc("czwartek 10-17");
        pozycja1.setSamochod("Audi A4");
        results.add(pozycja1);
        results.add(pozycja1);
        results.add(pozycja1);
        results.add(pozycja1);
        results.add(pozycja1);
        results.add(pozycja1);
        results.add(pozycja1);
        results.add(pozycja1);
        results.add(pozycja1);
        results.add(pozycja1);
        // TUTAJ DODAWAC WIERSZE DO LISTY

        return results;
    }

    public void addNewVolunteer(View view) {
        Intent start_edit = new Intent(ActivityVolunteers.this, ActivityVolunteerEdit.class);
        start_edit.putExtra("number_on_vol_list", -1);
        start_edit.putExtra("new_vol", true);
        startActivity(start_edit);
    }
}
