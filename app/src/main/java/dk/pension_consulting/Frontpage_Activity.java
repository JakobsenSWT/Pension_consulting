package dk.pension_consulting;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;

public class Frontpage_Activity extends AppCompatActivity implements View.OnClickListener {

    ImageButton News, Test, Contact, Settings;

    private Handler mHandler = new Handler();


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

        Settings = findViewById(R.id.settings_btn);
        Settings.setOnClickListener(this);



    }

    @Override
    protected void onStart () {
        super.onStart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                View arc_layout = findViewById(R.id.arc_layout);
                //arc_layout.setBackgroundResource(R.color.colorPrimaryDark);
                arc_layout.setPivotX(arc_layout.getWidth());
                arc_layout.setPivotY(arc_layout.getHeight()/2);
                arc_layout.setRotation(-180);
                arc_layout.animate().rotation(0).setDuration(1000).setInterpolator(new DecelerateInterpolator());
            }
        },100);
    }

    public void exit() {
        View arc_layout = findViewById(R.id.arc_layout);
     //   arc_layout.setBackgroundResource(R.color.colorPrimaryDark);
        arc_layout.setPivotX(arc_layout.getWidth());
        arc_layout.setPivotY(arc_layout.getHeight()/2);
        arc_layout.setRotation(0);
        arc_layout.animate().rotation(180).setDuration(1000).setInterpolator(new DecelerateInterpolator());
    }

    private Runnable launchNews = new Runnable() {
        @Override
        public void run() {
            Intent i= new Intent(getApplicationContext(), News_Activity.class);
            startActivity(i);
        }
    };

    private Runnable launchTest = new Runnable() {
        public void run() {
            Intent i= new Intent(getApplicationContext(), Investment_Guide_Activity.class);
            startActivity(i);
        }
    };

    private Runnable launchContact = new Runnable() {
        public void run() {
            Intent i= new Intent(getApplicationContext(), Contact_Activity.class);
            startActivity(i);
        }
    };

    private Runnable launchSettings = new Runnable() {
        public void run() {
            Intent i= new Intent(getApplicationContext(), Settings_Activity.class);
            startActivity(i);
        }
    };

    @Override
    public void onClick(View view) {
        if (view == News)
        {
            exit();
            mHandler.postDelayed(launchNews,1000);
        }

        else if (view == Test)
        {
            exit();
            mHandler.postDelayed(launchTest,1000);
        }

        else if (view == Contact)
        {
            exit();
            mHandler.postDelayed(launchContact,1000);

        }

        else if (view == Settings)
        {
            exit();
            mHandler.postDelayed(launchSettings,1000);
        }
    }





}
