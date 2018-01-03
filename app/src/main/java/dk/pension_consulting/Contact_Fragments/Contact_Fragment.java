package dk.pension_consulting.Contact_Fragments;

import android.support.v4.app.Fragment;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import dk.pension_consulting.R;

public class Contact_Fragment extends Fragment implements View.OnClickListener {

    String name, mail, subject, comment;

    private Button sendButton;
    private Spinner mySpinner;
    private ArrayAdapter<String> myAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_contact, container, false);

        sendButton = view.findViewById(R.id.button2);

        mySpinner = view.findViewById(R.id.spinner);

        myAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.contact_subjects));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        startLayout();

        return view;
    }

    public void onClick (View view) {
        if (view == sendButton) {

            setVariables(view);

            if (!name.equals("")) {
                if (!mail.equals("")) {
                    //tjek mail
                    try {
                        String [] mailPart = mail.split("@");
                        if (mailPart[1].equalsIgnoreCase("hotmail.com") || mailPart[1].equalsIgnoreCase("gmail.com") ||
                                mailPart[1].equalsIgnoreCase("live.dk") || mailPart[1].equalsIgnoreCase("yahoo.com")) {

                            sendMessageWithIntent(view);
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
        sendButton.setOnClickListener(this);
    }

    public void setVariables (View view) {
        EditText setName = view.findViewById(R.id.etName);
        EditText setSubject = view.findViewById(R.id.etMail);
        EditText setComment = view.findViewById(R.id.etComment);

        this.name = setName.getText().toString();
        this.mail = setSubject.getText().toString();
        this.subject = mySpinner.getSelectedItem().toString();
        this.comment = setComment.getText().toString();
    }

    public void sendMessageWithIntent(View view) {
        /* Create the Intent */
        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

        /* Fill it with Data */
        emailIntent.setType("plain/text");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"jakobsen1608@hotmail.com"});
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject:" + subject);
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Comment: " + comment + "From: " + name + " Mail: " + mail);

        /* Send it off to the Activity-Chooser */
        startActivity(Intent.createChooser(emailIntent, "Send mail..."));
    }

    public Dialog errorDialog (int ErrorType) {
        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .create();
        dialog.setCancelable(false);

        switch (ErrorType) {
            case 1:
                dialog.setTitle("Missing Name!");
                dialog.setMessage("You didn't enter a name");
                break;
            case 2:
                dialog.setTitle("Missing Mail!");
                dialog.setMessage("You didn't enter a mail");
                break;
            case 3:
                dialog.setTitle("Invalid Mail!" + name);
                dialog.setMessage("You entered a false e-mail! \nPlease enter your e-mail");
                break;
        }

        dialog.setButton(getActivity().getString(R.string.OK_text), new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        return dialog;
    }

    // Everything below is created to send email without intent.
    public void sendMessage () {

    }

    public class sendMailTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                sendMessage();
            } catch (Exception e) {

            }
            return null;
        }
    }

}

