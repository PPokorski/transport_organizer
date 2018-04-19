package projekt_java.organizator_dostaw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DeliveriesActivity extends AppCompatActivity {

    private ArrayAdapter<String> adapter ;
    ImageButton synchro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliveries);

        synchro = (ImageButton) findViewById(R.id.synchro_button);
        synchro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TUTAJ FUNKCJA AKTUALIZOWANIA - SYNCHRO
            }
        });

        ArrayList image_details = getListData();
        final ListView list = (ListView) findViewById(R.id.deliveries_list);
        list.setAdapter(new DeliveriesListAdapter(this, image_details));

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Intent start_edit = new Intent(DeliveriesActivity.this, DeliveriesIndirectActivity.class);
                start_edit.putExtra("number_on_del_list", position);
                startActivity(start_edit);
            }
        });
    }

    private ArrayList getListData() {
        ArrayList<DeliveriesRow> results = new ArrayList<DeliveriesRow>();
        /*DeliveriesRow opisKolumn = new DeliveriesRow();
        opisKolumn.setSkad("SKAD");
        opisKolumn.setNazwa("NAZWA");
        opisKolumn.setIlosc("ILOSC");
        opisKolumn.setKiedy_mozna("KIEDY MOZNA");
        opisKolumn.setStatus("STATUS");
        results.add(opisKolumn);*/

        DeliveriesRow pozycja1 = new DeliveriesRow();
        pozycja1.setNazwa("Karp");
        pozycja1.setIlosc("5,5kg");
        pozycja1.setStatus("brak");
        pozycja1.setSkad("ul. Postępu 2, Mokotów");
        pozycja1.setKiedy_mozna("czwartek 7-15, piątek 10-12");
        results.add(pozycja1);

        // TUTAJ DODAWAC WIERSZE DO LISTY

        return results;
    }

    public void go_new(View view){
        Intent start_edit = new Intent(DeliveriesActivity.this, DeliveriesEditActivity.class);
        start_edit.putExtra("number_on_vol_list", -1);
        start_edit.putExtra("new_del", true);
        startActivity(start_edit);
    }

    public void backToMenu(View view){
        Intent start_edit = new Intent(this, Menu.class);
        startActivity(start_edit);
    }
}
