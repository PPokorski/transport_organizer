package com.github.ppokorski.transport_organizer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.github.ppokorski.transport_organizer.models.Volunteer;

public class ActivityVolunteer extends AppCompatActivity {

    Volunteer volunteer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer);

        volunteer = new Volunteer();
        volunteer =  getIntent().getExtras().getParcelable("volunteer");
    }

    public void goEditVol(View view) {
        Intent go_edit = new Intent(this, ActivityVolunteerEdit.class);
        go_edit.putExtra("volunteer", volunteer);
        go_edit.putExtra("new_vol", false);
        startActivity(go_edit);
    }

    public void goAddHoursVol(View view) {
//        Intent go_add_hours = new Intent(this, VolunteersHours.class);
//        go_add_hours.putExtra("number_on_vol_list", number_on_list);
//        startActivity(go_add_hours);
    }

    public void callVol(View view) {
        //dzwonienie
    }

    public void sensSMSVol(View view) {
        //wysylanie sms'a
    }

    public void backToVolList(View view) {
        Intent intent = new Intent(this, ActivityVolunteers.class);
        startActivity(intent);
    }
}
