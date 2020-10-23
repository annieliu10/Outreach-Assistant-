package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MeetingTest {
    private Meeting meeting;
    private Company company1;
    @BeforeEach
    public void setUp(){

        company1 = new Company(65, "Information Technology", "AppAnn",
                "Charlie Liu");
        meeting = new Meeting(company1, 2020, "October", 25);
    }

    @Test
    public void testConstructor(){
        assertEquals(25 ,meeting.getDate().get(Calendar.DATE));
        assertEquals(9, meeting.getDate().get(Calendar.MONTH));
        assertEquals(2020, meeting.getDate().get(Calendar.YEAR));
        assertEquals(company1, meeting.getCompany());
    }



    @Test
    public void testChangeMeetingTime(){
        meeting.changeMeetingTime(2020, "November", 1);
        assertEquals(10, meeting.getDate().get(Calendar.MONTH));
        assertEquals(1, meeting.getDate().get(Calendar.DATE));

    }



}
