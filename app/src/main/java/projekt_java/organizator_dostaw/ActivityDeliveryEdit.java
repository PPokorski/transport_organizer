package projekt_java.organizator_dostaw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ActivityDeliveryEdit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_edit);

        //załadowanie danych elementu nr X pobranego z docs'a
        int number_on_list =  getIntent().getExtras().getInt("number_on_del_list",-1);
        boolean new_del = getIntent().getExtras().getBoolean("new_del");

        TextView headline = (TextView) findViewById(R.id.headline_deliveries_edit);
        if(new_del){
            headline.setText(getResources().getString(R.string.edit_activity_new));
        }
        else
        {
            headline.setText(getResources().getString(R.string.edit_activity_edit));
        }

        //załadowanie danych elementu nr X pobranego z docs'a
        if(number_on_list!=-1) {
            TextView name = (TextView) findViewById(R.id.delivery_name);
            name.setText("xxx");
            TextView from = (TextView) findViewById(R.id.delivery_from);
            from.setText("xxx");
            TextView count = (TextView) findViewById(R.id.delivery_count);
            count.setText("xxx");
            TextView availability = (TextView) findViewById(R.id.delivery_availability);
            availability.setText("xxx");

            RadioGroup assignment = (RadioGroup) findViewById(R.id.delivery_assignment);
            //assignment.check(R.id.assigned);
            assignment.check(R.id.unassigned);
        }
    }

    public void confirmDelivery(View view){
        //edycja bezposrednio w docs'ie
        // po wcisnieciu przycisku, pola z layoutu z danymi zapisywane do docs'a

        onBackPressed();
    }
}
