package projekt_java.organizator_dostaw;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DeliveriesIndirectActivity extends AppCompatActivity {

    int number_on_list;

    private String[] vols_array = {"Janek","Franek","Zosia"};
    private ListView available_vols_listView;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliveries_indirect);

        number_on_list =  getIntent().getExtras().getInt("number_on_del_list",-1); //wczytanie jaki miał numer na liście wybrany element

        available_vols_listView = (ListView) findViewById(R.id.available_vols);
        adapter = new ArrayAdapter<String>(this, R.layout.indirect_del_available_vols_row, vols_array);
        available_vols_listView.setAdapter(adapter);

        available_vols_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                //FUNKCJA WYWOŁYWANA PO NACIŚNIĘCIU ELEMENTU Z LISTY
            }
        });

    }

    public void goEditDel(View view) {
        Intent go_edit = new Intent(this, DeliveriesEditActivity.class);
        go_edit.putExtra("number_on_del_list", number_on_list);
        go_edit.putExtra("new_del", false);
        startActivity(go_edit);
    }

    public void goAddHoursDel(View view) {
        Intent go_add_hours = new Intent(this, DeliveriesHours.class);
        go_add_hours.putExtra("number_on_del_list", number_on_list);
        startActivity(go_add_hours);
    }

    public void callDel(View view) {
        //dzwonienie
    }

    public void sensSMSDel(View view) {
        //wysylanie sms'a
    }

    public void backToDelList(View view) {
        Intent intent = new Intent(this, DeliveriesActivity.class);
        startActivity(intent);
    }
}
