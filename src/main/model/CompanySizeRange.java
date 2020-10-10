package model;

import java.util.List;

public class CompanySizeRange {
    private List<Integer> range;

    // REQUIRES: lower <= upper
    // EFFECTS: constructs a range of integers from
    // lowerBound to upperBound including the lowerBound and upperBound
    public CompanySizeRange(int lowerBound, int upperBound) {
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
}
