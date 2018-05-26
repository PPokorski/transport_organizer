package com.github.ppokorski.transport_organizer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.github.ppokorski.transport_organizer.models.Car;
import com.github.ppokorski.transport_organizer.models.Volunteer;

import java.util.ArrayList;

public class ActivityVolunteerEdit extends AppCompatActivity {

    Volunteer volunteer;

    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_edit);

        volunteer = new Volunteer();
        volunteer =  getIntent().getExtras().getParcelable("volunteer");
        boolean new_vol = getIntent().getExtras().getBoolean("new_vol");

        TextView headline = (TextView) findViewById(R.id.headline_volunteers_edit);
        if(new_vol){
            headline.setText(getResources().getString(R.string.edit_activity_new));
        }
        else
        {
            headline.setText(getResources().getString(R.string.edit_activity_edit));
        }

        //załadowanie danych elementu nr X pobranego z docs'a
        TextView txt_name = (TextView) findViewById(R.id.volunteer_name);
        TextView txt_surname = (TextView) findViewById(R.id.volunteer_surname);
        TextView txt_phone_number = (TextView) findViewById(R.id.volunteer_phone_number);
        TextView txt_email = (TextView) findViewById(R.id.volunteer_email);
        final ListView list_cars = (ListView) findViewById(R.id.list_cars);
        add_button = (Button) findViewById(R.id.car_add);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volunteer.addCar(new Car());
                ((BaseAdapter)list_cars.getAdapter()).notifyDataSetChanged();
            }
        });

        if(!new_vol)
        {
            txt_name.setText(volunteer.getName());
            txt_surname.setText(volunteer.getSurname());
            txt_phone_number.setText(volunteer.getPhoneNumber());
            txt_email.setText(volunteer.getEmail());
            list_cars.setAdapter(new ListAdapterCar(this, volunteer.getCars()));
        }
    }

    public void confirmVolunteer(View view){
        //edycja bezposrednio w docs'ie
        // po wcisnieciu przycisku, pola z layoutu z danymi zapisywane do docs'a

        onBackPressed();
    }
}
