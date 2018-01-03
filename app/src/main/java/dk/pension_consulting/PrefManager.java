package dk.pension_consulting;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Thomas-PC on 3/01/2018.
 */

public class PrefManager {

    SharedPreferences preferences;
    SharedPreferences.Editor prefsEdit;
    Context _context;

    int PRIVATE_MODE = 0;

    private static final String Pref_file_name = "Pension_consult";
    private static final String First_launch = "IsItFirstLaunch";


    public PrefManager(Context context) {
        this._context = context;
        preferences = _context.getSharedPreferences(Pref_file_name, PRIVATE_MODE);
        prefsEdit = preferences.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        prefsEdit.putBoolean(First_launch, isFirstTime);
        prefsEdit.commit();
    }

    public boolean isFirstTimeLaunch() {
        return preferences.getBoolean(First_launch, true);
    }
}
