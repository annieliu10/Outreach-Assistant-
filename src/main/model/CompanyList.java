package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

import static java.util.Collections.reverse;


//Represents a full CompanyList with 3 different sub-lists, first list contains the companies that have been contacted;
// second list being the companies that have not been contacted; third list being the companies that have been
// contacted and followed up.

public class CompanyList {

    private List<Company> contactedCompanies;
    private List<Company> unContactedCompanies;
    private List<Company> followedUpCompanies;

    //constructs a CompanyList where all 3 lists are empty
    public CompanyList() {
        followedUpCompanies = new ArrayList<>();
        contactedCompanies = new ArrayList<>();
        unContactedCompanies = new ArrayList<>();
    }

    //REQUIRES: range is valid
    //MODIFIES: this
    //EFFECTS: adds the company to the new companies to be contacted this week if it hasn't been contacted
    //and it is not already one of the companies that hasn't been contacted and on top it meets the range requirement
    public void addCompany(Company company, CompanySizeRange range) {
        if (!(getContactedCompanies().contains(company)) && (!(getUnContactedCompanies().contains(company)))) {
            if (range.contains(company.getSize())) {
                unContactedCompanies.add(company);
            }
        }
    }

    // EFFECTS: prioritizes the list of companies that haven't been contacted based on size and the given range
    // for size, it's smaller the better as long as it's within that range
    public List<Company> prioritizeContactsBasedOnSize(CompanySizeRange range) {
        CallMethods getSize = new GetSizeMethod();
        List<Company> tempUncontacted = new ArrayList<>();
        for (Company company : unContactedCompanies) {
            if (range.contains(company.getSize())) {
                tempUncontacted.add(company);
            }
        }

        List<Company> sizeSortedCompanies = sortCompanies(tempUncontacted, getSize);
        return sizeSortedCompanies;
    }


    // EFFECTS: prioritizes the list of companies that haven't been contacted based on industry preferences
    public List<Company> prioritizeContactsBasedOnIndustry(CompanyIndustryPreferenceOrder order) {
        List<Company> industrySortedCompanies = sortBasedOnIndustry(getUnContactedCompanies(), order);
        return industrySortedCompanies;
    }


    //EFFECTS: sort companies based on size or interest given the method and listofCompany
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

    // EFFECTS: prioritizes the companies to contact based on given industry preference order as well as the list of
    // companies
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

    //MODIFIES: this
    //EFFECTS: put the contacted companies from the list of companies that have not been contacted into the list of
    //companies that have been contacted
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


    //EFFECTS: construct the follow-up order for companies that have been contacted but haven't been followed up
    // based on employers' interest levels and returns the follow-up list
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


    //MODIFIES: this
    //EFFECTS: put the contacted companies from the list of companies that have been contacted into the list of
    //companies that have been followed up
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

    // EFFECTS: returns the list of companies that have been followed up
    public List<Company> getFollowedUpCompanies() {
        return followedUpCompanies;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Companies which have not been contacted", companiesToJson(unContactedCompanies));
        json.put("Companies which have ben contacted", companiesToJson(contactedCompanies));
        json.put("Companies which have been followed-up", companiesToJson(followedUpCompanies));
        return json;
    }


    private JSONArray companiesToJson(List<Company> companiess) {
        JSONArray jsonArray = new JSONArray();
        for (Company next : companiess) {
            jsonArray.put(next.toJson());
        }
        return jsonArray;
    }


}
