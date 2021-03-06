package model;

import exceptions.InvalidSize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SalesMeetingsTest {
    private SalesMeetings salesMeetings;
    private Company company1;
    private Company company2;
    @BeforeEach
    public void setUp() {
        salesMeetings = new SalesMeetings();
        company1 = new Company(65, "Information Technology", "AppAnn",
                "Charlie Liu");
        company2 = new Company(70, "Marketing", "HealthPlus",
                "Ann Dawson");
    }

    @Test
    //adds a meeting when two meetings are on the same day
    public void testAddMeetingOnSameDay() {

        Meeting meeting1 = new Meeting(company1, 2020, "October", 25);
        Meeting meeting2 = new Meeting(company2, 2020, "October", 25);

        assertTrue(salesMeetings.addMeeting(meeting1));
        assertFalse(salesMeetings.addMeeting(meeting2));
        assertEquals(1, salesMeetings.getSalesMeetings().size());


    }


    @Test
    public void testAddMeetingSuccessful() {
        Meeting meeting1 = new Meeting(company1, 2020, "October", 25);
        Meeting meeting2 = new Meeting(company2, 2020, "October", 20);

        assertTrue(salesMeetings.addMeeting(meeting1));
        assertTrue(salesMeetings.addMeeting(meeting2));
        assertEquals(2, salesMeetings.getSalesMeetings().size());
        assertEquals(meeting1, salesMeetings.getSalesMeetings().get(0));
        assertEquals(meeting2, salesMeetings.getSalesMeetings().get(1));
    }

    @Test
    public void testCheckMostMeetings() {
        Company company5 = new Company(150, "Marketing", "AdX",
                "Chris Lee");
        Company company3 = new Company(200, "Engineering", "MGN",
                "Christopher Runnell");
        Company company4 = new Company(80, "Engineering", "WareX", "Bob Holton");
        Meeting meeting1 = new Meeting(company1, 2020, "October", 21);
        Meeting meeting2 = new Meeting(company2, 2020, "October", 20);
        Meeting meeting3 = new Meeting(company3, 2020, "October", 19);
        Meeting meeting4 = new Meeting(company4, 2020, "November", 3);
        Meeting meeting5 = new Meeting(company5, 2020, "November", 29);
        salesMeetings.addMeeting(meeting1);
        salesMeetings.addMeeting(meeting2);
        salesMeetings.addMeeting(meeting3);
        salesMeetings.addMeeting(meeting4);
        salesMeetings.addMeeting(meeting5);
        List<Meeting> meetings = salesMeetings.checkMostMeetings();
        assertEquals(3, meetings.size());
        assertEquals(meeting1, meetings.get(0));
        assertEquals(meeting2, meetings.get(1));
        assertEquals(meeting3, meetings.get(2));

    }

    @Test
    public void testFilterBookedInContacted(){
        CompanySizeRange range = null;
        try {
            range = new CompanySizeRange(50, 200);
        } catch (InvalidSize invalidSize) {
            invalidSize.getMessage();
        }
        CompanyList companies = new CompanyList();
        companies.addNewCompany(company1, range);
        companies.addNewCompany(company2, range);
        company1.contacted(5);
        company2.contacted(8);
        companies.updateListsBasedOnContactStatuses();
        Meeting meeting1 = new Meeting(company1, 2020, "October", 28);
        Meeting meeting2 = new Meeting(company2, 2020, "October", 20);
        salesMeetings.addMeeting(meeting1);
        salesMeetings.addMeeting(meeting2);
        List<Company> filtered = salesMeetings.filterBookedMeetingsInContacted(companies.getContactedCompanies());
        assertEquals(0, filtered.size());
    }


    @Test
    public void testEquals(){
        List<Company> companies = new ArrayList<>();
        companies.add(company2);
        companies.add(company1);
        Company company = new Company(65, "Information Technology", "AppAnn",
                "Charlie Liu");
        assertTrue(companies.get(1).equals(company));


    }
}
