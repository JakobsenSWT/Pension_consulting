package dk.pension_consulting;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Handler;
import android.os.HardwarePropertiesManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.CallScreeningService;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.ogaclejapan.arclayout.ArcLayout;

import dk.pension_consulting.Animations_Frontpage.MyBounceInterpolator;
import io.fabric.sdk.android.Fabric;

public class Frontpage_Activity extends AppCompatActivity implements View.OnClickListener {

    private PrefManager prefManager;

    ImageButton News, Guide, Contact, Info, Settings;

    ImageView smallcircle, largecircle;

    private Handler mHandler = new Handler();

    private ArcLayout arcLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_frontpage_);

        boolean EMULATOR = Build.PRODUCT.contains("sdk") || Build.MODEL.contains("Emulator");
        if (!EMULATOR) {
            Fabric.with(this, new Crashlytics());
        }


        prefManager = new PrefManager(this);

        News = findViewById(R.id.news_btn);
        News.setOnClickListener(this);

        Contact = findViewById(R.id.Info_btn);
        Contact.setOnClickListener(this);

        Guide = findViewById(R.id.guide_btn);
        Guide.setOnClickListener(this);

        Settings = findViewById(R.id.settings_btn);
        Settings.setOnClickListener(this);

        Info = findViewById(R.id.imageButton_logoRings);
        Info.setOnClickListener(this);

        smallcircle = findViewById(R.id.semicirclewhite);
        largecircle = findViewById(R.id.semicirclered);

        arcLayout = findViewById(R.id.arc_layout);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float dpHeight = displayMetrics.heightPixels / displayMetrics.density;


        //XLarge
        if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) ==
                Configuration.SCREENLAYOUT_SIZE_XLARGE) {
            setScreenXLarge();
        }

        //Large
        else if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) ==
                Configuration.SCREENLAYOUT_SIZE_LARGE) {
            setScreenLarge();
            }

        //3.2" screen
        else if (dpHeight == 480) {
            setScreen32();
        }


        //Small
        else if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) ==
                Configuration.SCREENLAYOUT_SIZE_SMALL) {
            setScreenSmall();
        }

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
            News.setEnabled(true);
            Intent i= new Intent(getApplicationContext(), News_Activity.class);
            startActivity(i);
        }
    };

    private Runnable launchTest = new Runnable() {
        public void run() {
            prefManager.setInvestmentValue1(0);
            prefManager.setInvestmentValue2(0);
            prefManager.setInvestmentValue3(0);
            prefManager.setInvestmentKnowledge(0);
            prefManager.setInvestmentProgress(0);

            Guide.setEnabled(true);
            Intent i= new Intent(getApplicationContext(), Investment_Guide_Activity.class);
            startActivity(i);
        }
    };

    private Runnable launchContact = new Runnable() {
        public void run() {
            Contact.setEnabled(true);
            Intent i= new Intent(getApplicationContext(), Info_Activity.class);
            i.putExtra("Contact", true);
            startActivity(i);
        }
    };

    private Runnable launchSettings = new Runnable() {
        public void run() {
            Settings.setEnabled(true);
            Intent i= new Intent(getApplicationContext(), Settings_Activity.class);
            startActivity(i);
        }
    };

    @Override
    public void onClick(View view) {
        if (view == News)
        {
            News.setEnabled(false);
            didTapButton(News);
            exit();
            mHandler.postDelayed(launchNews,1000);
        }

        else if (view == Contact)
        {
            Contact.setEnabled(false);
            didTapButton(Contact);
            exit();
            mHandler.postDelayed(launchContact,1000);
        }

        else if (view == Guide)
        {
            Guide.setEnabled(false);
            didTapButton(Guide);
            exit();
            mHandler.postDelayed(launchTest,1000);
        }

        else if (view == Info)
        {
            didTapButton(Info);
            exit();
            Intent i= new Intent(getApplicationContext(), Info_Activity.class);
            startActivity(i);
        }

        else if (view == Settings)
        {
            Settings.setEnabled(false);
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
        else if (view == Contact) {
            Contact.startAnimation(myAnim);
        }
        else if (view == Info) {
            Info.startAnimation(myAnim);
        }
        else if (view == Settings) {
            Settings.startAnimation(myAnim);
        }
    }

    private void setScreenXLarge() {
        arcLayout.setAxisRadius(400);
        smallcircle.getLayoutParams().width=380;
        smallcircle.getLayoutParams().height=760;
        largecircle.getLayoutParams().width=420;
        largecircle.getLayoutParams().height=840;
    }

    private void setScreenLarge() {
        arcLayout.setAxisRadius(300);
        smallcircle.getLayoutParams().width=290;
        smallcircle.getLayoutParams().height=580;
        largecircle.getLayoutParams().width=310;
        largecircle.getLayoutParams().height=620;
    }

    private void setScreen32() {
        arcLayout.setAxisRadius(150);
        smallcircle.getLayoutParams().width=140;
        smallcircle.getLayoutParams().height=280;
        largecircle.getLayoutParams().width=160;
        largecircle.getLayoutParams().height=320;
    }

    private void setScreenSmall() {
        arcLayout.setAxisRadius(100);
        smallcircle.getLayoutParams().width=90;
        smallcircle.getLayoutParams().height=180;
        largecircle.getLayoutParams().width=110;
        largecircle.getLayoutParams().height=220;
    }



}
