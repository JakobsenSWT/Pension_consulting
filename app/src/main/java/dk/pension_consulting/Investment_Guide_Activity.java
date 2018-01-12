package dk.pension_consulting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import dk.pension_consulting.Investment_Guide_Fragments.Investment_Guide;
import dk.pension_consulting.Investment_Guide_Fragments.Investment_Intro_Fragment;
import dk.pension_consulting.Investment_Guide_Fragments.Investment_Result_Fragment;

/**
 * Created by Thomas-PC on 2/01/2018.
 */

public class Investment_Guide_Activity extends AppCompatActivity {

    private PrefManager prefManager;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investment_guide_);
/*
        if (savedInstanceState != null) {
            savedInstanceState.getInt("PageNumber");
        }
*/
        prefManager = new PrefManager(this);

        toolbar = findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().show();

        if (!prefManager.getIsFirstTimeLaunch() && prefManager.getInvestmentProgress() != 4) {
            Fragment Guide = new Investment_Guide();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, Guide).commit();
        } else if (prefManager.getInvestmentProgress() == 4){
            goToResult();
        } else {
            Fragment Intro = new Investment_Intro_Fragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, Intro).commit();
        }
    }

    public void goToGuide () {
        recreate();
    }

    public void goToResult () {
        Fragment Result = new Investment_Result_Fragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, Result).commit();
    }
/*
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }
*/
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
