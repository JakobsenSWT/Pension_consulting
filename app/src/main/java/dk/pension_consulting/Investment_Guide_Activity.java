package dk.pension_consulting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dk.pension_consulting.Investment_Guide_Fragments.*;

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

        prefManager = new PrefManager(this);

        toolbar = findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().show();

        if (!prefManager.getIsFirstTimeLaunch()) {
            Fragment Guide = new Investment_Guide();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, Guide).commit();
        } else {
            prefManager.setFirstTimeLaunch(false);

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
