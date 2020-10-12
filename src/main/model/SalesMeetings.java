package model;

import java.util.*;

public class SalesMeetings {
    //FIELDS

    private List<Meeting> salesMeetings;
    private int numMeetingsMax;

    public SalesMeetings() {
        salesMeetings = new ArrayList<>();

    }

    //EFFECTS: adds a meeting to the sales meetings list only if no two meetings are on the same day;
    // returns true if meeting is successfully added, otherwise false



    public boolean addMeeting(Meeting meeting) {
        boolean flag = true;
        salesMeetings.add(meeting);
        for (int i = 0; i < salesMeetings.size() - 1; i++) {
            if (((meeting.getDate().get(Calendar.DAY_OF_MONTH))
                    == (salesMeetings.get(i).getDate().get(Calendar.DAY_OF_MONTH))) &&
                    (meeting.getDate().get(Calendar.MONTH) == salesMeetings.get(i).getDate().get(Calendar.MONTH))) {
                flag = false;
            }
        }
        if (!flag) {
            salesMeetings.remove(meeting);
        }
        return flag;
    }


    // REQUIRES: numMeetingsMax >=1
    //EFFECTS: returns the meetings in the week in which most meetings occur


    public List<Meeting> checkMostMeetings() {
        List<Integer> storeMeetingsInWeek = new ArrayList<>();
        for (Meeting next : salesMeetings) {
            storeMeetingsInWeek.add(next.getDate().get(Calendar.WEEK_OF_YEAR));
        }
        List<Integer> temporaryList = new ArrayList<>();
        HashMap<Integer, Integer> tempDict = new HashMap<Integer, Integer>();
        for (Integer i : storeMeetingsInWeek) {
            if (!tempDict.containsKey(i)) {
                tempDict.put(i, 1);
            } else {
                tempDict.put(i, tempDict.get(i) + 1);
            }
        }
        Integer greatestKey = Collections.max(tempDict.entrySet(), Map.Entry.comparingByValue()).getKey();
        List<Meeting> results = new ArrayList<>();
        for (Meeting next : salesMeetings) {
            if (next.getDate().get(Calendar.WEEK_OF_YEAR) == greatestKey) {
                results.add(next);
            }
        }
        return results;
    }


    public void setNumMeetingsMax(int numMeetingsMax) {
        this.numMeetingsMax = numMeetingsMax;
    }

    public List<Meeting> getSalesMeetings() {
        return salesMeetings;
    }


}
