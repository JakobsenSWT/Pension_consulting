package dk.pension_consulting.News_Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
                        import android.support.annotation.Nullable;
                        import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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

    private List <News_Item_Values> list;

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstance) {
        View view = inflater.inflate(R.layout.listview, container, false);

        listView = view.findViewById(R.id.listView);
        toolbar = getActivity().findViewById(R.id.toolbar_actionbar);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.News_Header);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        try {
            new getNewsFeedAsync(this.getActivity()).execute();
            return view;
        } catch (Exception e) {
            e.printStackTrace();

            AlertDialog dialog = new AlertDialog.Builder(view.getContext())
                    .create();
            dialog.setCancelable(false);
            dialog.setTitle(getString(R.string.Error_title));
            dialog.setMessage(getString(R.string.Error_common));
            dialog.setButton(view.getContext().getString(R.string.OK_text), new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }

        return view;
    }

    public void startLayout() {
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        News_Item_Values test = (News_Item_Values) adapterView.getItemAtPosition(i);

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(test.url));
        startActivity(intent);
    }

    class getNewsFeedAsync extends AsyncTask<Object, Void, String> {

        private Context context;

        getNewsFeedAsync(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(context);
            dialog.setTitle(R.string.Loading);
            dialog.show();
        }

        @Override
        protected String doInBackground(Object... params) {
                News_Adapter adapter = new News_Adapter(context);
                listView.setAdapter(adapter);
                startLayout();
            return null;
        }

        @Override
        protected void onPostExecute (String result) {
            dialog.dismiss();
        }

    }

}
