package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompanyIndustryPreferenceOrderTest {
    private CompanyIndustryPreferenceOrder preferenceOrder;

    @BeforeEach
    public void setUp(){
        preferenceOrder = new CompanyIndustryPreferenceOrder();
    }

    @Test
    public void testConstructor(){
        assertEquals("Information Technology", preferenceOrder.getPreferences().get((0)));
        assertEquals("Business Admin/ Project MGMT", preferenceOrder.getPreferences().get(1));
        assertEquals("Marketing", preferenceOrder.getPreferences().get(2));
        assertEquals("Engineering", preferenceOrder.getPreferences().get(3));
    }

    @Test
    public void testChangePreferenceOrder(){
        preferenceOrder.changePreferenceOrder("Marketing", "Information Technology", "Engineering",
                "Business Admin/ Project MGMT");
        assertEquals("Information Technology", preferenceOrder.getPreferences().get((1)));
        assertEquals("Business Admin/ Project MGMT", preferenceOrder.getPreferences().get(3));
        assertEquals("Marketing", preferenceOrder.getPreferences().get(0));
        assertEquals("Engineering", preferenceOrder.getPreferences().get(2));
    }
}
