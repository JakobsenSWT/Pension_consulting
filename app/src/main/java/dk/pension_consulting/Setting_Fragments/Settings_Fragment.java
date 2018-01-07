package dk.pension_consulting.Setting_Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

    public ListView listView;
    public ArrayList <String> list = new ArrayList<String>() {{
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

        Settings_Adapter adapter = new Settings_Adapter(this.getActivity(), R.layout.single_item_settings, list);
        listView.setAdapter(adapter);

        startLayout();
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        switch (position) {
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
