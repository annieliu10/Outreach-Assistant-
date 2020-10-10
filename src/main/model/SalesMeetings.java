package model;

import java.util.List;

public class SalesMeetings {
    //FIELDS

    private List<Meeting> salesMeetings;
    private int numMeetingsMax;


    public SalesMeetings(){

    }

    // REQUIRES: numMeetingsMax >=1
    //EFFECTS: ensures that no two meetings are booked on the same day and that no more than
    // a certain number of meetings are booked in the same week (changed depending on your schedule);
    public void checkBookingConflict() {

    }



    public void setNumMeetingsMax(int numMeetingsMax) {
        this.numMeetingsMax = numMeetingsMax;
    }


}
