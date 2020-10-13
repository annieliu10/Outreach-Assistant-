package model;

import java.util.ArrayList;
import java.util.List;


//The industries we are contacting are referencing the AIESEC IGT package//

//300+ positions fulfilled in IT
//66+ positions fulfilled in BA
//48+ positions fulfilled in MA
//48+ positions fulfilled in ENG

//Represents a preferred order of industries for AIESEC
public class CompanyIndustryPreferenceOrder {
    //CONSTANTS
    private static final String IT = "Information Technology";
    private static final String BA = "Business Admin/ Project MGMT";
    private static final String MA = "Marketing";
    private static final String ENG = "Engineering";

    //FIELDS
    private List<String> preferences;


    //default order
    //EFFECTS: initiate the constructor with a default ordered list
    public CompanyIndustryPreferenceOrder() {
        preferences = new ArrayList<>();
        preferences.add(IT);
        preferences.add(BA);
        preferences.add(MA);
        preferences.add(ENG);
    }

    //REQUIRES: preferred1, preferred2, preferred3, and preferred4 have to be valid industries, reference the above
    //MODIFIES: this
    //EFFECTS: changes up the preference order based on the order of the inputs
    public void changePreferenceOrder(String preferred1, String preferred2, String preferred3, String preferred4) {
        preferences.set(0, preferred1);
        preferences.set(1, preferred2);
        preferences.set(2, preferred3);
        preferences.set(3, preferred4);
    }

    //EFFECTS: returns the list of preferences
    public List<String> getPreferences() {
        return preferences;
    }


}
