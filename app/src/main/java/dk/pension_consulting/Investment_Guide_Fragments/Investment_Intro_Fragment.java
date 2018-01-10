package dk.pension_consulting.Investment_Guide_Fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import dk.pension_consulting.Investment_Guide_Activity;
import dk.pension_consulting.R;

/**
 * Created by Thomas-PC on 10/01/2018.
 */

public class Investment_Intro_Fragment extends Fragment implements View.OnClickListener {

    private TabLayout tabs;
    private LinearLayout dotsLayout;
    private Button next, previous;
    private TextView [] dots;
    private ProgressBar progressBar;

    private int [] layout;
    private int current;

    private ViewPager viewPagerIntro;
    private ViewPagerAdapter viewPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_investment_viewpager, container, false);

        RelativeLayout Rlayout = (RelativeLayout) view.findViewById(R.id.content);
        Rlayout.setBackgroundColor(getResources().getColor(R.color.bg_screen1));

        viewPagerIntro = view.findViewById(R.id.view_pager);

        tabs = view.findViewById(R.id.tabs);
        dotsLayout = view.findViewById(R.id.layoutDots);
        progressBar = view.findViewById(R.id.progressBar);
        next = view.findViewById(R.id.next_button);
        previous = view.findViewById(R.id.previous_button);

        layout = new int[] {
                R.layout.frag_investment_intro,
                R.layout.frag_investment_intro
        };

        addBottomDots(0);

        viewPagerAdapter = new ViewPagerAdapter();
        viewPagerIntro.setAdapter(viewPagerAdapter);
        viewPagerIntro.addOnPageChangeListener(viewPagerPageChangeListenerIntro);
        viewPagerIntro.setBackgroundColor(getResources().getColor(R.color.bg_screen1));

        ((Investment_Guide_Activity)this.getActivity()).getSupportActionBar().hide();

        startLayout();

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.previous_button:
                ((Investment_Guide_Activity)getActivity()).goToGuide();
                break;
            case R.id.next_button:
                current = getItem(+ 1);
                if (current < layout.length) {
                    viewPagerIntro.setCurrentItem(current);
                } else {
                    ((Investment_Guide_Activity)getActivity()).goToGuide();                }
                break;
        }

    }

    private void startLayout () {
        previous.setText(R.string.Skip);
        next.setText(R.string.Next);

        tabs.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);

        previous.setOnClickListener(this);
        next.setOnClickListener(this);
    }

    private int getItem(int i) {
        return viewPagerIntro.getCurrentItem() + i;
    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[layout.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this.getActivity());
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
            layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

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

}
