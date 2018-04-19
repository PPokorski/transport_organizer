package projekt_java.organizator_dostaw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;

public class VolunteersHours extends AppCompatActivity {

    int number_on_list;
    private TimePicker timePicker1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteers_hours);

        timePicker1 = (TimePicker) findViewById(R.id.timePicker_vol);

        number_on_list =  getIntent().getExtras().getInt("number_on_vol_list",-1); //wczytanie jaki miał numer na liście
    }

    //obsluga time_picker'a
    //https://www.tutorialspoint.com/android/android_timepicker_control.htm

    public void setHoursVol(View view){
        //int hour = timePicker1.getHour();
        //int min = timePicker1.getMinute();

        Intent go_indirect = new Intent(this, VolunteersIndirectActivity.class);
        go_indirect.putExtra("number_on_vol_list", number_on_list);
        startActivity(go_indirect);
    }

}
