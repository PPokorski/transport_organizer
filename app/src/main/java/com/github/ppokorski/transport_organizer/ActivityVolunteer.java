package com.github.ppokorski.transport_organizer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.ppokorski.transport_organizer.models.AvailableHours;
import com.github.ppokorski.transport_organizer.models.Car;
import com.github.ppokorski.transport_organizer.models.Volunteer;

import java.util.ArrayList;

public class ActivityVolunteer extends AppCompatActivity {

    static final int MANAGE_VOLUNTEER_REQUEST = 0;
    static final int MANAGE_CARS_REQUEST = 1;
    static final int MANAGE_HOURS_REQUEST = 2;

    Volunteer volunteer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer);

        volunteer = new Volunteer();
        volunteer = getIntent().getExtras().getParcelable("volunteer");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case MANAGE_VOLUNTEER_REQUEST:
                if(resultCode == RESULT_OK)
                {
                    this.volunteer = data.getParcelableExtra("volunteer");
                }
                break;

            case MANAGE_CARS_REQUEST:
                if(resultCode == RESULT_OK)
                {
                    ArrayList<Car> cars = data.getParcelableArrayListExtra("cars");
                    volunteer.setCars(cars);
                }
                break;

            case MANAGE_HOURS_REQUEST:
                if(resultCode == RESULT_OK)
                {
                    ArrayList<AvailableHours> hours = data.getParcelableArrayListExtra("hours");
                    volunteer.setAvailableHours(hours);
                }
                break;

        }
    }

    public void goEditVol(View view) {
        Intent go_edit = new Intent(this, ActivityVolunteerEdit.class);
        go_edit.putExtra("volunteer", volunteer);
        startActivityForResult(go_edit, MANAGE_VOLUNTEER_REQUEST);
    }

    public void goEditCars(View view) {
        Intent go_cars = new Intent(this, ActivityCarsEdit.class);
        go_cars.putExtra("cars", volunteer.getCars());
        startActivityForResult(go_cars, MANAGE_CARS_REQUEST);
    }


    public void goAddHoursVol(View view) {
        Intent go_add_hours = new Intent(this, ActivityHoursEdit.class);
        go_add_hours.putExtra("hours", volunteer.getAvailableHours());
        startActivityForResult(go_add_hours, MANAGE_HOURS_REQUEST);
    }

    public void callVol(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + volunteer.getPhoneNumber()));
        startActivity(intent);
    }

    public void sendSMSVol(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + volunteer.getPhoneNumber()));
        startActivity(intent);
    }
}
