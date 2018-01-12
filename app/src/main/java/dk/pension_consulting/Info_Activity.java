package dk.pension_consulting;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import dk.pension_consulting.Contact_Fragments.Contact_Fragment;
import dk.pension_consulting.Contact_Fragments.Info_Fragment;

public class Info_Activity extends AppCompatActivity{

    private Toolbar toolbar;
    private TabLayout tabLayout;
    public ViewPager viewPagerFragment;
    public FragmentViewPagerAdapter fragmentViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        toolbar = findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.Contact_Header);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tabLayout = findViewById(R.id.tabs);

        viewPagerFragment = findViewById(R.id.view_pager);
        fragmentViewPagerAdapter = new FragmentViewPagerAdapter(getSupportFragmentManager());
        fragmentViewPagerAdapter.addFragment(new Info_Fragment(), "Information");
        fragmentViewPagerAdapter.addFragment(new Contact_Fragment(), "Kontakt");
        viewPagerFragment.setAdapter(fragmentViewPagerAdapter);
        viewPagerFragment.addOnPageChangeListener(viewPagerPageChangeListenerFragment);

        tabLayout.setupWithViewPager(viewPagerFragment);

        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            Boolean contactOrNot = extra.getBoolean("Contact");
            if (contactOrNot) {
                viewPagerFragment.setCurrentItem(fragmentViewPagerAdapter.getCount());
            }
        }
    }


    ViewPager.OnPageChangeListener viewPagerPageChangeListenerFragment = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };



    public class FragmentViewPagerAdapter extends FragmentPagerAdapter {
        public final List<Fragment> FragmentList = new ArrayList<>();
        private final List<String> FragmentTitleList = new ArrayList<>();

        public FragmentViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return FragmentList.get(position);
        }

        @Override
        public int getCount() {
            return FragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            FragmentList.add(fragment);
            FragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return FragmentTitleList.get(position);
        }
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
