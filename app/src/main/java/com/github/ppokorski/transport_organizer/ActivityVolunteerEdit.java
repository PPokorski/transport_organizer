package com.github.ppokorski.transport_organizer;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
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

import io.objectbox.Box;

public class ActivityVolunteerEdit extends AppCompatActivity {

    private Volunteer volunteer;
    private Intent result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_edit);

        long id = getIntent().getExtras().getLong("volunteer");
        volunteer = id != 0? DatabaseHelper.getInstance(this).getObject(Volunteer.class, id) : null;

        TextView headline = (TextView) findViewById(R.id.headline_volunteers_edit);
        TextView txt_name = (TextView) findViewById(R.id.volunteer_name);
        TextView txt_surname = (TextView) findViewById(R.id.volunteer_surname);
        TextView txt_phone_number = (TextView) findViewById(R.id.volunteer_phone_number);
        TextView txt_email = (TextView) findViewById(R.id.volunteer_email);

        if(volunteer == null) {
            headline.setText(getResources().getString(R.string.edit_activity_new));
            volunteer = new Volunteer();
        }
        else
        {
            headline.setText(getResources().getString(R.string.edit_activity_edit));
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
                        save();
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

    private void save() {
        volunteer.setName(((TextView) findViewById(R.id.volunteer_name)).getText().toString());
        volunteer.setSurname(((TextView) findViewById(R.id.volunteer_surname)).getText().toString());
        volunteer.setPhoneNumber(((TextView) findViewById(R.id.volunteer_phone_number)).getText().toString());
        volunteer.setEmail(((TextView) findViewById(R.id.volunteer_email)).getText().toString());

        DatabaseHelper.getInstance(this).updateOrCreateObject(Volunteer.class, volunteer);
    }
}
