package dk.pension_consulting.Contact_Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import dk.pension_consulting.R;

/**
 * Created by Thomas-PC on 2/01/2018.
 */

public class Contact_Fragment extends Fragment implements View.OnClickListener {

    private String name, mail, subject, comment;

    private TextView text;
    private EditText setName, setSubject, setComment;
    private Button sendButton;
    private Spinner mySpinner;
    private ArrayAdapter<String> myAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_contact, container, false);

        text = view.findViewById(R.id.textView2);

        sendButton = view.findViewById(R.id.button2);

        setName = view.findViewById(R.id.etName);
        setSubject = view.findViewById(R.id.etMail);
        setComment = view.findViewById(R.id.etComment);

        mySpinner = view.findViewById(R.id.spinner);

        myAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.contact_subjects));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        retrieveData(savedInstanceState);

        startLayout();

        return view;
    }

    public void onClick (View view) {
        if (view == sendButton) {

            setVariables();

            if (!name.equals("")) {
                if (!mail.equals("")) {
                    try {
                        String [] mailPart = mail.split("@");
                        if (mailPart[1].equalsIgnoreCase("hotmail.com") || mailPart[1].equalsIgnoreCase("gmail.com") ||
                                mailPart[1].equalsIgnoreCase("live.dk") || mailPart[1].equalsIgnoreCase("yahoo.com")) {

                            sendMessageWithIntent();
                        }
                    } catch (Exception e) {
                        errorDialog(3).show();
                    }
                } else {
                    errorDialog(2).show();
                }
            } else {
                errorDialog(1).show();
            }
        }
    }

    public void startLayout () {
        text.setText(Html.fromHtml(getString(R.string.Contact_txt)));

        sendButton.setOnClickListener(this);
    }

    public void setVariables () {
        name = setName.getText().toString();
        mail = setSubject.getText().toString();
        subject = mySpinner.getSelectedItem().toString();
        comment += setComment.getText().toString();
    }

    public void retrieveData (Bundle dataMap) {
        if (dataMap != null) {
            String s = dataMap.getString("Result_score");
            int i = dataMap.getInt("Investment_experience");

            comment = "Result score: " + s +
                    "\nInvestment experience: " + i +
                    "\n\n";
        }
    }

    public void sendMessageWithIntent() {

        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

        emailIntent.setType("plain/text");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"example@hotmail.com"});
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject:" + subject);
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Comment: " + comment + "From: " + name + " Mail: " + mail);

        startActivity(Intent.createChooser(emailIntent, "Send mail..."));
    }



    public Dialog errorDialog (int ErrorType) {
        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .create();
        dialog.setCancelable(false);

        switch (ErrorType) {
            case 1:
                dialog.setTitle(R.string.MissingName);
                dialog.setMessage(getText(R.string.MissNameTxt));
                break;
            case 2:
                dialog.setTitle(R.string.MissingMail);
                dialog.setMessage(getText(R.string.MissMailTxt));
                break;
            case 3:
                dialog.setTitle(R.string.InvalidMail);
                dialog.setMessage(getText(R.string.InvalidMailTxt));
                break;
        }

        dialog.setButton(getActivity().getString(R.string.OK_text), new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        return dialog;
    }
}
