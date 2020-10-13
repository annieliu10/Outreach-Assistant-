package model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

//Represents one sale meeting
public class Meeting {
    private Company company;
    private Calendar date;
    private String agenda;

    //REQUIRES: company is valid, month has to be in word form and entered in full while date>=1,
    // year is a valid year 4 digit integer
    //EFFECTS: constructs the meeting with given company, year, month and date
    public Meeting(Company company, int year, String month, int date) {
        this.date = Calendar.getInstance();
        int months = convertFromStringToNumMonth(month);
        this.date.set(year, months, date);
        this.company = company;
    }

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


    //MODIFIES: this
    //EFFECTS: sets the meeting agenda
    public void setMeetingAgenda(String agenda) {
        this.agenda = agenda;

    }

    //EFFECTS: returns the meeting date
    public Calendar getDate() {
        return date;
    }

    //EFFECTS: returns the company for the meeting
    public Company getCompany() {
        return company;
    }

    //EFFECTS: returns the agenda for the meeting
    public String getAgenda() {
        return agenda;
    }

    //REQUIRES: month has to be in word form and entered in full while date>=1, year is a valid year 4 digit integer
    //MODIFIES: this
    //EFFECTS: changes the meeting time
    public void changeMeetingTime(int year, String month, int date) {
        int months = convertFromStringToNumMonth(month);
        this.date.set(year, months, date);
    }


}
