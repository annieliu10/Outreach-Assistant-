package model;

import java.util.Calendar;

public class Meeting {
    private Company company;
    private Calendar date;
    private String agenda;

    //REQUIRES: month is greater than or equal to 0; 0 being January
    //EFFECTS: books the meeting
    public Meeting(Company company, int year, String month, int date) {
        this.date = Calendar.getInstance();
        int months = convertFromStringToNumMonth(month);
        this.date.set(year, months, date);
        this.company = company;
    }

    private int convertFromStringToNumMonth(String month) {
        if (month == "January") {
            return 0;
        } else if (month == "February") {
            return 1;
        } else if (month == "March") {
            return 2;
        } else if (month == "April") {
            return 3;
        } else if (month == "May") {
            return 4;
        } else if (month == "June") {
            return 5;
        } else if (month == "July") {
            return 6;
        } else if (month == "August") {
            return 7;
        } else if (month == "September") {
            return 8;
        } else if (month == "October") {
            return 9;
        } else if (month == "November") {
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
