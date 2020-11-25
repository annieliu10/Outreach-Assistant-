package model;

import exceptions.InvalidSize;

import java.util.ArrayList;
import java.util.List;

//Represents a list of integers within the range of [lower, upper] and includes all integers from lower to upper
//Referenced the range in the ColourPalette
public class CompanySizeRange {
    private List<Integer> range;
    private Integer lowerBound;
    private Integer upperBound;


    // EFFECTS:
    // if lowerBound <1, throws InvalidSize exception
    // if lowerBound > upperBound, throws InvalidSize exception
    // otherwise, constructs a range of integers from lowerBound to upperBound including the lowerBound and upperBound
    public CompanySizeRange(Integer lowerBound, Integer upperBound) throws InvalidSize {
        if (lowerBound < 1 | lowerBound > upperBound) {
            throw new InvalidSize("Be careful about the bounds");
        }
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        range = new ArrayList<>();
        int i = lowerBound;
        while (i <= upperBound) {
            range.add(i);
            i++;
        }
    }

    //EFFECTS: returns true if val is contained in this; false otherwise
    public boolean contains(Integer val) {
        return range.contains(val);
    }

    //EFFECTS: returns the list of integers within the range
    public List<Integer> getRange() {
        return range;
    }

    //EFFECTS: returns the lower bound integer
    public int getLowerBound() {
        return lowerBound;
    }

    //EFFECTS: returns the upper bound integer
    public int getUpperBound() {
        return upperBound;

    }
}
