package dk.pension_consulting.News_Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

    private Toolbar toolbar;
    private ListView listView;
    private Dialog dialog;

    private List<News_Item_Values> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstance) {
        View view = inflater.inflate(R.layout.listview, container, false);

        dialog = new ProgressDialog(this.getActivity());
        dialog.setTitle(R.string.Loading);
        dialog.show();

        listView = view.findViewById(R.id.listView);
        toolbar = getActivity().findViewById(R.id.toolbar_actionbar);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.News_Header);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        try {
            News_Adapter adapter = new News_Adapter(this.getActivity());
            listView.setAdapter(adapter);
        } catch (Exception e) {
            AlertDialog dialog = new AlertDialog.Builder(view.getContext())
                    .create();
            dialog.setCancelable(false);
            dialog.setTitle(getString(R.string.Error_title));
            dialog.setMessage(getString(R.string.Error_common));
            dialog.setButton(view.getContext().getString(R.string.OK_text), new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    getActivity().finish();
                }
            });
            dialog.show();
        }

        startLayout();

        return view;
    }

    public void startLayout() {
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        dialog.dismiss();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        News_Item_Values test = (News_Item_Values) adapterView.getItemAtPosition(i);

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(test.url));
        startActivity(intent);
    }
}
