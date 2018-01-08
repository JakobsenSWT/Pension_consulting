package dk.pension_consulting;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dk.pension_consulting.Setting_Fragments.*;

public class Settings_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        startfragment();
    }

    public void startfragment() {
        Fragment settings = new Settings_Fragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, settings).addToBackStack(null).commit();

        setContentView(R.layout.activity_settings);
    }

    @Override
    public void onBackPressed () {
        finish();
    }


}
