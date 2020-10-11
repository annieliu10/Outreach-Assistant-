package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CompanySizeRangeTest {
    private CompanySizeRange range;

    @BeforeEach
    public void setUp() {
        range = new CompanySizeRange(10, 100);
    }

    @Test
    public void testConstructor() {
        assertEquals(91, range.getRange().size());
        int pos = 0;
        for (Integer i : range.getRange()) {
            assertEquals(i, range.getRange().get(pos));
            pos = pos + 1;
        }
    }

    @Test
    public void testContains() {
        assertTrue(range.contains(10));
        assertTrue(range.contains(100));
        assertTrue(range.contains(38));
        assertFalse(range.contains(8));
        assertFalse(range.contains(150));
    }

}
