package model;


//This class is designed for an accumulator that is in charge of displaying certain components in the user
//interface
public class CountAccumulator {
    public int count;
    public int secondLevelCount;


    //Constructs a CountAccumulator with default values for the count accumulators
    public CountAccumulator() {
        count = 0;
        secondLevelCount = 0;
    }

    // MODIFIES: this
    // EFFECTS: increments the count accumulator
    public void incrementCount() {
        count++;
    }

    // MODIFIES: this
    // EFFECTS: increments the second level count accumulator
    public void incrementSecondLevelCount() {
        secondLevelCount++;
    }


    public int getCount() {
        return count;
    }

    public int getSecondLevelCount() {
        return secondLevelCount;
    }


}
