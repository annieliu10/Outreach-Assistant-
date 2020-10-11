package model;

import jdk.nashorn.internal.codegen.CompilerConstants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.reverse;


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
    //filters the list of new companies to be contacted based on their sizes
    public void addCompany(Company company, CompanySizeRange range) {
        if (!(getContactedCompanies().contains(company)) && (!(getUnContactedCompanies().contains(company)))) {
            if (range.contains(company.getSize())) {
                companiesThisWeek.add(company);
            }
        }
    }

    // EFFECTS: prioritizes the list of new companies to be contacted as well as the companies that have
    // not been contacted in the record first based on their size and then base it on industry preferences
    // for size, it's smaller the better as long as it's within that range
    public List<Company> prioritizeContacts(CompanyIndustryPreferenceOrder order) {
        getNewCompaniesThisWeek().addAll(getUnContactedCompanies());
        CallMethods getSize = new GetSizeMethod();
        List<Company> sizeSortedCompanies = quicksortCompanies(getNewCompaniesThisWeek(), getSize);
        List<Company> industrySortedCompanies = sortBasedOnIndustry(sizeSortedCompanies, order);
        return industrySortedCompanies;
    }


    private List<Company> quicksortCompanies(List<Company> listOfCompany, CallMethods method) {
        if (listOfCompany.size() == 0) {
            return listOfCompany;
        } else {
            List<Company> leftArray = new ArrayList<>();
            List<Company> rightArray = new ArrayList<>();
            Company pivotCompany = listOfCompany.get(0);
            int pivot = method.call(pivotCompany);
            listOfCompany.remove(pivotCompany);
            for (Company next : listOfCompany) {
                if (method.call(next) < pivot) {
                    leftArray.add(next);
                } else {
                    rightArray.add(next);
                }
            }
            leftArray.add(pivotCompany);
            leftArray.addAll(rightArray);
            return quicksortCompanies(leftArray, method);
        }

    }


    // create four different lists
    //
    private List<Company> sortBasedOnIndustry(List<Company> companies, CompanyIndustryPreferenceOrder order) {
        List<Company> list1 = new ArrayList<>();
        List<Company> list2 = new ArrayList<>();
        List<Company> list3 = new ArrayList<>();
        List<Company> list4 = new ArrayList<>();
        for (Company next : companies) {
            if (next.getIndustry() == order.getPreferences().get(0)) {
                list1.add(next);
            } else if (next.getIndustry() == order.getPreferences().get(1)) {
                list2.add(next);
            } else if (next.getIndustry() == order.getPreferences().get(2)) {
                list3.add(next);
            } else if (next.getIndustry() == order.getPreferences().get(3)) {
                list4.add(next);
            }
        }
        list1.addAll(list2);
        list1.addAll(list3);
        list1.addAll(list4);
        return list1;
    }

    //REQUIRES: the week has ended and that finishedCompanies have been prioritized to be contacted and have their
    // status updated (either contacted or uncontacted) still; the ones that were contacted have employer's
    // interest levels updated as well
    //EFFECTS: construct the follow-up order for companies that have been contacted this week
    // based on employers' interest levels
    //ONLY IF they have been contacted
    // to be displayed to the user
    public List<Company> prioritizeFollowUp(List<Company> finishedCompanies) {
        List<Company> temporaryResults = new ArrayList<>();
        for (Company next : finishedCompanies) {
            if (next.getStatus()) {
                temporaryResults.add(next);
            }
        }
        CallMethods getInterest = new GetInterestMethod();
        List<Company> sortedCompanies = quicksortCompanies(temporaryResults, getInterest);
        reverse(sortedCompanies);
        return sortedCompanies;
    }


    //REQUIRES: this week has ended and that companies have to be returned by prioritizeContacts
    //MODIFIES: this

    public void getReadyForNextWeek(List<Company> companies) {
        storeUncontactedCompaniesThisWeek(companies);
        storeContactedCompaniesThisWeek(companies);
        resetNewCompaniesForNewWeek();
    }


    // MODIFIES: this

    // EFFECTS: put the companies that were going to be contacted but did not end up being contacted this week into
    // the record system; store ONLY if they are not already in the uncontacted system
    // (uncontacted ones from before could be uncontacted again)
    private void storeUncontactedCompaniesThisWeek(List<Company> companies) {
        for (Company next : companies) {
            if (!next.getStatus() && !getUnContactedCompanies().contains(next)) {
                unContactedCompanies.add(next);
            }
        }
    }

    // MODIFIES: this
    // REQUIRES: this week has ended and that prioritizeFollowUp has been called
    // EFFECTS: put the companies that have been contacted this week into the record system
    private void storeContactedCompaniesThisWeek(List<Company> companies) {
        //put the companies that have been contacted (including new companies as well as the ones that were
        // contacted in the uncontacted list into the contacted list
        for (Company next : companies) {
            if (next.getStatus()) {
                if (getUnContactedCompanies().contains(next)) {
                    unContactedCompanies.remove(next);
                }
                contactedCompanies.add(next);
            }
        }
    }

    //REQUIRES: this week has ended
    //MODIFIES: this
    private void resetNewCompaniesForNewWeek() {
        companiesThisWeek = new ArrayList<>();
    }

    // EFFECTS: returns the list of companies that have not been contacted
    public List<Company> getUnContactedCompanies() {
        return unContactedCompanies;
    }

    // EFFECTS: returns the list of companies that have been contacted
    public List<Company> getContactedCompanies() {
        return contactedCompanies;
    }

    public List<Company> getNewCompaniesThisWeek() {
        return null;
    }


}
