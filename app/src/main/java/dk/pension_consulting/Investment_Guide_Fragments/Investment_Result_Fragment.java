package dk.pension_consulting.Investment_Guide_Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import dk.pension_consulting.Info_Activity;
import dk.pension_consulting.PrefManager;
import dk.pension_consulting.R;

/**
 * Created by Thomas-PC on 2/01/2018.
 */

public class Investment_Result_Fragment extends Fragment implements View.OnClickListener {

    private PrefManager prefManager;

    private TextView resultText;
    private Button previous, send;

    private float result;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_investment_result, container, false);

        prefManager = new PrefManager(this.getActivity());

        resultText = view.findViewById(R.id.textView_result);

        previous = view.findViewById(R.id.previous_button);
        send = view.findViewById(R.id.send_button);

        startLayout();
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.previous_button:
                this.getActivity().finish();
                break;
            case R.id.send_button:
                this.getActivity().finish();
                Intent i = new Intent(this.getActivity(), Info_Activity.class);
                i.putExtra("Result_score", result);
                i.putExtra("Investment_experience", prefManager.getInvestmentKnowledge());
                i.putExtra("Contact", true);
                startActivity(i);
                break;
        }
    }

    public void startLayout () {
        this.result = ((prefManager.getInvestmentValue1() * 17)
                + (prefManager.getInvestmentValue2() * 26)
                + (prefManager.getInvestmentValue3() * 57))
                    / 100;

        if (this.result > 3.48) {
            resultText.setText(R.string.InvestmentProfile_low);
        } else if (this.result <= 3.48 && this.result > 3.15) {
            resultText.setText(R.string.InvestmentProfile_medium);
        } else if (this.result <= 3.15 && this.result > 2.84) {
            resultText.setText(R.string.InvestmentProfile_average);
        } else if (this.result < 2.84) {
            resultText.setText(R.string.InvestmentProfile_high);
        }

        prefManager.setInvestmentResult(result);

        send.setText(R.string.Send);
        send.setOnClickListener(this);

        previous.setText(R.string.Finish);
        previous.setVisibility(View.VISIBLE);
        previous.setOnClickListener(this);
    }

}
