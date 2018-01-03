package dk.pension_consulting;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dk.pension_consulting.Contact_Fragments.*;

public class Contact_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_);

        startfragment();
    }

    public void startfragment() {
        Fragment contact = new Contact_Fragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, contact).addToBackStack(null).commit();

        setContentView(R.layout.activity_contact_);
    }
}
