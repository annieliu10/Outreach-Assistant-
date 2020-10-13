package model;

import java.util.Calendar;

public class Meeting {
    private Company company;
    private Calendar date;
    private String agenda;

    //REQUIRES: month is greater than or equal to 0; 0 being January
    //EFFECTS: constructs the meeting
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


    //EFFECTS: sets the meeting agenda
    public void setMeetingAgenda(String agenda) {
        this.agenda = agenda;

    }


    public Calendar getDate() {
        return date;
    }

    public Company getCompany() {
        return company;
    }


    public String getAgenda() {
        return agenda;
    }

    //EFFECTS: changes the meeting time
    public void changeMeetingTime(int year, String month, int date) {
        int months = convertFromStringToNumMonth(month);
        this.date.set(year, months, date);
    }


}
