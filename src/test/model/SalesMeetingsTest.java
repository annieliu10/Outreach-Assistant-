package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SalesMeetingsTest {
    private SalesMeetings salesMeetings;

    @BeforeEach
    public void setUp() {
        salesMeetings = new SalesMeetings();
    }

    @Test
    //adds a meeting when two meetings are on the same day
    public void testAddMeetingOnSameDay() {
        Company company1 = new Company(65, "Information Technology", "AppAnn",
                "Charlie Liu");
        Company company2 = new Company(70, "Marketing", "HealthPlus",
                "Ann Dawson");
        Meeting meeting1 = new Meeting(company1, 2020, 10, 25);
        Meeting meeting2 = new Meeting(company2, 2020, 10, 25);

        assertTrue(salesMeetings.addMeeting(meeting1));
        assertFalse(salesMeetings.addMeeting(meeting2));
        assertEquals(1, salesMeetings.getSalesMeetings().size());


    }

    @Test
    public void testAddMeetingSuccessful() {
        Company company1 = new Company(65, "Information Technology", "AppAnn",
                "Charlie Liu");
        Company company2 = new Company(70, "Marketing", "HealthPlus",
                "Ann Dawson");
        Meeting meeting1 = new Meeting(company1, 2020, 10, 25);
        Meeting meeting2 = new Meeting(company2, 2020, 10, 20);

        assertTrue(salesMeetings.addMeeting(meeting1));
        assertTrue(salesMeetings.addMeeting(meeting2));
        assertEquals(2, salesMeetings.getSalesMeetings().size());
    }

    @Test
    public void testCheckMostMeetings() {
        Company company1 = new Company(65, "Information Technology", "AppAnn",
                "Charlie Liu");
        Company company2 = new Company(70, "Marketing", "HealthPlus",
                "Ann Dawson");
        Company company5 = new Company(150, "Marketing", "AdX",
                "Chris Lee");
        Company company3 = new Company(200, "Engineering", "MGN",
                "Christopher Runnell");
        Company company4 = new Company(80, "Engineering", "WareX", "Bob Holton");
        Meeting meeting1 = new Meeting(company1, 2020, 10, 21);
        Meeting meeting2 = new Meeting(company2, 2020, 10, 20);
        Meeting meeting3 = new Meeting(company3, 2020, 10, 19);
        Meeting meeting4 = new Meeting(company4, 2020, 11, 3);
        Meeting meeting5 = new Meeting(company5, 2020, 11, 29);
        salesMeetings.addMeeting(meeting1);
        salesMeetings.addMeeting(meeting2);
        salesMeetings.addMeeting(meeting3);
        salesMeetings.addMeeting(meeting4);
        salesMeetings.addMeeting(meeting5);
        List<Meeting> meetings = salesMeetings.checkMostMeetings();
        assertEquals(3, meetings.size());
        assertEquals(meeting1, meetings.get(0));
    }

}
