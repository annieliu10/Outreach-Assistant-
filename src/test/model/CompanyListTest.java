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
    public void testConstructor(){
        assertEquals(0, companies.getContactedCompanies());
        assertEquals(0, companies.getContactedCompanies());
        assertEquals(0, companies.getFollowedUpCompanies());
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
        assertEquals(2, companies.getUnContactedCompanies().size());
        assertEquals(65, companies.getUnContactedCompanies().get(0).getSize());
        assertEquals(70, companies.getUnContactedCompanies().get(1).getSize());
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
        assertEquals(0, companies.getUnContactedCompanies().size());
    }

    @Test
    //doesn't add when it has been contacted recently and is not in the list of uncontacted companies
    public void testAddCompanyWhenRecentlyContacted() {

        Company company1 = new Company(65, "Information Technology", "AppAnn",
                "Charlie Liu");
        companies.addCompany(company1, range);
        assertEquals(1, companies.getUnContactedCompanies().size());
        company1.contacted(5);
        companies.updateListsBasedOnContactStatuses();
        List<Company> contacts = companies.prioritizeContactsBasedOnSize(range);



        companies.addCompany(company1, range);
        assertEquals(0, companies.getUnContactedCompanies().size());

    }

    @Test
    //doesn't add when it hasn't been contacted recently but is in the list of uncontacted companies from before
    public void testAddCompanyWhenUncontacted() {
        //Setup
        //first week
        Company company1 = new Company(65, "Information Technology", "AppAnn",
                "Charlie Liu");
        companies.addCompany(company1, range);
        assertEquals(1, companies.getUnContactedCompanies().size());
        List<Company> contacts = companies.prioritizeContactsBasedOnSize(range);


        //second week starts
        //invoke the method
        companies.addCompany(company1, range);

        //check outcome
        assertEquals(1, companies.getUnContactedCompanies().size());
    }

    @Test
    public void testPrioritizeContactsBasedOnSizeNoFilter() {
        //first week
        Company company5 = new Company(150, "Marketing", "AdX",
                "Chris Lee");
        companies.addCompany(company5, range);


        //second week starts
        Company company1 = new Company(65, "Information Technology", "AppAnn",
                "Charlie Liu");
        Company company3 = new Company(200, "Engineering", "MGN",
                "Christopher Runnell");
        companies.addCompany(company1, range);
        companies.addCompany(company3, range);


        //invoking the method
        List<Company> result = companies.prioritizeContactsBasedOnSize(range);
        assertEquals(3, result.size());
        assertEquals(company1, result.get(0));
        assertEquals(company5, result.get(1));
        assertEquals(company3, result.get(2));
    }

    @Test
    public void testPrioritizeContactsBasedOnSizeAndFilter() {
        //first week
        Company company5 = new Company(150, "Marketing", "AdX",
                "Chris Lee");

        Company company2 = new Company(800, "Information Technology", "Facebook",
                "Anson Li");

        Company company4= new Company(1000, "Engineering", "MM", "Annie Liu");

        Company company1 = new Company(65, "Information Technology", "AppAnn",
                "Charlie Liu");
        Company company3 = new Company(200, "Engineering", "MGN",
                "Christopher Runnell");
        companies.addCompany(company5, range);
        companies.addCompany(company1, range);
        companies.addCompany(company3, range);
        companies.addCompany(company2, range);
        companies.addCompany(company4, range);


        //invoking the method
        List<Company> result = companies.prioritizeContactsBasedOnSize(range);
        assertEquals(3, result.size());
        assertEquals(company1, result.get(0));
        assertEquals(company5, result.get(1));
        assertEquals(company3, result.get(2));
    }

    @Test
    public void testPrioritizeContactsBasedOnIndustry() {
        Company company5 = new Company(150, "Engineering", "AdX",
                "Chris Lee");

        Company company4 = new Company(70, "Business Admin/ Project MGMT", "YaT",
                "Chris Lee");
        Company company1 = new Company(65, "Information Technology", "AppAnn",
                "Charlie Liu");
        Company company3 = new Company(200, "Marketing", "MGN",
                "Christopher Runnell");
        companies.addCompany(company5, range);
        companies.addCompany(company1, range);
        companies.addCompany(company3, range);
        companies.addCompany(company4, range);

        //invoking the method
        List<Company> result = companies.prioritizeContactsBasedOnIndustry(industries);
        assertEquals(company1, result.get(0));
        assertEquals(company4, result.get(1));
        assertEquals(company3, result.get(2));
        assertEquals(company5, result.get(3));

    }


    @Test
    public void testPrioritizeFollowUpContactedAndNotFollowedUp() {
        //first week starts

        Company company1 = new Company(65, "Information Technology", "AppAnn",
                "Charlie Liu");
        Company company3 = new Company(200, "Engineering", "MGN",
                "Christopher Runnell");
        companies.addCompany(company1, range);
        companies.addCompany(company3, range);

        List<Company> contacts = companies.prioritizeContactsBasedOnSize(range);
        company1.contacted(8);
        company3.contacted(5);
        companies.updateListsBasedOnContactStatuses();


        List<Company> results = companies.prioritizeFollowUp();
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

        List<Company> contacts = companies.prioritizeContactsBasedOnSize(range);
        //first week has ended
        List<Company> results = companies.prioritizeFollowUp();
        assertEquals(0, results.size());

    }

    @Test
    public void testPrioritizeFollowUpWhenFollowedUp(){
        Company company1 = new Company(65, "Information Technology", "AppAnn",
                "Charlie Liu");
        Company company3 = new Company(200, "Engineering", "MGN",
                "Christopher Runnell");
        companies.addCompany(company1, range);
        companies.addCompany(company3, range);
        List<Company> contacts = companies.prioritizeContactsBasedOnSize(range);
        company1.contacted(8);
        company3.contacted(5);
        companies.updateListsBasedOnContactStatuses();
        company1.followedUp();
        company3.followedUp();
        companies.updateListsBasedOnFollowedUpCompanies();
        List<Company> results = companies.prioritizeFollowUp();
        assertEquals(0, results.size());


    }

    @Test
    public void testUpdateListsBasedOnContactedSuccess(){
        Company company1 = new Company(65, "Information Technology", "AppAnn",
                "Charlie Liu");
        Company company3 = new Company(200, "Engineering", "MGN",
                "Christopher Runnell");
        companies.addCompany(company1, range);
        companies.addCompany(company3, range);
        List<Company> contacts = companies.prioritizeContactsBasedOnSize(range);
        company1.contacted(8);
        company3.contacted(5);
        companies.updateListsBasedOnContactStatuses();
        assertEquals(2, companies.getContactedCompanies().size());
        assertEquals(company1, companies.getContactedCompanies().get(0));
        assertEquals(company3, companies.getContactedCompanies().get(1));
        assertEquals(0, companies.getUnContactedCompanies().size());
    }


    @Test
    public void testUpdateListsBasedOnContactedUnSuccessful(){
        Company company1 = new Company(65, "Information Technology", "AppAnn",
                "Charlie Liu");
        Company company3 = new Company(200, "Engineering", "MGN",
                "Christopher Runnell");
        companies.addCompany(company1, range);
        companies.addCompany(company3, range);
        List<Company> contacts = companies.prioritizeContactsBasedOnSize(range);
        companies.updateListsBasedOnContactStatuses();
        assertEquals(0, companies.getContactedCompanies().size());
        assertEquals(2, companies.getUnContactedCompanies().size());
        assertEquals(company1, companies.getUnContactedCompanies().get(0));
        assertEquals(company3, companies.getUnContactedCompanies().get(1));
    }

    @Test
    public void testUpdatedListsBasedOnFollowedUpSuccess(){
        Company company1 = new Company(65, "Information Technology", "AppAnn",
                "Charlie Liu");
        Company company3 = new Company(200, "Engineering", "MGN",
                "Christopher Runnell");
        companies.addCompany(company1, range);
        companies.addCompany(company3, range);
        List<Company> contacts = companies.prioritizeContactsBasedOnSize(range);
        company1.contacted(8);
        company3.contacted(5);
        companies.updateListsBasedOnContactStatuses();
        company1.followedUp();
        company3.followedUp();
        companies.updateListsBasedOnFollowedUpCompanies();
        assertEquals(0, companies.getContactedCompanies().size());
        assertEquals(2, companies.getFollowedUpCompanies().size());
        assertEquals(company1, companies.getFollowedUpCompanies().get(0));
        assertEquals(company3, companies.getFollowedUpCompanies().get(1));
    }
    @Test
    public void testUpdatedListsBasedOnFollowedUpUnSuccess() {
        Company company1 = new Company(65, "Information Technology", "AppAnn",
                "Charlie Liu");
        Company company3 = new Company(200, "Engineering", "MGN",
                "Christopher Runnell");
        companies.addCompany(company1, range);
        companies.addCompany(company3, range);
        List<Company> contacts = companies.prioritizeContactsBasedOnSize(range);
        company1.contacted(8);
        company3.contacted(5);
        companies.updateListsBasedOnContactStatuses();
        companies.updateListsBasedOnFollowedUpCompanies();
        assertEquals(2, companies.getContactedCompanies().size());
        assertEquals(0, companies.getFollowedUpCompanies().size());
        assertEquals(company1, companies.getContactedCompanies().get(0));
        assertEquals(company3, companies.getContactedCompanies().get(1));

    }

    }
