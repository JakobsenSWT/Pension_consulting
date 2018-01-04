package dk.pension_consulting;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dk.pension_consulting.Investment_Guide_Fragments.*;

/**
 * Created by Thomas-PC on 2/01/2018.
 */

public class Investment_Guide_Activity extends AppCompatActivity {

    private Fragment investment;
    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investment_guide_);

        prefManager = new PrefManager(this);
//        prefManager.setFirstTimeLaunch(true);

        startfragment();
    }

    public void startfragment() {
/*        if (prefManager.isFirstTimeLaunch()) {
            investment = new Investment_Intro_Fragment();
            prefManager.setFirstTimeLaunch(false);
        } else {
*/
            switch (prefManager.getInvestmentProgress()) {
                case 1:
                    investment = new Investment_Question1_Fragment();
                    break;
                case 2:

                case 3:

                case 4:
            }
 //       }

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, investment).addToBackStack(null).commit();

        setContentView(R.layout.activity_investment_guide_);

    }
}
