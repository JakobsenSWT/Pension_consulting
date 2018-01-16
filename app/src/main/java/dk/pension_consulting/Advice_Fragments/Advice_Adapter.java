package dk.pension_consulting.Advice_Fragments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import dk.pension_consulting.R;


/**
 * Created by jonathanlarsen on 16/01/2018.
 */

public class Advice_Adapter extends ArrayAdapter {

    private ArrayList <String> data;
    private Context context;

    public Advice_Adapter(@NonNull Context context, int resource, ArrayList<String> data) {
        super(context, resource, data);
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView (int position, @Nullable View view, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = layoutInflater.inflate(R.layout.single_item_advice, parent, false);

        TextView settings_title = rowView.findViewById(R.id.title_textView);

        settings_title.setText(data.get(position));

        return rowView;
    }

}
