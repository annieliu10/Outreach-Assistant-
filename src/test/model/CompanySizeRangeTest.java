package model;

import exceptions.InvalidSize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CompanySizeRangeTest {
    private CompanySizeRange range;


    @BeforeEach
    public void setUpGood() {

        try {
            range = new CompanySizeRange(10, 100);
            assertEquals(10, range.getLowerBound());
            assertEquals(100, range.getUpperBound());
        } catch (InvalidSize invalidSize) {
            fail("The exception should not have been thrown");
        }

    }

    @Test
    public void setUpLowerBoundNegative() {
        CompanySizeRange range1 = null;
        try {
            range1 = new CompanySizeRange(-1, 100);

        } catch (InvalidSize invalidSize) {
            invalidSize.getMessage();
        }

        assertEquals(null, range1);

    }


    @Test
    public void setUpLowerBoundZero() {
        CompanySizeRange range2 = null;
        try {
            range2 = new CompanySizeRange(600, 500);
        } catch (InvalidSize invalidSize) {
            invalidSize.getMessage();
        }

        assertEquals(null, range2);
    }


    @Test
    public void setUpBothZeros() {
        CompanySizeRange range3 = null;
        try {
            range3 = new CompanySizeRange(0, -500);
        } catch (InvalidSize invalidSize) {
            invalidSize.getMessage();
        }


        assertEquals(null, range3);
    }

    @Test
    public void testConstructor() {
        assertEquals(91, range.getRange().size());
        int pos = 0;
        for (Integer i : range.getRange()) {
            assertEquals(i, range.getRange().get(pos));
            pos++;

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

    @Test
    public void testGetLowerBound() {
        assertEquals(10, range.getLowerBound());
        assertEquals(100, range.getUpperBound());
    }

}
