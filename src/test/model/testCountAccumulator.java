package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testCountAccumulator {

    private CountAccumulator counts;

    @BeforeEach
    public void setUp(){
        counts =new CountAccumulator();
    }


    @Test
    public void testConstructor(){
        assertEquals(0, counts.getCount());
        assertEquals(0, counts.getSecondLevelCount());
    }
    @Test
    public void testIncrementCount(){
        counts.incrementCount();
        assertEquals(1, counts.getCount());

    }
    @Test
    public void testIncrementSecondLevelAccount(){
        counts.incrementSecondLevelCount();
        assertEquals(1, counts.getSecondLevelCount());
    }

}
