package com.github.ppokorski.transport_organizer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ActivityVolunteerEdit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_edit);

        //wczytanie z ktorego elementu z listy zostalo wywolane activity
        int number_on_list =  getIntent().getExtras().getInt("number_on_vol_list",-1); //to jest numer edytowanego elementu na liscie
        boolean new_vol = getIntent().getExtras().getBoolean("new_vol");

        System.out.println(number_on_list);

        TextView headline = (TextView) findViewById(R.id.headline_volunteers_edit);
        if(new_vol){
            headline.setText(getResources().getString(R.string.edit_activity_new));
        }
        else
        {
            headline.setText(getResources().getString(R.string.edit_activity_edit));
        }

        //załadowanie danych elementu nr X pobranego z docs'a
        if(number_on_list!=-1) {
            TextView name = (TextView) findViewById(R.id.volunteer_name);
            name.setText("xxx");
            TextView surname = (TextView) findViewById(R.id.voluneer_surname);
            surname.setText("xxx");
            TextView contact = (TextView) findViewById(R.id.voluneer_contact);
            contact.setText("xxx");
            TextView availability = (TextView) findViewById(R.id.voluneer_availability);
            availability.setText("xxx");
            TextView car = (TextView) findViewById(R.id.voluneer_car);
            car.setText("xxx");
        }
    }

    public void confirmVolunteer(View view){
        //edycja bezposrednio w docs'ie
        // po wcisnieciu przycisku, pola z layoutu z danymi zapisywane do docs'a

        onBackPressed();
    }
}
