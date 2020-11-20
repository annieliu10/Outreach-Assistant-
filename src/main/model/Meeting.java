package model;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.*;

//Represents one sale meeting
public class Meeting {
    private Company company;
    private Calendar date;


    //REQUIRES: company is valid, month has to be in word form and entered in full while date>=1,
    // year is a valid year 4 digit integer
    //EFFECTS: constructs the meeting with given company, year, month and date
    public Meeting(Company company, int year, String month, int date) {
        this.date = Calendar.getInstance();
        int months = convertFromStringToNumMonth(month);
        this.date.set(year, months, date);
        this.company = company;
    }

    //EFFECTS: converts from string to month
    private int convertFromStringToNumMonth(String month) {
        List<String> months = Arrays.asList("January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December");
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            nums.add(i);
        }
        int target = 0;
        for (String next : months) {
            if (next.equals(month)) {
                target = nums.get((months.indexOf(next)));
            }
        }
        return target;
    }


    //EFFECTS: returns the meeting date
    public Calendar getDate() {
        return date;
    }

    //EFFECTS: returns the company for the meeting
    public Company getCompany() {
        return company;
    }


    //REQUIRES: month has to be in word form and entered in full while date>=1, year is a valid year 4 digit integer
    //MODIFIES: this
    //EFFECTS: changes the meeting time
    public void changeMeetingTime(int year, String month, int date) {
        int months = convertFromStringToNumMonth(month);
        this.date.set(year, months, date);
    }

    //EFFECTS: converts the meeting java object to a json object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", company.getCompanyName());
        json.put("size", company.getSize());
        json.put("industry", company.getIndustry());
        json.put("employer name", company.getEmployerName());
        json.put("interest level", company.getInterestLevel());
        json.put("contact status", company.getContactStatus());
        json.put("follow-up status", company.getFollowUpStatus());
        json.put("year", date.get(Calendar.YEAR));
        json.put("month", date.get(Calendar.MONTH));
        json.put("date", date.get(Calendar.DATE));
        return json;
    }

}
