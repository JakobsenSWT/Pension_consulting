package dk.pension_consulting.Advice_Fragments;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dk.pension_consulting.R;


/**
 * Created by jonathanlarsen on 16/01/2018.
 */

public class Advice_Adapter extends BaseExpandableListAdapter {

    private List<String> header;
    private Context context;
    private HashMap<String, List<String>> child;

    public Advice_Adapter(@NonNull Context context, List<String> header, HashMap<String, List<String>> child) {

        this.header = header;
        this.context = context;
        this.child = child;
    }


    @Override
    public int getGroupCount() {
        return header.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.child.get(header.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int arg0) {
        return header.get(arg0);
    }

    @Override
    public Object getChild(int parent, int s) {
        return child.get(header.get(parent)).get(s);
    }

    @Override
    public long getGroupId(int arg0) {
        return arg0;
    }

    @Override
    public long getChildId(int parent, int child) {
        return child;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int parent, boolean isExpanded, View view, ViewGroup parentview) {
        String grouptitle = (String) getGroup(parent);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.single_item_advice, parentview, false);
        }
        TextView parentTextView = (TextView) view.findViewById(R.id.textViewParent);
        parentTextView.setTypeface(null, Typeface.BOLD);
        parentTextView.setText(grouptitle);
        return view;
    }


    @Override
    public View getChildView(int parent, int child, boolean isLastChild, View view, ViewGroup parentview) {
        String childtext = (String) getChild(parent, child);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.child_item_advice, parentview, false);
        }
        TextView childTextView = (TextView) view.findViewById(R.id.textViewChild);
        childTextView.setText(childtext);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}