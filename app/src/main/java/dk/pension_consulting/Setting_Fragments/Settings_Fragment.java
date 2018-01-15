package dk.pension_consulting.Setting_Fragments;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import dk.pension_consulting.PrefManager;
import dk.pension_consulting.R;

/**
 * Created by Thomas-PC on 5/01/2018.
 */

public class Settings_Fragment extends Fragment implements AdapterView.OnItemClickListener {

    private PrefManager prefManager;

    private Toolbar toolbar;
    private ListView listView;
    private ArrayList <String> list = new ArrayList<String>() {{
        add("Notifikationer");
        add("Nulstil App");
        add("Hjælp");
    }};

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstance) {
        View view = inflater.inflate(R.layout.listview, container, false);

        prefManager = new PrefManager(this.getActivity());

        listView = view.findViewById(R.id.listView);
        toolbar = getActivity().findViewById(R.id.toolbar_actionbar);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.Settings_Header);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        Settings_Adapter adapter = new Settings_Adapter(this.getActivity(), R.layout.single_item_settings, list);
        listView.setAdapter(adapter);

        startLayout();
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        switch (position) {
            case 0:
                if (prefManager.getNotificationEnabled()) {
                    prefManager.setNotificationEnabled(false);
                    Toast.makeText(this.getActivity(), "Notifikationer: Off",
                            Toast.LENGTH_SHORT).show();
                } else {
                    prefManager.setNotificationEnabled(true);
                    Toast.makeText(this.getActivity(), "Notifikationer: On",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            case 1:
                prefManager.setFirstTimeLaunch(true);
                prefManager.setInvestmentValue1(0);
                prefManager.setInvestmentValue2(0);
                prefManager.setInvestmentValue3(0);
                prefManager.setInvestmentKnowledge(0);

                Toast.makeText(this.getActivity(), "Clicked",
                        Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this.getActivity(), "Clicked",
                        Toast.LENGTH_SHORT).show();
                break;
        }

    }

    public void startLayout() {
        listView.setOnItemClickListener(this);
    }
}
