package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CompanyTest {
    private Company company;

    @BeforeEach
    public void setUp(){
        company = new Company(65, "Information Technology", "AppAnn",
                "Charlie Liu");
    }

    @Test
    public void testConstructor(){
        assertEquals(65, company.getSize());
        assertEquals("Information Technology", company.getIndustry());
        assertEquals("AppAnn", company.getCompanyName());
        assertEquals("Charlie Liu", company.getEmployerName());
        assertFalse(company.getContactStatus());
        assertEquals(0, company.getInterestLevel());
    }

    @Test
    public void testContacted(){
        company.contacted(8);
        assertTrue(company.getContactStatus());
        assertEquals(8, company.getInterestLevel());
    }


    @Test
    public void testFollowedUp(){
        company.followedUp();
        assertTrue(company.getFollowUpStatus());
    }


}
