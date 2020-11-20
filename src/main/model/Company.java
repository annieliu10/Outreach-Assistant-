package model;

import org.json.JSONObject;

import java.util.Date;
import java.util.Objects;


//Represents a company
public class Company {
    private int companySize;
    private String industry;
    private String companyName;
    private String employerName;
    private boolean contactStatus;
    private int interestLevel;
    private boolean followUpStatus;


    //EFFECTS: constructs a new company with size, industry, companyName and employerName as information and assumes
    //it hasn't been contacted
    public Company(Integer size, String industry, String companyName, String employerName) {
        this.companySize = size;
        this.industry = industry;
        this.companyName = companyName;
        this.contactStatus = false;
        this.employerName = employerName;
        this.interestLevel = 0;
        this.followUpStatus = false;

    }

    //MODIFIES: this
    //EFFECTS: updates the contact status for this when it has been contacted
    public void contacted(int interest) {
        this.contactStatus = true;
        interestLevel = interest;
    }

    //MODIFIES: this
    //EFFECTS: updates the follow-up status for this when it has been followed up
    public void followedUp() {
        this.followUpStatus = true;

    }

    //EFFECTS: returns the size of this
    public Integer getSize() {
        return companySize;
    }

    //EFFECTS: returns the industry of this
    public String getIndustry() {
        return industry;
    }

    //EFFECTS: returns the company name of this
    public String getCompanyName() {
        return companyName;
    }

    //EFFECTS: returns the recruiter name of this
    public String getEmployerName() {
        return employerName;
    }

    //EFFECTS: returns the contact status of this
    public boolean getContactStatus() {
        return contactStatus;
    }

    //EFFECTS: returns the interest level of this
    public int getInterestLevel() {
        return interestLevel;
    }

    //EFFECTS: returns the follow up status of this
    public boolean getFollowUpStatus() {
        return followUpStatus;
    }

    //EFFECTS: parses from java objects to json objects
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", companyName);
        json.put("size", companySize);
        json.put("industry", industry);
        json.put("employer name", employerName);
        json.put("interest level", interestLevel);
        json.put("contact status", contactStatus);
        json.put("follow-up status", followUpStatus);
        return json;
    }

    //EFFECTS: compares the fields (company size, contact status, interest level, follow-up status)
    //to see whether two companies are considered the "same"
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Company company = (Company) o;
        return companySize == company.companySize
                && contactStatus == company.contactStatus
                && interestLevel == company.interestLevel
                && followUpStatus == company.followUpStatus
                && Objects.equals(industry, company.industry)
                && Objects.equals(companyName, company.companyName)
                && Objects.equals(employerName, company.employerName);
    }

    //EFFECTS: used in the equals method to compare two objects
    @Override
    public int hashCode() {
        return Objects.hash(companySize, industry, companyName, employerName, contactStatus,
                interestLevel, followUpStatus);
    }



}
