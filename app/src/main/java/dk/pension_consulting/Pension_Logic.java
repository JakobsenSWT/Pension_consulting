package dk.pension_consulting;

/**
 * Created by Thomas-PC on 2/01/2018.
 */

class Pension_Logic {
    private static final Pension_Logic ourInstance = new Pension_Logic();

    static Pension_Logic getInstance() {
        return ourInstance;
    }

    private Pension_Logic() {
    }
}
