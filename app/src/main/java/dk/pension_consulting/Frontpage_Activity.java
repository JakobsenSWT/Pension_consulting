package dk.pension_consulting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;



public class Frontpage_Activity extends AppCompatActivity implements View.OnClickListener {

    ImageButton News, Test, Contact, settings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frontpage_);

        News = findViewById(R.id.news_btn);
        News.setOnClickListener(this);

        Test = findViewById(R.id.guide_btn);
        Test.setOnClickListener(this);

        Contact = findViewById(R.id.contact_btn);
        Contact.setOnClickListener(this);

        settings = findViewById(R.id.settings_btn);
        settings.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == News)
        {
            Intent i= new Intent(this, News_Activity.class);
            startActivity(i);
        }

        else if (view == Test)
        {
            Intent i= new Intent(this, Investment_Guide_Activity.class);
            startActivity(i);
        }

        else if (view == Contact)
        {
            Intent i= new Intent(this, Contact_Activity.class);
            startActivity(i);
        }

        else if (view == settings)
        {
            Intent i= new Intent(this, Settings_Activity.class);
            startActivity(i);
        }
    }
}
