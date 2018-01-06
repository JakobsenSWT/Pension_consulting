package dk.pension_consulting;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import dk.pension_consulting.Investment_Guide_Fragments.*;

/**
 * Created by Thomas-PC on 2/01/2018.
 */

public class Investment_Guide_Activity extends AppCompatActivity {

    private PrefManager prefManager;

    private Fragment investment;

    private Button next, skip;
    private LinearLayout dotsLayout;
    private TextView [] dots;
    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;

    private int [] layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investment_guide_);

        prefManager = new PrefManager(this);
        prefManager.setFirstTimeLaunch(true);

        if (!prefManager.getIsFirstTimeLaunch()) {
            startfragment();
        } else {

            viewPager = findViewById(R.id.view_pager);
            dotsLayout = findViewById(R.id.layoutDots);
            next = findViewById(R.id.next_button);
            skip = findViewById(R.id.skip_button);

            layout = new int[] {
                R.layout.frag_investment_intro,
                R.layout.frag_investment_intro,
                R.layout.frag_investment_intro
            };

            addBottomDots(0);

            myViewPagerAdapter = new MyViewPagerAdapter();
            viewPager.setAdapter(myViewPagerAdapter);
            viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

            skip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startfragment();
                }
            });

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int current = getItem(+1);
                    if (current < layout.length) {
                        viewPager.setCurrentItem(current);
                    } else {
                        startfragment();
                    }
                }
            });

        }
    }

    public void startfragment() {
        switch (prefManager.getInvestmentProgress()) {
            case 1:
                investment = new Investment_Question1_Fragment();
                break;
            case 2:
                investment = new Investment_Question2_Fragment();
                break;
            case 3:
                investment = new Investment_Question3_Fragment();
                break;
            case 4:
                investment = new Investment_Question4_Fragment();
                break;
            default:
                investment = new Investment_Question1_Fragment();
        }

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, investment).addToBackStack(null).commit();

        setContentView(R.layout.activity_investment_guide_);

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
        return viewPager.getCurrentItem() + i;
    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            if (position == layout.length - 1) {
                next.setText(getString(R.string.GotIt));
                skip.setVisibility(View.GONE);
            } else {
                next.setText(getString(R.string.Next));
                skip.setVisibility(View.VISIBLE);
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
