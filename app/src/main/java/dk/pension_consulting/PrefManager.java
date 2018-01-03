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


    PrefManager(Context context) {
        this._context = context;
        preferences = this._context.getSharedPreferences(Pref_file_name, PRIVATE_MODE);
        prefsEdit = preferences.edit();
    }

    void setFirstTimeLaunch (boolean isFirstTime) {
        prefsEdit.putBoolean(First_launch, isFirstTime);
        prefsEdit.commit();
    }

    boolean isFirstTimeLaunch() {
        return preferences.getBoolean(First_launch, true);
    }

    void setInvestmentProgress (int currentProgress) {
        prefsEdit.putInt(Investment_Progress, currentProgress);
        prefsEdit.commit();
    }

    int investmentProgress() {
        return preferences.getInt(Investment_Progress, 1);
    }
}
