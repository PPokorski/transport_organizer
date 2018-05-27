package com.github.ppokorski.transport_organizer;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.github.ppokorski.transport_organizer.models.AvailableHours;
import com.github.ppokorski.transport_organizer.models.Transport;

import java.util.ArrayList;

public class ActivityDelivery extends AppCompatActivity {

    static final int MANAGE_TRANSPORT_REQUEST = 0;
    static final int MANAGE_HOURS_REQUEST = 1;

    Transport transport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        transport = new Transport();
        transport = getIntent().getExtras().getParcelable("transport");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case MANAGE_TRANSPORT_REQUEST:
                if(resultCode == RESULT_OK)
                {
                    Transport transport = data.getParcelableExtra("transport");
                }
                break;

            case MANAGE_HOURS_REQUEST:
                if(resultCode == RESULT_OK)
                {
                    ArrayList<AvailableHours> hours = data.getParcelableArrayListExtra("hours");
                    transport.setAvailableHours(hours);
                }
                break;
        }
    }

    public void goEditDel(View view) {
        Intent go_edit = new Intent(this, ActivityDeliveryEdit.class);
        go_edit.putExtra("transport", transport);
        startActivityForResult(go_edit, MANAGE_TRANSPORT_REQUEST);
    }

    public void goAddHoursDel(View view) {
        Intent go_add_hours = new Intent(this, ActivityHoursEdit.class);
        go_add_hours.putExtra("hours", transport.getAvailableHours());
        startActivityForResult(go_add_hours, MANAGE_HOURS_REQUEST);
    }

    public void callDel(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + transport.getReporterPhoneNumber()));
        startActivity(intent);
    }

    public void sendSMSDel(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + transport.getReporterPhoneNumber()));
        startActivity(intent);
    }
}
