package com.github.ppokorski.transport_organizer;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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
    Intent result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_edit);

        volunteer = new Volunteer();
        volunteer =  getIntent().getExtras().getParcelable("volunteer");
        boolean new_vol = (volunteer == null);

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

        if(!new_vol)
        {
            txt_name.setText(volunteer.getName());
            txt_surname.setText(volunteer.getSurname());
            txt_phone_number.setText(volunteer.getPhoneNumber());
            txt_email.setText(volunteer.getEmail());
        }

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
                        result.putExtra("volunteer", volunteer);
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
