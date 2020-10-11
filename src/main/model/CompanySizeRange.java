package model;

import java.util.ArrayList;
import java.util.List;

public class CompanySizeRange {
    private List<Integer> range;
    private Integer lowerBound;
    private Integer upperBound;

    // REQUIRES: lower <= upper
    // EFFECTS: constructs a range of integers from
    // lowerBound to upperBound including the lowerBound and upperBound
    public CompanySizeRange(Integer lowerBound, Integer upperBound) {
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

    public List<Integer> getRange() {
        return range;
    }


}
