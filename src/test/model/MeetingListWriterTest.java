package model;

import org.junit.jupiter.api.Test;
import persistence.MeetingListReader;
import persistence.MeetingsListWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MeetingListWriterTest {

    private MeetingsListWriter writer;

    @Test
    public void testMeetingListWriteInvalidPath(){
        writer = new MeetingsListWriter("./data/invalid\0file.json");
        try {
            writer.open();
            fail("FileNotFoundException should have been thrown");
        } catch (FileNotFoundException e) {
            //expected;
        }

    }

    @Test
    public void testMeetingListWriteEmptyList(){
        SalesMeetings meetings = new SalesMeetings();
        writer = new MeetingsListWriter("./data/testReaderEmptyMeetingList.json");
        try {
            writer.open();
            writer.write(meetings);
            writer.close();

            MeetingListReader reader = new MeetingListReader("./data/testReaderEmptyMeetingList.json");
            meetings = reader.read();
            assertEquals(0, meetings.getSalesMeetings().size());
        } catch (IOException e) {
            fail("FileNotFoundException should not have been thrown");
        }
    }

    @Test
    public void testMeetingListWriteFilledMeeting(){
        SalesMeetings meetings = new SalesMeetings();
        Company company1 = new Company(65, "Information Technology", "AppAnn",
                "Charlie Liu");
        company1.contacted(8);
        Company company2 = new Company(70, "Marketing", "HealthPlus",
                "Ann Dawson");
        company2.contacted(10);
        Meeting meeting1 = new Meeting(company1, 2020, "November", 10);
        Meeting meeting2 = new Meeting(company2, 2020, "November", 20);
        meetings.addMeeting(meeting1);
        meetings.addMeeting(meeting2);
        writer = new MeetingsListWriter("./data/testReaderAFilledMeetingList.json");
        try {
            writer.open();
            writer.write(meetings);
            writer.close();

            MeetingListReader reader = new MeetingListReader("./data/testReaderAFilledMeetingList.json");
            meetings = reader.read();
            assertEquals(2, meetings.getSalesMeetings().size());
            assertEquals("AppAnn", meetings.getSalesMeetings().get(0).getCompany().getCompanyName());
            assertEquals("HealthPlus", meetings.getSalesMeetings().get(1).getCompany().getCompanyName());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }

    }

}
