package dk.pension_consulting.Investment_Guide_Fragments;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import dk.pension_consulting.PrefManager;
import dk.pension_consulting.R;

/**
 * Created by Thomas-PC on 2/01/2018.
 */

public class Investment_Question1_Fragment extends Fragment implements View.OnClickListener {

    private PrefManager prefManager;

    private FrameLayout frameLayout;
    private View view;
    private TextView Question;
    private RadioButton Answer1, Answer2, Answer3, Answer4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        frameLayout = new FrameLayout(getActivity());
        inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.frag_investment_question_portrait, null);
        frameLayout.addView(view);

        prefManager = new PrefManager(this.getActivity());

        Question = view.findViewById(R.id.question_textView);

        Answer1 = view.findViewById(R.id.radioButton1);
        Answer2 = view.findViewById(R.id.radioButton2);
        Answer3 = view.findViewById(R.id.radioButton3);
        Answer4 = view.findViewById(R.id.radioButton4);

        startLayout();
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.radioButton1:
                prefManager.setInvestmentValue1(1);
                break;
            case R.id.radioButton2:
                prefManager.setInvestmentValue1(2);
                break;
            case R.id.radioButton3:
                prefManager.setInvestmentValue1(3);
                break;
            case R.id.radioButton4:
                prefManager.setInvestmentValue1(4);
                break;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            frameLayout.removeAllViews();
            LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.frag_investment_question_landscape, null);

            Toast.makeText(this.getActivity(), "landscape", Toast.LENGTH_SHORT).show();

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            frameLayout. removeAllViews();
            LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.frag_investment_question_portrait, null);
            frameLayout .addView(view);

            Toast.makeText(this.getActivity(), "portrait", Toast.LENGTH_SHORT).show();
        }
    }

    public void startLayout () {
        Question.setText(R.string.Question1);
        Answer1.setText(R.string.Answer1_1);
        Answer2.setText(R.string.Answer1_2);
        Answer3.setText(R.string.Answer1_3);
        Answer4.setText(R.string.Answer1_4);

        Answer1.setOnClickListener(this);
        Answer2.setOnClickListener(this);
        Answer3.setOnClickListener(this);
        Answer4.setOnClickListener(this);
    }
}
