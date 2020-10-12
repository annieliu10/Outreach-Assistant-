package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CompanyListTest {

    private CompanyList companies;
    private CompanySizeRange range;
    private CompanyIndustryPreferenceOrder industries;

    @BeforeEach
    public void setUp() {
        companies = new CompanyList();
        range = new CompanySizeRange(50, 200);
        industries = new CompanyIndustryPreferenceOrder();
    }

    @Test
    //add when it hasn't been contacted recently and it is not in the list of uncontacted companies from the past,
    // and meets the size range
    public void testAddCompanyWhenMeetRequirement() {

        Company company1 = new Company(65, "Information Technology", "AppAnn",
                "Charlie Liu");
        Company company2 = new Company(70, "Marketing", "HealthPlus",
                "Ann Dawson");
        companies.addCompany(company1, range);
        companies.addCompany(company2, range);
        assertEquals(2, companies.getNewCompaniesThisWeek().size());
        assertEquals(65, companies.getNewCompaniesThisWeek().get(0).getSize());
        assertEquals(70, companies.getNewCompaniesThisWeek().get(1).getSize());


    }

    @Test
    //doesn't add when it hasn't been contacted recently and it is not in the list of uncontacted companies
    // from the past but doesn't meet the size range

    public void testAddCompanyHalfMeet() {

        Company company1 = new Company(30, "Information Technology", "AppAnn",
                "Charlie Liu");
        Company company2 = new Company(10, "Marketing", "HealthPlus",
                "Ann Dawson");
        companies.addCompany(company1, range);
        companies.addCompany(company2, range);
        assertEquals(0, companies.getNewCompaniesThisWeek().size());
    }

    @Test
    //doesn't add when it has been contacted recently but is not in the list of uncontacted companies from before
    public void testAddCompanyWhenRecentlyContacted() {
        //first week
        Company company1 = new Company(65, "Information Technology", "AppAnn",
                "Charlie Liu");
        companies.addCompany(company1, range);
        company1.contacted(5);
        List<Company> contacts = companies.prioritizeContactsBasedOnSize();
        companies.getReadyForNextWeek(contacts);


        //second week
        companies.addCompany(company1, range);
        assertEquals(0, companies.getNewCompaniesThisWeek().size());

    }

    @Test
    //doesn't add when it hasn't been contacted recently but is in the list of uncontacted companies from before
    public void testAddCompanyWhenUncontacted() {
        //Setup
        //first week
        Company company1 = new Company(65, "Information Technology", "AppAnn",
                "Charlie Liu");
        companies.addCompany(company1, range);
        List<Company> contacts = companies.prioritizeContactsBasedOnSize();
        companies.getReadyForNextWeek(contacts);

        //second week starts
        //invoke the method
        companies.addCompany(company1, range);

        //check outcome
        assertEquals(0, companies.getNewCompaniesThisWeek().size());
    }

    @Test
    public void testPrioritizeContactsBasedOnSize() {
        //first week
        Company company5 = new Company(150, "Marketing", "AdX",
                "Chris Lee");
        companies.addCompany(company5, range);
        List<Company> contacts = companies.prioritizeContactsBasedOnSize();
        companies.getReadyForNextWeek(contacts);


        //second week starts
        Company company1 = new Company(65, "Information Technology", "AppAnn",
                "Charlie Liu");
        Company company3 = new Company(200, "Engineering", "MGN",
                "Christopher Runnell");
        companies.addCompany(company1, range);
        companies.addCompany(company3, range);


        //invoking the method
        List<Company> result = companies.prioritizeContactsBasedOnSize();
        assertEquals(3, result.size());
        assertEquals(company1, result.get(0));
        assertEquals(company5, result.get(1));
        assertEquals(company3, result.get(2));
    }

    @Test
    public void testPrioritizeContactsBasedOnIndustry() {
        Company company5 = new Company(150, "Engineering", "AdX",
                "Chris Lee");
        companies.addCompany(company5, range);
        List<Company> contacts = companies.prioritizeContactsBasedOnSize();
        companies.getReadyForNextWeek(contacts);


        //second week starts
        Company company1 = new Company(65, "Information Technology", "AppAnn",
                "Charlie Liu");
        Company company3 = new Company(200, "Marketing", "MGN",
                "Christopher Runnell");
        companies.addCompany(company1, range);
        companies.addCompany(company3, range);

        //invoking the method
        List<Company> result = companies.prioritizeContactsBasedOnIndustry(industries);
        assertEquals(company1, result.get(0));
        assertEquals(company3, result.get(1));
        assertEquals(company5, result.get(2));

    }


    @Test
    public void testPrioritizeFollowUpContacted() {
        //first week starts

        Company company1 = new Company(65, "Information Technology", "AppAnn",
                "Charlie Liu");
        Company company3 = new Company(200, "Engineering", "MGN",
                "Christopher Runnell");
        companies.addCompany(company1, range);
        companies.addCompany(company3, range);


        List<Company> contacts = companies.prioritizeContactsBasedOnSize();
        company1.contacted(8);
        company3.contacted(5);

        //first week has ended
        List<Company> results = companies.prioritizeFollowUp(contacts);
        assertEquals(2, results.size());
        assertEquals(company1, results.get(0));
        assertEquals(company3, results.get(1));

    }


    @Test
    public void testPrioritizeFollowUpWhenNotContacted() {
        //first week starts

        Company company1 = new Company(65, "Information Technology", "AppAnn",
                "Charlie Liu");
        Company company3 = new Company(200, "Engineering", "MGN",
                "Christopher Runnell");
        companies.addCompany(company1, range);
        companies.addCompany(company3, range);

        List<Company> contacts = companies.prioritizeContactsBasedOnSize();
        //first week has ended
        List<Company> results = companies.prioritizeFollowUp(contacts);
        assertEquals(0, results.size());

    }

    @Test
    public void testGetReadyForNextWeekAllNewCompaniesContacted() {

        Company company1 = new Company(65, "Information Technology", "AppAnn",
                "Charlie Liu");
        Company company3 = new Company(200, "Engineering", "MGN",
                "Christopher Runnell");
        companies.addCompany(company1, range);
        companies.addCompany(company3, range);

        List<Company> contacts = companies.prioritizeContactsBasedOnSize();
        company1.contacted(5);
        company3.contacted(10);
        companies.prioritizeFollowUp(contacts);
        //week has already ended
        companies.getReadyForNextWeek(contacts);


        //outcome
        assertEquals(0, companies.getNewCompaniesThisWeek().size());
        assertEquals(2, companies.getContactedCompanies().size());
        assertEquals(0, companies.getUnContactedCompanies().size());
        assertTrue(companies.getContactedCompanies().contains(company1));
        assertTrue(companies.getContactedCompanies().contains(company3));
        assertFalse(companies.getUnContactedCompanies().contains(company3));
        assertFalse(companies.getUnContactedCompanies().contains(company1));


    }

    @Test
    public void testGetReadyForNextWeekContactedTheUncontactedFromPast() {
        //first week

        Company company1 = new Company(65, "Information Technology", "AppAnn",
                "Charlie Liu");
        Company company3 = new Company(200, "Engineering", "MGN",
                "Christopher Runnell");
        companies.addCompany(company1, range);
        companies.addCompany(company3, range);

        List<Company> contacts = companies.prioritizeContactsBasedOnSize();
        companies.prioritizeFollowUp(contacts);
        companies.getReadyForNextWeek(contacts);

        //second week(no new companies added)
        List<Company> contacts2 = companies.prioritizeContactsBasedOnSize();
        company1.contacted(8);
        company3.contacted(7);
        companies.prioritizeFollowUp(contacts2);
        companies.getReadyForNextWeek(contacts2);

        //outcome
        assertEquals(0, companies.getNewCompaniesThisWeek().size());
        assertEquals(0, companies.getUnContactedCompanies().size());
        assertEquals(2, companies.getContactedCompanies().size());
        assertTrue(companies.getContactedCompanies().contains(company1));
        assertTrue(companies.getContactedCompanies().contains(company3));
        assertFalse(companies.getUnContactedCompanies().contains(company3));
        assertFalse(companies.getUnContactedCompanies().contains(company1));

    }

    @Test
    public void testGetReadyForNextWeekAllNewCompaniesUncontacted() {

        Company company1 = new Company(65, "Information Technology", "AppAnn",
                "Charlie Liu");
        Company company3 = new Company(200, "Engineering", "MGN",
                "Christopher Runnell");
        companies.addCompany(company1, range);
        companies.addCompany(company3, range);

        List<Company> contacts = companies.prioritizeContactsBasedOnSize();
        companies.prioritizeFollowUp(contacts);
        //week has already ended
        companies.getReadyForNextWeek(contacts);


        //outcome
        assertEquals(0, companies.getNewCompaniesThisWeek().size());
        assertEquals(0, companies.getContactedCompanies().size());
        assertEquals(2, companies.getUnContactedCompanies().size());
        assertFalse(companies.getContactedCompanies().contains(company1));
        assertFalse(companies.getContactedCompanies().contains(company3));
        assertTrue(companies.getUnContactedCompanies().contains(company3));
        assertTrue(companies.getUnContactedCompanies().contains(company1));

    }


}
