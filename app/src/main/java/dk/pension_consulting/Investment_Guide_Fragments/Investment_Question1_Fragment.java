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

import dk.pension_consulting.R;

/**
 * Created by Thomas-PC on 2/01/2018.
 */

public class Investment_Question1_Fragment extends Fragment implements View.OnClickListener {

    private TextView Question, text1, text2, text3, text4;
    private RadioButton Answer1, Answer2, Answer3, Answer4;
    private Button previus, next;
    private ProgressBar Bar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_investment_4answers, container, false);

        Question = view.findViewById(R.id.question_textView);
        text1 = view.findViewById(R.id.textViewR1);
        text2 = view.findViewById(R.id.textViewR2);
        text3 = view.findViewById(R.id.textViewR3);
        text4 = view.findViewById(R.id.textViewR4);

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
                break;
            case R.id.radioButton2:
                break;
            case R.id.radioButton3:
                break;
            case R.id.radioButton4:
                break;
            case R.id.previus_button:
                break;
            case R.id.next_button:
                break;
        }
    }

    public void startLayout () {
        Question.setText(R.string.Question1);
        text1.setText(R.string.Answer1_1);
        text2.setText(R.string.Answer1_2);
        text3.setText(R.string.Answer1_3);
        text4.setText(R.string.Answer1_4);

        previus.setText(R.string.Exit);
        next.setText(R.string.Next);
    }
}
