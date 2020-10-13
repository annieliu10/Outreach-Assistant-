package model;

import java.util.Date;


//Represents a company
public class Company {
    private int companySize;
    private String industry;
    private String companyName;
    private String employerName;
    private boolean contactStatus;
    private int interestLevel;
    private boolean followUpStatus;


    //constructs a new company with size, industry, companyName and employerName as information and assumes
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


}
