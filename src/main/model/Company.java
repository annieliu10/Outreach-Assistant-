package model;

import java.util.Date;

public class Company {
    private int companySize;
    private String industry;
    private String companyName;
    private String employerName;
    private boolean contactStatus;
    private int interestLevel;
    private Date dateContacted;

    public Company(int size, String industry, String companyName, String employerName) {
        this.companySize = size;
        this.industry = industry;
        this.companyName = companyName;
        this.contactStatus = false;

    }


    public void contacted() {
        this.contactStatus = true;
        dateContacted = new Date();
    }




    public void setInterestLevel(int interest) {
        interestLevel = interest;
    }

    public int getSize() {
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




}
