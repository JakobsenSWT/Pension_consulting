package dk.pension_consulting.Contact_Fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import dk.pension_consulting.Info_Activity;
import dk.pension_consulting.R;

public class Info_Fragment extends Fragment implements View.OnClickListener{


    FloatingActionButton contact;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_info, container, false);

        TextView text = view.findViewById(R.id.infotext);
        text.setText(Html.fromHtml(getString(R.string.infotxt)));


        contact = view.findViewById(R.id.FABcontact);
        contact.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == contact) {
            ((Info_Activity)getActivity()).viewPagerFragment.setCurrentItem(((Info_Activity)getActivity()).fragmentViewPagerAdapter.getCount() - 1);
        }
    }

}
