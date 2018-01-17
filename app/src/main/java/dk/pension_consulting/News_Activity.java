package dk.pension_consulting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import dk.pension_consulting.News_Fragments.News_Fragment;

public class News_Activity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_);

        toolbar = findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(toolbar);

        startfragment();
    }

    public void startfragment() {
        Fragment news = new News_Fragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, news).addToBackStack(null).commit();

        setContentView(R.layout.activity_news_);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.news_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_notifications:
                Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
                return(true);
    }
        return(super.onOptionsItemSelected(item));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed () {
        finish();
    }

}
