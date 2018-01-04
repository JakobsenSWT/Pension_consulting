package dk.pension_consulting.Investment_Guide_Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

import dk.pension_consulting.PrefManager;
import dk.pension_consulting.R;

/**
 * Created by Thomas-PC on 2/01/2018.
 */

public class Investment_Question1_Fragment extends Fragment implements View.OnClickListener {

    private PrefManager prefManager;

    private Fragment Question2;

    private TextView Question;
    private RadioButton Answer1, Answer2, Answer3, Answer4;
    private Button previus, next;
    private ProgressBar Bar;

    private int InvestmentValue;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_investment_4answers, container, false);

        prefManager = new PrefManager(this.getActivity());

        Question = view.findViewById(R.id.question_textView);

        Answer1 = view.findViewById(R.id.radioButton1);
        Answer2 = view.findViewById(R.id.radioButton2);
        Answer3 = view.findViewById(R.id.radioButton3);
        Answer4 = view.findViewById(R.id.radioButton4);

        previus = view.findViewById(R.id.previus_button);
        next = view.findViewById(R.id.next_button);

        startLayout();
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.radioButton1:
                this.InvestmentValue = 1;
                break;
            case R.id.radioButton2:
                this.InvestmentValue = 2;
                break;
            case R.id.radioButton3:
                this.InvestmentValue = 3;
                break;
            case R.id.radioButton4:
                this.InvestmentValue = 4;
                break;
            case R.id.previus_button:
                break;
            case R.id.next_button:
                prefManager.setInvestmentValue1(this.InvestmentValue);
                goToNextPage();
                break;
        }
    }

    public void startLayout () {
        Question.setText(R.string.Question1);
        Answer1.setText(R.string.Answer1_1);
        Answer2.setText(R.string.Answer1_2);
        Answer3.setText(R.string.Answer1_3);
        Answer4.setText(R.string.Answer1_4);

        previus.setText(R.string.Exit);
        next.setText(R.string.Next);

        Answer1.setOnClickListener(this);
        Answer2.setOnClickListener(this);
        Answer3.setOnClickListener(this);
        Answer4.setOnClickListener(this);

        previus.setOnClickListener(this);
        next.setOnClickListener(this);
    }

    public void goToNextPage () {
        Question2 = new Investment_Question2_Fragment();

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, Question2).commit();
    }
}
