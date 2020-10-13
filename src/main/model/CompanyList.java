package model;

import java.util.*;

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

    private List<Company> contactedCompanies;
    private List<Company> unContactedCompanies;
    private List<Company> followedUpCompanies;

    public CompanyList() {
        followedUpCompanies = new ArrayList<>();
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
                unContactedCompanies.add(company);
            }
        }
    }

    // REQUIRES: prioritizeContactsBasedOnIndustry can't be called
    // EFFECTS: prioritizes the list of new companies to be contacted as well as the companies that have
    // not been contacted in the record first based on their size
    // for size, it's smaller the better as long as it's within that range
    public List<Company> prioritizeContactsBasedOnSize() {
        CallMethods getSize = new GetSizeMethod();
        List<Company> sizeSortedCompanies = sortCompanies(getUnContactedCompanies(), getSize);
        return sizeSortedCompanies;
    }

    // REQUIRES: prioritizeContactsBasedOnSize can't be called
    // EFFECTS: prioritizes the list of new companies to be contacted as well as the companies that have
    // not been contacted in the record first based on industries
    public List<Company> prioritizeContactsBasedOnIndustry(CompanyIndustryPreferenceOrder order) {
        List<Company> industrySortedCompanies = sortBasedOnIndustry(getUnContactedCompanies(), order);
        return industrySortedCompanies;
    }


    //method.call(company)
    private List<Company> sortCompanies(List<Company> listOfCompany, CallMethods method) {
        List<Integer> values = new ArrayList<>();
        for (Company next : listOfCompany) {
            values.add(method.call(next));
        }
        Collections.sort(values);
        List<Company> sorted = new ArrayList<>();
        for (Integer i : values) {
            for (Company next : listOfCompany) {
                if (i == method.call(next) && !(sorted.contains(next))) {
                    sorted.add(next);
                }
            }
        }

        return sorted;

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

    public void updateListsBasedOnContactStatuses() {
        List<Company> removedOnes = new ArrayList<>();
        for (Company next : unContactedCompanies) {
            if (next.getContactStatus()) {
                contactedCompanies.add(next);
                removedOnes.add(next);
            }
        }
        for (Company next : removedOnes) {
            unContactedCompanies.remove(next);
        }

    }


    //REQUIRES: the week has ended and that finishedCompanies have been prioritized to be contacted and have their
    // status updated (either contacted or uncontacted) still; the ones that were contacted have employer's
    // interest levels updated as well
    //EFFECTS: construct the follow-up order for companies that have been contacted this week
    // based on employers' interest levels
    //ONLY IF they have been contacted
    // to be displayed to the user
    public List<Company> prioritizeFollowUp() {
        List<Company> temporaryResults = new ArrayList<>();
        for (Company next : contactedCompanies) {
            if (!followedUpCompanies.contains(next)) {
                temporaryResults.add(next);
            }
        }
        CallMethods getInterest = new GetInterestMethod();
        List<Company> sortedCompanies = sortCompanies(temporaryResults, getInterest);
        reverse(sortedCompanies);
        return sortedCompanies;
    }


    public void updateListsBasedOnFollowedUpCompanies() {
        List<Company> removedOnes = new ArrayList<>();
        for (Company next : contactedCompanies) {
            if (next.getFollowUpStatus()) {
                followedUpCompanies.add(next);
                removedOnes.add(next);
            }
        }
        for (Company next : removedOnes) {
            contactedCompanies.remove(next);
        }
    }


    // EFFECTS: returns the list of companies that have not been contacted
    public List<Company> getUnContactedCompanies() {
        return unContactedCompanies;
    }

    // EFFECTS: returns the list of companies that have been contacted
    public List<Company> getContactedCompanies() {
        return contactedCompanies;
    }

    public List<Company> getFollowedUpCompanies() {
        return followedUpCompanies;
    }

}
