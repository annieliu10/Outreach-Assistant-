package model;

import java.util.ArrayList;
import java.util.List;


//The CompanyList class has 3 different lists, one list contains the companies that are to be contacted in
// the current week; second list being the companies that have been contacted in the past; third list being
// the companies that were going to be contacted but didn't end up being contacted from the past
// At the end of the current week, the contacted companies will get stored in the second list. The ones that have not
// been contacted will get stored in the third list. If they are stored, that means the week has already ended and
// therefore the first list would reset itself to get ready for next week. The companies that have not been contacted
// from the past weeks will get contacted again in the following week along with the new companies.
// This process repeats.
public class CompanyList {
    private List<Company> companiesThisWeek;
    private List<Company> contactedCompanies;
    private List<Company> unContactedCompanies;

    public CompanyList() {
        companiesThisWeek = new ArrayList<>();
        contactedCompanies = new ArrayList<>();
        unContactedCompanies = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds the company to the new companies to be contacted this week if it hasn't been contacted recently
    // and that it is not already one of the companies that is in the record from the past
    // but hasn't been contacted yet
    public void addCompany(Company company) {
        filterContacted();
    }

    //MODIFIES: this
    //EFFECTS: filters companies that have been contacted recently
    private void filterContacted() {
    }


    //MODIFIES: this
    //EFFECTS: filters the list of new companies to be contacted based on their sizes
    //based on range
    public void filterSize(CompanySizeRange range) {

    }


    // REQUIRES: the list of companies have been filtered by size
    // EFFECTS: prioritizes the list of new companies to be contacted as well as the companies that have
    // not been contacted in the record first based on their size and then base it on  as well as industry preferences
    // for size, it's smaller the better as long as it's within that range
    public List<Company> prioritizeContacts(CompanyIndustryPreferenceOrder order) {
        return null;
    }


    //REQUIRES: the week has ended and that prioritizeContacts has to be called before this method
    //EFFECTS: construct the follow-up order for companies that have been contacted this week
    // based on employers' interest levels
    //ONLY IF they have been contacted
    // to be displayed to the user
    public List<Company> prioritizeFollowUp() {
        return null;
    }


    public void getReadyForNextWeek() {
        storeUncontactedCompaniesThisWeek();
        storeContactedCompaniesThisWeek();
        resetNewCompaniesForNewWeek();
    }


    // MODIFIES: this
    // REQUIRES: this week has ended and that prioritizeFollowUp has been called
    // EFFECTS: put the companies that were going to be contacted but did not end up being contacted this week into
    // the record system; store ONLY if they are not already in the uncontacted system
    // (uncontacted ones from before could be uncontacted again)
    private void storeUncontactedCompaniesThisWeek() {

    }

    // MODIFIES: this
    // REQUIRES: this week has ended and that prioritizeFollowUp has been called
    // EFFECTS: put the companies that have been contacted this week into the record system
    private void storeContactedCompaniesThisWeek() {
        //put the companies that have been contacted (including new companies as well as the ones that were
        // contacted in the uncontacted list into the contacted list
    }

    //REQUIRES: this week has ended
    //MODIFIES: this
    private void resetNewCompaniesForNewWeek() {
        companiesThisWeek = new ArrayList<>();
    }

    // EFFECTS: returns the list of companies that have not been contacted
    public List<Company> getUnContactedCompanies() {
        return null;
    }

    // EFFECTS: returns the list of companies that have been contacted
    public List<Company> getContactedCompanies() {
        return null;
    }

    public List<Company> getNewCompaniesThisWeek() {
        return null;
    }


}
