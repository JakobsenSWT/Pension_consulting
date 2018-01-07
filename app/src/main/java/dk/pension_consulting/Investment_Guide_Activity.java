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
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dk.pension_consulting.Investment_Guide_Fragments.*;

/**
 * Created by Thomas-PC on 2/01/2018.
 */

public class Investment_Guide_Activity extends AppCompatActivity {

    private PrefManager prefManager;

    private Fragment result_frag;

    private TabLayout tabLayout;
    private LinearLayout dotsLayout;

    private Button next, previous;
    private TextView [] dots;
    private ProgressBar progressBar;

    private ViewPager viewPagerIntro;
    private ViewPager viewPagerFragment;
    private ViewPagerAdapter viewPagerAdapter;
    private FragmentViewPagerAdapter fragmentViewPagerAdapter;

    private int [] layout;
    private int current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investment_guide_);

        prefManager = new PrefManager(this);

        progressBar = findViewById(R.id.progressBar);

        next = findViewById(R.id.next_button);
        previous = findViewById(R.id.previous_button);

        tabLayout = findViewById(R.id.tabs);

        if (!prefManager.getIsFirstTimeLaunch()) {

            previous.setVisibility(View.GONE);

            viewPagerFragment = findViewById(R.id.view_pager);
            fragmentViewPagerAdapter = new FragmentViewPagerAdapter(getSupportFragmentManager());
                fragmentViewPagerAdapter.addFragment(new Investment_Question1_Fragment(), "ONE");
                fragmentViewPagerAdapter.addFragment(new Investment_Question2_Fragment(), "TWO");
                fragmentViewPagerAdapter.addFragment(new Investment_Question3_Fragment(), "THREE");
                fragmentViewPagerAdapter.addFragment(new Investment_Question4_Fragment(), "FOUR");
            viewPagerFragment.setAdapter(fragmentViewPagerAdapter);
            viewPagerFragment.addOnPageChangeListener(viewPagerPageChangeListenerFragment);

            tabLayout.setupWithViewPager(viewPagerFragment);

            previous.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    current = viewPagerFragment.getCurrentItem();
                    if (current < 1) {

                    } else if (current < fragmentViewPagerAdapter.getCount()) {
                        viewPagerFragment.setCurrentItem(current - 1);
                    } else {
                        finish();
                        Intent i = new Intent(getBaseContext(), Frontpage_Activity.class);
                        startActivity(i);
                    }
                }
            });

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    current = viewPagerFragment.getCurrentItem();
                    if (current < fragmentViewPagerAdapter.getCount()) {
                        viewPagerFragment.setCurrentItem(current + 1);
                        progressBar.setProgress(InvestmentProgress());
                    } else {
                        result_frag = new Investment_Result_Fragment();

                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, result_frag).commit();
                    }
                }
            });


        } else {

            prefManager.setFirstTimeLaunch(false);

            viewPagerIntro = findViewById(R.id.view_pager);
            dotsLayout = findViewById(R.id.layoutDots);

            layout = new int[] {
                R.layout.frag_investment_intro,
                R.layout.frag_investment_intro
            };

            addBottomDots(0);

            viewPagerAdapter = new ViewPagerAdapter();
            viewPagerIntro.setAdapter(viewPagerAdapter);
            viewPagerIntro.addOnPageChangeListener(viewPagerPageChangeListenerIntro);

            progressBar.setVisibility(View.GONE);
            tabLayout.setVisibility(View.GONE);

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
                    current = getItem(+1);
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

    //Fragments
    public int InvestmentProgress () {
        prefManager.setInvestmentProgress(0);

        if (prefManager.getInvestmentValue1() != 0) {
            prefManager.setInvestmentProgress(1);
        }
        if (prefManager.getInvestmentValue2() != 0) {
            prefManager.setInvestmentProgress(1);
        }
        if (prefManager.getInvestmentValue3() != 0) {
            prefManager.setInvestmentProgress(1);
        }
        if (prefManager.getInvestmentKnowledge() != 0) {
            prefManager.setInvestmentProgress(1);
        }

        return prefManager.getInvestmentProgress();
    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListenerFragment = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            progressBar.setProgress(InvestmentProgress());
        }

        @Override
        public void onPageSelected(int position) {
            if (position == 0) {
                previous.setVisibility(View.GONE);
            } else if (position == fragmentViewPagerAdapter.getCount()) {
                next.setText("Afslut");
                previous.setVisibility(View.VISIBLE);
            } else {
                next.setText(R.string.Next);
                previous.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private class FragmentViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
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

    //Intro
    private int getItem(int i) {
        return viewPagerIntro.getCurrentItem() + i;
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

    ViewPager.OnPageChangeListener viewPagerPageChangeListenerIntro = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

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
        public void onPageScrollStateChanged(int state) {

        }
    };

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
        Intent i = new Intent(this, Frontpage_Activity.class);
        startActivity(i);
    }
}
