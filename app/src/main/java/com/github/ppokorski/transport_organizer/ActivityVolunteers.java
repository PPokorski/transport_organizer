package com.github.ppokorski.transport_organizer;

import android.content.Intent;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.github.ppokorski.transport_organizer.models.Car;
import com.github.ppokorski.transport_organizer.models.Size;
import com.github.ppokorski.transport_organizer.models.Volunteer;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;

public class ActivityVolunteers extends AppCompatActivity {

    ArrayList<Volunteer> volunteers;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteers);
        list = (ListView) findViewById(R.id.list_volunteers);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Intent intent = new Intent(ActivityVolunteers.this, ActivityVolunteer.class);
                intent.putExtra("volunteer", volunteers.get(position).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateList();
    }

    public void addNewVolunteer(View view) {
        Intent intent = new Intent(ActivityVolunteers.this, ActivityVolunteerEdit.class);
        intent.putExtra("volunteer", 0);
        startActivity(intent);
    }

    private void updateList() {
        volunteers = new ArrayList<>(DatabaseHelper.getInstance(this).getAllObjects(Volunteer.class));
        list.setAdapter(new ListAdapterVolunteer(this, volunteers));
    }
}
