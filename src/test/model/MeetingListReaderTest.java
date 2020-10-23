package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.MeetingListReader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MeetingListReaderTest {

    private MeetingListReader reader;

    @Test
    public void testReaderInvalidPath(){
        reader = new MeetingListReader("./data/WhereIsThisPath.json");
        try {
            SalesMeetings meetings = reader.read();
            fail("IOException should have been thrown since the path is not right");
        } catch (IOException e) {
            //expected
        }
    }

    @Test
    public void testReaderEmptyList(){
        reader = new MeetingListReader("./data/testReaderEmptyMeetingList.json");
        try {
            SalesMeetings meetings = reader.read();
            assertEquals(0, meetings.getSalesMeetings().size());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    public void testReadFilledList(){
        reader = new MeetingListReader("./data/testReaderAFilledMeetingList.json");
        try {
            SalesMeetings meetings = reader.read();
            assertEquals(2, meetings.getSalesMeetings().size());
            assertEquals("AppAnn", meetings.getSalesMeetings().get(0).getCompany().getCompanyName());
            assertEquals("HealthPlus", meetings.getSalesMeetings().get(1).getCompany().getCompanyName());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }

    }
}
