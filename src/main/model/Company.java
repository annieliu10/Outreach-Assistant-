package model;

import java.util.Date;

public class Company {
    private int companySize;
    private String industry;
    private String companyName;
    private String employerName;
    private boolean contactStatus;
    private int interestLevel;
    private boolean followUpStatus;


    public Company(Integer size, String industry, String companyName, String employerName) {
        this.companySize = size;
        this.industry = industry;
        this.companyName = companyName;
        this.contactStatus = false;
        this.employerName = employerName;
        this.interestLevel = 0;
        this.followUpStatus = false;

    }


    public void contacted(int interest) {
        this.contactStatus = true;
        interestLevel = interest;
    }

    public void followedUp(){
        this.followUpStatus = true;

    }

    public Integer getSize() {
        return companySize;
    }


    public String getIndustry() {
        return industry;
    }


    public String getCompanyName() {
        return companyName;
    }

    public String getEmployerName() {
        return employerName;
    }

    public boolean getStatus() {
        return contactStatus;
    }

    public int getInterestLevel() {
        return interestLevel;
    }


    public boolean getFollowUpStatus(){
        return followUpStatus;
    }


}
