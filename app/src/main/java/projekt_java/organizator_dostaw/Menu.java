package projekt_java.organizator_dostaw;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Menu extends AppCompatActivity {
    private List<List<String>> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void go_volunteers(View view) {
        Intent intent = new Intent(this, VolunteersActivity.class);
        startActivity(intent);
    }

    public void go_deliveries(View view) {
        Intent intent = new Intent(this, DeliveriesActivity.class);
        startActivity(intent);
    }

    static final int CHECK_IT = 1;
    public void update(View view) {
        //Intent intent = new Intent(this, UpdateActivity.class);
        //intent.putExtra(UpdateActivity.SHEET_URL_, "https://docs.google.com/spreadsheets/d/1Aihnh7DUlS25iw2PA63OfZOgHyScfKMnKZj1nzk1nwo/edit#gid=0");

        //startActivityForResult(intent, CHECK_IT);
    }

    @Override
    protected void onActivityResult(int request_code, int result_code, Intent data) {
        if(request_code == CHECK_IT) {
            if(result_code == RESULT_OK) {
                //list = (ArrayList<List<String>>) data.getSerializableExtra(UpdateActivity.LIST_NAME_);
            }
        }
    }
}
