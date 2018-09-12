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

import io.objectbox.Box;

public class ActivityVolunteer extends AppCompatActivity {

    static final int MANAGE_VOLUNTEER_REQUEST = 0;
    static final int MANAGE_CARS_REQUEST = 1;
    static final int MANAGE_HOURS_REQUEST = 2;

    Volunteer volunteer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer);

        long id = getIntent().getExtras().getLong("volunteer");
        volunteer = DatabaseHelper.getInstance(this).getObject(Volunteer.class, id);
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
                    // TODO Adapt to ObjectBox
//                    ArrayList<Car> cars = data.getParcelableArrayListExtra("cars");
//                    volunteer.setCars(cars);
                }
                break;

            case MANAGE_HOURS_REQUEST:
                if(resultCode == RESULT_OK)
                {
                    // TODO Adapt to ObjectBox
//                    ArrayList<AvailableHours> hours = data.getParcelableArrayListExtra("hours");
//                    volunteer.setAvailableHours(hours);
                }
                break;

        }
    }

    public void goEditVol(View view) {
        Intent intent = new Intent(this, ActivityVolunteerEdit.class);
        intent.putExtra("volunteer", volunteer.getId());
        startActivityForResult(intent, MANAGE_VOLUNTEER_REQUEST);
    }

    public void goEditCars(View view) {
        Intent go_cars = new Intent(this, ActivityCarsEdit.class);
        // TODO Adapt to ObjectBox
//        go_cars.putExtra("cars", volunteer.getCars());
        startActivityForResult(go_cars, MANAGE_CARS_REQUEST);
    }


    public void goAddHoursVol(View view) {
        Intent go_add_hours = new Intent(this, ActivityHoursEdit.class);
        // TODO Adapt to ObjectBox
//        go_add_hours.putExtra("hours", volunteer.getAvailable_hours());
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
