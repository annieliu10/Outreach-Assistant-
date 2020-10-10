package model;



import java.util.ArrayList;
import java.util.List;

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

    }

    //MODIFIES: this
    //EFFECTS: filters companies that have been contacted recently
    public void filterContacted() { }


    //EFFECTS: filters the list of new companies to be contacted based on their sizes
    //based on range
    public void filterSize(CompanySizeRange range) {

    }


    //EFFECTS: prioritizes the list of new companies to be contacted as well as the companies that have
    // not been contacted in the record based on their sizes as well as industry preferences
    // to be displayed to the user
    public List<Company> prioritizeContactsBasedOnSize(CompanySizeRange range, CompanyIndustryPreferenceOrder order) {
        return null;
    }


    //REQUIRES: the week has ended
    //EFFECTS: construct the follow-up order for companies that have been contacted this week
    // based on employers' interest levels
    //ONLY IF they have been contacted
    // to be displayed to the user
    public List<Company> prioritizeFollowUp() {
        return null;
    }




    // REQUIRES: this week has ended and that prioritizeFollowUp has been called
    // EFFECTS: put the companies that were going to be contacted but did not end up being contacted this week into
    // the record system
    public void storeUncontactedCompaniesThisWeek() {

    }

    // REQUIRES: this week has ended and that prioritizeFollowUp has been called
    // EFFECTS: put the companies that have been contacted this week into the record system
    public void storeContactedCompaniesThisWeek() {
        //put the companies that have been contacted (including new companies as well as the ones that were
        // contacted in the uncontacted list into the contacted list
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


    public void resetNewCompaniesForNewWeek(){
        companiesThisWeek = new ArrayList<>();
    }

}
