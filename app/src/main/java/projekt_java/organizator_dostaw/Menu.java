package projekt_java.organizator_dostaw;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void go_volunteers(View view) {
        Intent intent = new Intent(this, ActivityVolunteers.class);
        startActivity(intent);
    }

    public void go_deliveries(View view) {
        Intent intent = new Intent(this, ActivityDeliveries.class);
        startActivity(intent);
    }
}
