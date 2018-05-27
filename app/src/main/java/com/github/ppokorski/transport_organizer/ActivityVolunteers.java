package com.github.ppokorski.transport_organizer;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.github.ppokorski.transport_organizer.models.Car;
import com.github.ppokorski.transport_organizer.models.Size;
import com.github.ppokorski.transport_organizer.models.Volunteer;

import java.util.ArrayList;
import java.util.List;

public class ActivityVolunteers extends AppCompatActivity {

    ArrayList<Volunteer> volunteers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteers);

        volunteers = new ArrayList<>();
        ArrayList image_details = getListData();
        ListView list = (ListView) findViewById(R.id.list_volunteers);
        list.setAdapter(new ListAdapterVolunteer(this, image_details));

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Intent go_indirect = new Intent(ActivityVolunteers.this, ActivityVolunteer.class);
                go_indirect.putExtra("volunteer", volunteers.get(position));
                startActivity(go_indirect);
            }
        });
    }

    private ArrayList getListData() {
        Volunteer vol = new Volunteer();
        vol.setName("Jan");
        vol.setSurname("Kowalski");
        vol.setPhoneNumber("65465432");
        vol.setEmail("j.kowalski@gmail.com");
        Car car = new Car("Tico", Size.SMALL);
        Car car1 = new Car("Lambo", Size.MEDIUM);
        vol.addCar(car);
        vol.addCar(car1);

        volunteers.add(vol);
        volunteers.add(vol);
        volunteers.add(vol);
        volunteers.add(vol);
        volunteers.add(vol);

        return volunteers;
    }

    public void addNewVolunteer(View view) {
        Intent start_edit = new Intent(ActivityVolunteers.this, ActivityVolunteerEdit.class);
        start_edit.putExtra("volunteer", (Parcelable) null);
        startActivity(start_edit);
    }
}
