package model;

import exceptions.InvalidSize;
import org.junit.jupiter.api.Test;
import persistence.CompanyListReader;
import persistence.CompanyListWriter;


import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


//tests are modeled based on the sample given
//URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class CompanyListWriterTest {


    @Test
    public void testWriterInvalidPath() {
        CompanyList list1 = new CompanyList();
        CompanyListWriter writer1 = new CompanyListWriter("./data/invalid\0file.json");
        try {
            writer1.open();
            fail("File not found exception should be thrown");
        } catch (FileNotFoundException e) {
            //expected
        }
    }

    @Test
    public void testWriterEmptyCompanyList() {
        CompanyList list1 = new CompanyList();
        CompanyListWriter writer1 = new CompanyListWriter("./data/testReaderEmptyCompanyList.json");
        try {
            writer1.open();
            writer1.write(list1);
            writer1.close();

            CompanyListReader reader1 = new CompanyListReader("./data/testReaderEmptyCompanyList.json");
            list1 = reader1.read();
            assertEquals(0, list1.getFollowedUpCompanies().size());
            assertEquals(0, list1.getContactedCompanies().size());
            assertEquals(0, list1.getUnContactedCompanies().size());

        } catch (IOException e) {
            fail("This exception should not have been caught");
        }

    }


    @Test
    public void testWriterRegularCompanyList() {
        Company company1 = new Company(65, "Information Technology", "AppAnn",
                "Charlie Liu");
        Company company2 = new Company(70, "Marketing", "HealthPlus",
                "Ann Dawson");
        Company company3 = new Company(10, "Marketing", "J@L", "Chris Dune");
        CompanySizeRange range = null;
        try {
            range = new CompanySizeRange(10, 200);
        } catch (InvalidSize invalidSize) {
            invalidSize.getMessage();
        }
        CompanyIndustryPreferenceOrder industries = new CompanyIndustryPreferenceOrder();
        CompanyList list1 = new CompanyList();
        list1.addNewCompany(company1, range);
        list1.addNewCompany(company2, range);
        list1.addNewCompany(company3, range);
        company1.contacted(8);
        company2.contacted(5);
        company2.followedUp();
        list1.updateListsBasedOnContactStatuses();
        list1.updateListsBasedOnFollowedUpCompanies();
        CompanyListWriter writer1 = new CompanyListWriter("./data/testReaderAFilledCompanyList.json");
        try {
            writer1.open();
            writer1.write(list1);
            writer1.close();

            CompanyListReader reader1 = new CompanyListReader("./data/testReaderAFilledCompanyList.json");
            list1 = reader1.read();

            assertEquals(1, list1.getUnContactedCompanies().size());
            assertEquals("AppAnn", list1.getContactedCompanies().get(0).getCompanyName());
            assertEquals(1, list1.getContactedCompanies().size());
            assertEquals("HealthPlus", list1.getFollowedUpCompanies().get(0).getCompanyName());
            assertEquals(1, list1.getFollowedUpCompanies().size());
            assertEquals("J@L", list1.getUnContactedCompanies().get(0).getCompanyName());
        } catch (IOException e) {
            fail("This exception should not have been thrown");
        }
    }

}
