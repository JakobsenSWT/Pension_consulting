package dk.pension_consulting.Investment_Guide_Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import dk.pension_consulting.PrefManager;
import dk.pension_consulting.R;

/**
 * Created by Thomas-PC on 2/01/2018.
 */

public class Investment_Question3_Fragment extends Fragment implements View.OnClickListener {

    private PrefManager prefManager;

    private TextView Question;
    private RadioButton Answer1, Answer2, Answer3, Answer4;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_investment_question_portrait, container, false);

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
            case R.id.radioButton2:
                prefManager.setInvestmentValue3(5);
                break;
            case R.id.radioButton3:
                prefManager.setInvestmentValue3(3);
                break;
        }
    }

    public void startLayout () {
        Question.setText(R.string.Question3);
        Answer1.setText(R.string.Answer3_1);
        Answer2.setText(R.string.Answer3_2);
        Answer3.setVisibility(View.GONE);
        Answer4.setVisibility(View.GONE);

        Answer1.setOnClickListener(this);
        Answer2.setOnClickListener(this);
    }
}
