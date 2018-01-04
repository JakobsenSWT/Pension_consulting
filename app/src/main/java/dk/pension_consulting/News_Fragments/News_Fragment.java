package dk.pension_consulting.News_Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import dk.pension_consulting.R;

/**
 * Created by Thomas-PC on 3/01/2018.
 */

public class News_Fragment extends Fragment implements View.OnClickListener {

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    public ListView listView;


    private List <TestAdapter> list;

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstance) {
        View view = inflater.inflate(R.layout.frag_news, container, false);

        listView = view.findViewById(R.id.listView);
        News_Adapter adapter = new News_Adapter(this.getActivity());
        listView.setAdapter(adapter);

        startLayout();
        return view;

    }

    @Override
    public void onClick(View v) {

    }

    public void startLayout() {

    }
}
