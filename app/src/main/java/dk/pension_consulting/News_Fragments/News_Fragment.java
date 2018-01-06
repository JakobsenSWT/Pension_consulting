package dk.pension_consulting.News_Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
                        import android.support.annotation.Nullable;
                        import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import dk.pension_consulting.R;

/**
 * Created by Thomas-PC on 3/01/2018.
 */

public class News_Fragment extends Fragment implements AdapterView.OnItemClickListener {

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    public ListView listView;

    private List <News_Item_Values> list;

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstance) {
        View view = inflater.inflate(R.layout.listview, container, false);

        listView = view.findViewById(R.id.listView);

        News_Adapter adapter = new News_Adapter(this.getActivity());
        listView.setAdapter(adapter);

        startLayout();
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        News_Item_Values test = (News_Item_Values) adapterView.getItemAtPosition(i);

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(test.url));
        startActivity(intent);
    }

    public void startLayout() {
        listView.setOnItemClickListener(this);
    }
}
