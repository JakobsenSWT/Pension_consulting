package dk.pension_consulting.Investment_Guide_Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import dk.pension_consulting.Investment_Guide_Activity;
import dk.pension_consulting.PrefManager;
import dk.pension_consulting.R;

/**
 * Created by Thomas-PC on 10/01/2018.
 */

public class Investment_Guide extends Fragment implements View.OnClickListener {

    private PrefManager prefManager;

    private Toolbar toolbar;
    private ProgressBar progressBar;
    private Button next, previous;

//    private String key = "PageNumber";

    public ViewPager viewPagerFragment;
    private Investment_ViewPagerAdapter_Fragment fragmentViewPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_investment_viewpager, container, false);

        prefManager = new PrefManager(this.getActivity());

        toolbar = getActivity().findViewById(R.id.toolbar_actionbar);

        progressBar = view.findViewById(R.id.progressBar);
        previous = view.findViewById(R.id.previous_button);
        next = view.findViewById(R.id.next_button);

        viewPagerFragment = view.findViewById(R.id.view_pager);
        fragmentViewPagerAdapter = new Investment_ViewPagerAdapter_Fragment(getChildFragmentManager());
        fragmentViewPagerAdapter.addFragment(new Investment_Question1_Fragment(), "I");
        fragmentViewPagerAdapter.addFragment(new Investment_Question2_Fragment(), "II");
        fragmentViewPagerAdapter.addFragment(new Investment_Question3_Fragment(), "III");
        fragmentViewPagerAdapter.addFragment(new Investment_Question4_Fragment(), "IV");
        viewPagerFragment.setAdapter(fragmentViewPagerAdapter);
        viewPagerFragment.addOnPageChangeListener(viewPagerPageChangeListenerFragment);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.Investment_Header);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        startLayout();

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.previous_button:
                if (viewPagerFragment.getCurrentItem() != 0
                        && viewPagerFragment.getCurrentItem() < fragmentViewPagerAdapter.getCount()) {

                    viewPagerFragment.setCurrentItem(viewPagerFragment.getCurrentItem() - 1);
                }
                break;
            case R.id.next_button:
                progressBar.setProgress(InvestmentProgress());
                
                if(viewPagerFragment.getCurrentItem() == fragmentViewPagerAdapter.getCount() - 1
                        && progressBar.getProgress() == 4) {

                    ((Investment_Guide_Activity) getActivity()).goToResult();

                } else if (viewPagerFragment.getCurrentItem() < fragmentViewPagerAdapter.getCount()){
                    viewPagerFragment.setCurrentItem(viewPagerFragment.getCurrentItem() + 1);
                }
                break;
        }
    }
/*
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            viewPagerFragment.setCurrentItem(savedInstanceState.getInt(key));
            progressBar.setProgress(InvestmentProgress());
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);


    }
*/
    public void startLayout () {
        previous.setText(R.string.Previus);
        previous.setVisibility(View.GONE);
        previous.setOnClickListener(this);

        next.setText(R.string.Next);
        next.setOnClickListener(this);
    }

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
        private final List<Fragment> FragmentList = new ArrayList<>();
        private final List<String> FragmentTitleList = new ArrayList<>();

        public Investment_ViewPagerAdapter_Fragment(FragmentManager manager) {
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

}
