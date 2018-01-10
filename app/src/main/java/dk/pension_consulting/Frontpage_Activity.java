package dk.pension_consulting;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageButton;

public class Frontpage_Activity extends AppCompatActivity implements View.OnClickListener {

    ImageButton News, Guide, Info, Settings;

    private Handler mHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frontpage_);

        News = findViewById(R.id.news_btn);
        News.setOnClickListener(this);


        Guide = findViewById(R.id.guide_btn);
        Guide.setOnClickListener(this);

        Info = findViewById(R.id.Info_btn);
        Info.setOnClickListener(this);

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
        arc_layout.animate().rotation(180).setDuration(1000).setInterpolator(new AccelerateInterpolator());
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
            Intent i= new Intent(getApplicationContext(), Info_Activity.class);
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
            didTapButton(News);
            exit();
            mHandler.postDelayed(launchNews,1000);
        }

        else if (view == Guide)
        {
            didTapButton(Guide);
            exit();
            mHandler.postDelayed(launchTest,1000);
        }

        else if (view == Info)
        {
            didTapButton(Info);
            exit();
            mHandler.postDelayed(launchContact,1000);

        }

        else if (view == Settings)
        {
            didTapButton(Settings);
            exit();
            mHandler.postDelayed(launchSettings,1000);
        }
    }


    public void didTapButton(View view) {
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        if (view == News) {
            News.startAnimation(myAnim);
        }
        else if (view == Guide) {
            Guide.startAnimation(myAnim);
        }
        else if (view == Info) {
            Info.startAnimation(myAnim);
        }
        else if (view == Settings) {
            Settings.startAnimation(myAnim);
        }
    }



}
