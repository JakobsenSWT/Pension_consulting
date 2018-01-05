package dk.pension_consulting.News_Fragments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import dk.pension_consulting.R;

/**
 * Created by Thomas-PC on 3/01/2018.
 */

public class News_Adapter extends ArrayAdapter<News_Item_Values> implements ValueEventListener {

    public News_Adapter(@NonNull Context context) {
        super(context, -1,  new ArrayList<News_Item_Values>());

        DatabaseReference articleRef = FirebaseDatabase.getInstance().getReference("example");
        articleRef.addValueEventListener(this);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        News_Item_Values test = getItem(position);
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = layoutInflater.inflate(R.layout.single_item_news, parent, false);

        TextView articleTitle = rowView.findViewById(R.id.title_view);
        TextView articleDate = rowView.findViewById(R.id.textViewDate);
        ImageView articlePicture = rowView.findViewById(R.id.imageViewPic);

        String url = test.url;
        articleTitle.setText(test.title);
        articleDate.setText(test.date);


        return rowView;
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        this.clear();
        for (DataSnapshot child : dataSnapshot.getChildren()) {
            this.add(child.getValue(News_Item_Values.class));
        }
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        System.out.println("Failed to retrieve Database data!");
        System.out.println(databaseError.getMessage());
        System.out.println(databaseError.getDetails());
    }
}
