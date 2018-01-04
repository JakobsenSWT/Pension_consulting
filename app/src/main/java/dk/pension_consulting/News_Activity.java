package dk.pension_consulting;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dk.pension_consulting.News_Fragments.*;

public class News_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_);

        startfragment();
    }

    public void startfragment() {
        Fragment news = new News_Fragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, news).addToBackStack(null).commit();

        setContentView(R.layout.activity_news_);
    }
}
