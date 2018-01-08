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

    private Fragment result_frag;

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private LinearLayout dotsLayout;

    private Button next, previous;
    private TextView [] dots;
    private ProgressBar progressBar;

    private ViewPager viewPagerIntro;
    private ViewPager viewPagerFragment;
    private ViewPagerAdapter viewPagerAdapter;
    private Investment_ViewPagerAdapter_Fragment fragmentViewPagerAdapter;

    private int [] layout;
    private int current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investment_guide_);

        prefManager = new PrefManager(this);

        toolbar = findViewById(R.id.toolbar_actionbar);
        tabLayout = findViewById(R.id.tabs);
        progressBar = findViewById(R.id.progressBar);
        next = findViewById(R.id.next_button);
        previous = findViewById(R.id.previous_button);

        next.setText(R.string.Next);

        if (!prefManager.getIsFirstTimeLaunch()) {

            previous.setVisibility(View.GONE);
            previous.setText(R.string.Previus);

            viewPagerFragment = findViewById(R.id.view_pager);
            fragmentViewPagerAdapter = new Investment_ViewPagerAdapter_Fragment(getSupportFragmentManager());
                fragmentViewPagerAdapter.addFragment(new Investment_Question1_Fragment(), "I");
                fragmentViewPagerAdapter.addFragment(new Investment_Question2_Fragment(), "II");
                fragmentViewPagerAdapter.addFragment(new Investment_Question3_Fragment(), "III");
                fragmentViewPagerAdapter.addFragment(new Investment_Question4_Fragment(), "IV");
            viewPagerFragment.setAdapter(fragmentViewPagerAdapter);
            viewPagerFragment.addOnPageChangeListener(viewPagerPageChangeListenerFragment);

            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(R.string.Investment_Header);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

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
                    }
                }
            });

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    current = viewPagerFragment.getCurrentItem();
                    if (current < fragmentViewPagerAdapter.getCount() - 1) {
                        viewPagerFragment.setCurrentItem(current + 1);
                        progressBar.setProgress(InvestmentProgress());
                    } else {
                        //Needs to start a brand new fragment

                        result_frag = new Investment_Result_Fragment();

                        getSupportFragmentManager().popBackStack();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, result_frag).commit();
                    }
                }
            });


        } else {

            prefManager.setFirstTimeLaunch(false);

            viewPagerIntro = findViewById(R.id.view_pager);
            dotsLayout = findViewById(R.id.layoutDots);

            previous.setText(R.string.Skip);

            layout = new int[] {
                R.layout.frag_investment_intro,
                R.layout.frag_investment_intro
            };

            addBottomDots(0);

            viewPagerIntro.setBackgroundColor(getResources().getColor(R.color.bg_screen1));
            viewPagerAdapter = new ViewPagerAdapter();
            viewPagerIntro.setAdapter(viewPagerAdapter);
            viewPagerIntro.addOnPageChangeListener(viewPagerPageChangeListenerIntro);

            toolbar.setVisibility(View.GONE);
            tabLayout.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);

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
            } else if (position < fragmentViewPagerAdapter.getCount() - 1) {
                next.setText(R.string.Next);
                previous.setVisibility(View.VISIBLE);
            } else {
                next.setText(R.string.Finish);
                previous.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public class Investment_ViewPagerAdapter_Fragment extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Investment_ViewPagerAdapter_Fragment(FragmentManager manager) {
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
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
