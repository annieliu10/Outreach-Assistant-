package model;

import java.util.Calendar;

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
        if (month.equals("January")) {
            return 0;
        } else if (month.equals("February")) {
            return 1;
        } else if (month.equals("March")) {
            return 2;
        } else if (month.equals("April")) {
            return 3;
        } else if (month.equals("May")) {
            return 4;
        } else if (month.equals("June")) {
            return 5;
        } else if (month.equals("July")) {
            return 6;
        } else if (month.equals("August")) {
            return 7;
        } else if (month.equals("September")) {
            return 8;
        } else if (month.equals("October")) {
            return 9;
        } else if (month.equals("November")) {
            return 10;
        }
        return 11;
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
