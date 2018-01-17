package dk.pension_consulting;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import dk.pension_consulting.Advice_Fragments.*;

public class Advice_Activity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice);

        toolbar = findViewById(R.id.toolbar_actionbar);

        setSupportActionBar(toolbar);

        startfragment();
    }




    public void startfragment() {
        Fragment advice = new Advice_Fragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, advice).addToBackStack(null).commit();

        setContentView(R.layout.activity_advice);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed () {
        finish();
    }


}
