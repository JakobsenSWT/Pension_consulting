package dk.pension_consulting.Investment_Guide_Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import dk.pension_consulting.Contact_Fragments.*;
import dk.pension_consulting.Frontpage_Activity;
import dk.pension_consulting.PrefManager;
import dk.pension_consulting.R;

/**
 * Created by Thomas-PC on 2/01/2018.
 */

public class Investment_Result_Fragment extends Fragment implements View.OnClickListener {

    private PrefManager prefManager;

    private Fragment contact;

    private TextView resultText;
    private Button previous, next;
    private ProgressBar Bar;

    private float result;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_investment_result, container, false);

        prefManager = new PrefManager(this.getActivity());

        resultText = view.findViewById(R.id.textView3);

        previous = this.getActivity().findViewById(R.id.previous_button);
        next = this.getActivity().findViewById(R.id.next_button);

        startLayout();
        return view;
    }

    @Override
    public void onClick(View v) {

    }

    public void startLayout () {
        this.result = ((prefManager.getInvestmentValue1() * 17)
                + (prefManager.getInvestmentValue2() * 26)
                + (prefManager.getInvestmentValue3() * 57))
                    / 100;

        //ToDo add strings in values/Strings
        if (this.result > 3.48) {
            resultText.setText("Meget lav investeringsprofil");
        } else if (this.result <= 3.48 && this.result > 3.15) {
            resultText.setText("Forsigtig investeringsprofil");
        } else if (this.result <= 3.15 && this.result > 2.84) {
            resultText.setText("Gennemsnitlig investeringsprofil");
        } else if (this.result < 2.84) {
            resultText.setText("Risikobetonet investeringsprofil");
        }

        prefManager.setInvestmentResult(result);
    }

}
