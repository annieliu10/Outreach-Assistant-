package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetInterestMethodTest {
    private CallMethods getInterest;
    private Company company;
    @BeforeEach
    public void setUp() {
        getInterest = new GetInterestMethod();
        company = new Company(65, "Information Technology", "AppAnn",
                "Charlie Liu");
    }

    @Test
    public void testGetInterestMethod(){
        company.contacted(8);
        int interest = getInterest.call(company);
        assertEquals(8, interest);
    }




}
