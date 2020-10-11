package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompanyListTest {

    private CompanyList companies;

    @BeforeEach
    public void setUp() {
        companies = new CompanyList();
    }


    @Test
    //add when it hasn't been contacted recently and it is not in the list of uncontacted companies from the past
    public void testAddCompanyWhenMeetRequirement() {
        Company company1 = new Company(65, "Information Technology", "AppAnn",
                "Charlie Liu");
        Company company2 = new Company(10, "Marketing", "HealthPlus",
                "Ann Dawson");
        companies.addCompany(company1);
        companies.addCompany(company2);
        assertEquals(2, companies.getNewCompaniesThisWeek().size());
        assertEquals(65, companies.getNewCompaniesThisWeek().get(0).getSize());
        assertEquals(10, companies.getNewCompaniesThisWeek().get(1).getSize());

    }

    @Test
    //add when it has been contacted recently but is not in the list of uncontacted companies from before
    public void testAddCompanyWhenRecentlyContacted() {
        Company company1 = new Company(65, "Information Technology", "AppAnn",
                "Charlie Liu");
        company1.updateContactStatus();
        companies.addCompany(company1);
        assertEquals(0, companies.getNewCompaniesThisWeek().size());

    }

    @Test
    //add when it hasn't been contacted recently but is in the list of uncontacted companies from before
    public void testAddCompanyWhenUncontacted() {
        //Setup
        //first week
        Company company1 = new Company(65, "Information Technology", "AppAnn",
                "Charlie Liu");
        companies.addCompany(company1);
        companies.getReadyForNextWeek();

        //second week starts
        //invoke the method
        companies.addCompany(company1);

        //check outcome
        assertEquals(0, companies.getNewCompaniesThisWeek().size());
    }

    public void testFilterSize() {
        CompanySizeRange range = new CompanySizeRange(50, 200);
        Company company1 = new Company(65, "Information Technology", "AppAnn",
                "Charlie Liu");
        Company company2 = new Company(10, "Marketing", "HealthPlus",
                "Ann Dawson");
        Company company3 = new Company(200, "Engineering", "MGN",
                "Christopher Runnell");
        Company company4 = new Company(36, "Engineering", "J&L",
                "Jessica Lam");
        companies.addCompany(company1);
        companies.addCompany(company2);
        companies.addCompany(company3);
        companies.addCompany(company4);
        companies.filterSize(range);
        assertEquals(2, companies.getNewCompaniesThisWeek().size());
        assertEquals(company1, companies.getNewCompaniesThisWeek().get(0));
        assertEquals(company3, companies.getNewCompaniesThisWeek().get(1));
    }


    public void testPrioritizeContacts() {
        CompanySizeRange range = new CompanySizeRange(50, 200);
        CompanyIndustryPreferenceOrder industries = new CompanyIndustryPreferenceOrder();

        //first week
        Company company5 = new Company(150, "Marketing", "AdX",
                "Chris Lee");
        companies.addCompany(company5);
        companies.getReadyForNextWeek();


        //second week starts
        Company company1 = new Company(65, "Information Technology", "AppAnn",
                "Charlie Liu");
        Company company3 = new Company(200, "Engineering", "MGN",
                "Christopher Runnell");
        companies.addCompany(company1);
        companies.addCompany(company3);
        companies.filterSize(range);

        //invoking the method
        List<Company> result = companies.prioritizeContacts(industries);
        assertEquals(3, result.size());
        assertEquals(company1, result.get(0));
        assertEquals(company5, result.get(1));
        assertEquals(company3, result.get(2));
    }

    @Test
    public void testPrioritizeFollowUpContacted() {
        //first week starts
        CompanySizeRange range = new CompanySizeRange(50, 200);
        CompanyIndustryPreferenceOrder industries = new CompanyIndustryPreferenceOrder();
        Company company1 = new Company(65, "Information Technology", "AppAnn",
                "Charlie Liu");
        Company company3 = new Company(200, "Engineering", "MGN",
                "Christopher Runnell");
        companies.addCompany(company1);
        companies.addCompany(company3);
        companies.filterSize(range);
        companies.prioritizeContacts(industries);
        company1.updateContactStatus();
        company3.updateContactStatus();

        //first week has ended
        List<Company> results = companies.prioritizeFollowUp();
        assertEquals(2, results.size());
        assertEquals(company1, results.get(0));
        assertEquals(company3, results.get(1));

    }


    @Test
    public void testPrioritizeFollowUpWhenNotContacted() {
        //first week starts
        CompanySizeRange range = new CompanySizeRange(50, 200);
        CompanyIndustryPreferenceOrder industries = new CompanyIndustryPreferenceOrder();
        Company company1 = new Company(65, "Information Technology", "AppAnn",
                "Charlie Liu");
        Company company3 = new Company(200, "Engineering", "MGN",
                "Christopher Runnell");
        companies.addCompany(company1);
        companies.addCompany(company3);
        companies.filterSize(range);
        companies.prioritizeContacts(industries);
        //first week has ended
        List<Company> results = companies.prioritizeFollowUp();
        assertEquals(0, results.size());

    }

    @Test
    public void testGetReadyForNextWeek(){



    }


}
