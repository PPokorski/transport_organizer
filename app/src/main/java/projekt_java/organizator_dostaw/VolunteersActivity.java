package projekt_java.organizator_dostaw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class VolunteersActivity extends AppCompatActivity {

    private ArrayAdapter<String> adapter ;
    ImageButton synchro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteers);

        synchro = (ImageButton) findViewById(R.id.synchro_button);
        synchro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TUTAJ FUNKCJA AKTUALIZOWANIA - SYNCHRO
            }
        });

        ArrayList image_details = getListData();
        final ListView list = (ListView) findViewById(R.id.volunteers_list);
        list.setAdapter(new VolunteersListAdapter(this, image_details));

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Intent go_indirect = new Intent(VolunteersActivity.this, VolunteersIndirectActivity.class);
                go_indirect.putExtra("number_on_vol_list", position);
                startActivity(go_indirect);
            }
        });
    }

    private ArrayList getListData() {
        ArrayList<VolunteersRow> results = new ArrayList<VolunteersRow>();
        /*VolunteersRow opisKolumn = new VolunteersRow();
        opisKolumn.setImie("IMIE");
        opisKolumn.setNazwisko("NAZWISKO");
        opisKolumn.setKontakt("KONTAKT");
        opisKolumn.setDostepnosc("DOSTEPNOSC");
        opisKolumn.setSamochod("SAMOCHOD");
        results.add(opisKolumn);*/


        VolunteersRow pozycja1 = new VolunteersRow();
        pozycja1.setImie("Marek");
        pozycja1.setNazwisko("Kowal");
        pozycja1.setKontakt("601622121");
        pozycja1.setDostepnosc("czwartek 10-17");
        pozycja1.setSamochod("Audi A4");
        results.add(pozycja1);
        // TUTAJ DODAWAC WIERSZE DO LISTY

        return results;
    }

    public void go_new(View view){
        Intent start_edit = new Intent(VolunteersActivity.this, VolunteersEditActivity.class);
        start_edit.putExtra("number_on_vol_list", -1);
        start_edit.putExtra("new_vol", true);
        startActivity(start_edit);
    }

    public void backToMenu(View view){
        Intent start_edit = new Intent(this, Menu.class);
        startActivity(start_edit);
    }
}
