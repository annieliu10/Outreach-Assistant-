package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;


//Represents a list of sales meetings
public class SalesMeetings {
    //FIELDS

    private List<Meeting> salesMeetings;

    //EFFECTS: constructs an empty list of sales meetings
    public SalesMeetings() {
        salesMeetings = new ArrayList<>();
    }

    //REQUIRES: meeting is valid
    //MODIFIES: this
    //EFFECTS: adds a meeting to the sales meetings list only if no two meetings are on the same day;
    //returns true if meeting is successfully added, otherwise false
    public boolean addMeeting(Meeting meeting) {
        boolean flag = true;
        salesMeetings.add(meeting);
        for (int i = 0; i < salesMeetings.size() - 1; i++) {
            if ((((meeting.getDate().get(Calendar.DATE))
                    == (salesMeetings.get(i).getDate().get(Calendar.DATE)))
                    && (meeting.getDate().get(Calendar.MONTH) == salesMeetings.get(i).getDate().get(Calendar.MONTH)))
            ) {
                flag = false;
            }
        }
        if (!flag) {
            salesMeetings.remove(meeting);
        }
        return flag;
    }


    //EFFECTS: filters the companies that have been contacted but haven't been booked yet
    public List<Company> filterBookedMeetingsInContacted(List<Company> contacted) {
        List<Company> store = new ArrayList<>();
        boolean flag = true;
        for (Company next : contacted) {
            for (Meeting meeting : salesMeetings) {
                if (next.equals(meeting.getCompany())) {
                    flag = false;
                }
            }
            if (flag) {
                store.add(next);
            }
            flag = true;
        }
        return store;
    }


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

        //looked up how to get the max value in a hash map
        Integer greatestKey = Collections.max(tempDict.entrySet(), Map.Entry.comparingByValue()).getKey();

        List<Meeting> results = new ArrayList<>();
        for (Meeting next : salesMeetings) {
            if (next.getDate().get(Calendar.WEEK_OF_YEAR) == greatestKey) {
                results.add(next);
            }
        }
        return results;
    }


    //EFFECTS: returns the list of sales meetings
    public List<Meeting> getSalesMeetings() {
        return salesMeetings;
    }


    //EFFECTS: converts the list of meetings from java object to json object and returns it
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("list of meetings", meetingstojson());
        return json;
    }

    //EFFECTS: converts the sales meetings to a JSON array and returns it
    public JSONArray meetingstojson() {
        JSONArray json = new JSONArray();
        for (Meeting next : salesMeetings) {
            json.put(next.toJson());
        }
        return json;
    }

    //MODIFIES: this
    //EFFECTS: retrieve the meeting back into the list of sales meetings
    public void reAddMeetingsBack(Meeting meeting) {
        salesMeetings.add(meeting);
    }
}
