package dk.pension_consulting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import github.hellocsl.cursorwheel.CursorWheelLayout;


public class Frontpage_Activity extends AppCompatActivity implements View.OnClickListener {

    Button News, Test, Contact, settings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frontpage_);

        News = findViewById(R.id.NewsButton);
        News.setOnClickListener(this);

        Test = findViewById(R.id.TestButton);
        Test.setOnClickListener(this);

        Contact = findViewById(R.id.ContactButton);
        Contact.setOnClickListener(this);

        settings = findViewById(R.id.button9);
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
