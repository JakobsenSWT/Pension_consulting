package dk.pension_consulting;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Thomas-PC on 3/01/2018.
 */

public class PrefManager {

    private SharedPreferences preferences;
    private SharedPreferences.Editor prefsEdit;
    private Context _context;

    private int PRIVATE_MODE = 0;

    private final String Pref_file_name = "Pension_consult";
    private final String First_launch = "IsItFirstLaunch";
    private final String Investment_Progress = "CurrentQuestion";
    private final String Investment_Value1 = "Weight_Question1";
    private final String Investment_Value2 = "Weight_Question2";
    private final String Investment_Value3 = "Weight_Question3";
    private final String Investment_Knowledge = "Weight_Question4";
    private final String Investment_Result = "TheActualResult";
    private final String NotificationKey = "NotificationOnOff";


    public PrefManager(Context context) {
        this._context = context;
        preferences = this._context.getSharedPreferences(Pref_file_name, PRIVATE_MODE);
        prefsEdit = preferences.edit();
    }

    public void setFirstTimeLaunch (boolean isFirstTime) {
        prefsEdit.putBoolean(First_launch, isFirstTime);
        prefsEdit.commit();
    }

    public boolean getIsFirstTimeLaunch() {
        return preferences.getBoolean(First_launch, true);
    }

    public void setInvestmentProgress (int currentProgress) {
        if (currentProgress == 0) {
            prefsEdit.putInt(Investment_Progress, currentProgress);
        } else
            prefsEdit.putInt(Investment_Progress, preferences.getInt(Investment_Progress, currentProgress) + 1);
        prefsEdit.commit();
    }

    public int getInvestmentProgress() {
        return preferences.getInt(Investment_Progress, 1);
    }

    public void setInvestmentValue1 (float value) {
        prefsEdit.putFloat(Investment_Value1, value);
        prefsEdit.commit();
    }

    public float getInvestmentValue1 () {
        return preferences.getFloat(Investment_Value1, 0);
    }

    public void setInvestmentValue2 (float value) {
        prefsEdit.putFloat(Investment_Value2, value);
        prefsEdit.commit();
    }

    public float getInvestmentValue2 () {
        return preferences.getFloat(Investment_Value2, 0);
    }

    public void setInvestmentValue3 (float value) {
        prefsEdit.putFloat(Investment_Value3, value);
        prefsEdit.commit();
    }

    public float getInvestmentValue3 () {
        return preferences.getFloat(Investment_Value3, 0);
    }

    public void setInvestmentKnowledge (int value) {
        prefsEdit.putInt(Investment_Knowledge, value);
        prefsEdit.commit();
    }

    public int getInvestmentKnowledge () {
        return preferences.getInt(Investment_Knowledge, 0);
    }

    public void setInvestmentResult (float value) {
        prefsEdit.putFloat(Investment_Result, value);
        prefsEdit.commit();
    }

    public boolean getNotificationEnabled() {
        return preferences.getBoolean(NotificationKey, true);
    }

    public void setNotificationEnabled(Boolean value) {
        prefsEdit.putBoolean(NotificationKey, value);
        prefsEdit.commit();
    }
}
