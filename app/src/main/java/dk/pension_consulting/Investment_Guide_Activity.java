package dk.pension_consulting;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dk.pension_consulting.Investment_Guide_Fragments.*;

public class Investment_Guide_Activity extends AppCompatActivity {

    private final String IntroKey = "FirstLaunch";

    private Fragment investment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investment_guide_);

        startfragment();
    }

    public void startfragment() {
 /*       if (Frontpage_Activity.preferences.getBoolean(IntroKey, true)) {

            investment = new Investment_Intro_Fragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, investment).addToBackStack(null).commit();

            setContentView(R.layout.frag_investment_intro);
        } else {
*/
            investment = new Investment_Question1_Fragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, investment).addToBackStack(null).commit();

            setContentView(R.layout.frag_investment_4answers);
 //       }

    }
}
