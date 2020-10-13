package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetSizeMethodTest {
    private CallMethods getSize;
    private Company company;
    @BeforeEach
    public void setUp(){
        getSize = new GetSizeMethod();
        company = new Company(65, "Information Technology", "AppAnn",
                "Charlie Liu");
    }

    @Test
    public void testGetSizeMethod(){
        int size = getSize.call(company);
        assertEquals(65, size);
    }
}
