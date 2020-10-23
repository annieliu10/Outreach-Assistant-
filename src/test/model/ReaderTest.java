package model;

import org.junit.jupiter.api.Test;
import persistence.Reader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ReaderTest {

    @Test
    public void testReaderInvalidPath(){
        Reader reader1 = new Reader("./data/invalidfile.json");
        try {
            CompanyList list1 = reader1.read();
            fail("IOException should have been thrown");
        } catch (IOException e) {
            //expected
        }
    }

    @Test
    public void testReaderEmptyCompanyList(){
        Reader reader1 = new Reader("./data/testReaderEmptyCompanyList.json");
        try {
            CompanyList list = reader1.read();
            assertEquals(0, list.getUnContactedCompanies().size());
            assertEquals(0, list.getContactedCompanies().size());
            assertEquals(0, list.getFollowedUpCompanies().size());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }

    }

    @Test
    public void testReaderValidCompanyList(){
        Reader reader1 = new Reader("./data/testReaderAFilledCompanyList.json");
        try {
            CompanyList list = reader1.read();
            assertEquals(1, list.getUnContactedCompanies().size());
            assertEquals("AppAnn", list.getContactedCompanies().get(0).getCompanyName());
            assertEquals(1, list.getContactedCompanies().size());
            assertEquals("HealthPlus", list.getFollowedUpCompanies().get(0).getCompanyName());
            assertEquals(1, list.getFollowedUpCompanies().size());
            assertEquals("J@L", list.getUnContactedCompanies().get(0).getCompanyName());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}
