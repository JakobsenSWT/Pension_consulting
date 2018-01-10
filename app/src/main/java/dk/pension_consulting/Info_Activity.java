package dk.pension_consulting;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dk.pension_consulting.Contact_Fragments.*;

public class Info_Activity extends AppCompatActivity{


    private TabLayout tabLayout;
    private ProgressBar progressBar;
    public ViewPager viewPagerFragment;
    private ViewPagerAdapter viewPagerAdapter;
    public FragmentViewPagerAdapter fragmentViewPagerAdapter;

    private int [] layout;
    private int current;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);


        tabLayout = findViewById(R.id.tabs);

        viewPagerFragment = findViewById(R.id.view_pager);
        fragmentViewPagerAdapter = new FragmentViewPagerAdapter(getSupportFragmentManager());
        fragmentViewPagerAdapter.addFragment(new Info_Fragment(), "Information");
        fragmentViewPagerAdapter.addFragment(new Contact_Fragment(), "Kontakt");
        viewPagerFragment.setAdapter(fragmentViewPagerAdapter);
        viewPagerFragment.addOnPageChangeListener(viewPagerPageChangeListenerFragment);

        tabLayout.setupWithViewPager(viewPagerFragment);


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
        public final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public FragmentViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    private class ViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public ViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layout[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layout.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
