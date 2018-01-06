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
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dk.pension_consulting.Investment_Guide_Fragments.*;

/**
 * Created by Thomas-PC on 2/01/2018.
 */

public class Investment_Guide_Activity extends AppCompatActivity {

    private PrefManager prefManager;

    private TabLayout tabLayout;
    private Button next, previous;
    private LinearLayout dotsLayout;
    private TextView [] dots;
    private ViewPager viewPagerIntro;
    private ViewPager viewPagerFragment;
    private MyViewPagerAdapter myViewPagerAdapter;

    private int [] layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investment_guide_);

        prefManager = new PrefManager(this);

        next = findViewById(R.id.next_button);
        previous = findViewById(R.id.previous_button);

        if (!prefManager.getIsFirstTimeLaunch()) {

            viewPagerFragment = findViewById(R.id.view_pager);
            setupViewPager(viewPagerFragment);

            tabLayout = findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(viewPagerFragment);

            previous.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //ToDo
                }
            });

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //ToDo
                }
            });


        } else {

            prefManager.setFirstTimeLaunch(false);

            tabLayout = findViewById(R.id.tabs);
            viewPagerIntro = findViewById(R.id.view_pager);
            dotsLayout = findViewById(R.id.layoutDots);

            tabLayout.setVisibility(View.GONE);

            layout = new int[] {
                R.layout.frag_investment_intro,
                R.layout.frag_investment_intro
            };

            addBottomDots(0);

            myViewPagerAdapter = new MyViewPagerAdapter();
            viewPagerIntro.setAdapter(myViewPagerAdapter);
            viewPagerIntro.addOnPageChangeListener(viewPagerPageChangeListener);

            previous.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    startActivity(getIntent());
                }
            });

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int current = getItem(+1);
                    if (current < layout.length) {
                        viewPagerIntro.setCurrentItem(current);
                    } else {
                        finish();
                        startActivity(getIntent());
                    }
                }
            });

        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Investment_Question1_Fragment(), "ONE");
        adapter.addFragment(new Investment_Question2_Fragment(), "TWO");
        adapter.addFragment(new Investment_Question3_Fragment(), "THREE");
        adapter.addFragment(new Investment_Question4_Fragment(), "FOUR");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
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

    private void addBottomDots(int currentPage) {
        dots = new TextView[layout.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[0]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[0]);
    }

    private int getItem(int i) {
        return viewPagerIntro.getCurrentItem() + i;
    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            if (position == layout.length - 1) {
                next.setText(getString(R.string.GotIt));
                previous.setVisibility(View.GONE);
            } else {
                next.setText(getString(R.string.Next));
                previous.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
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
        Intent i = new Intent(this, Frontpage_Activity.class);
        startActivity(i);
    }
}
