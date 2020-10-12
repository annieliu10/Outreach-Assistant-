package model;

import java.util.Calendar;

public class Meeting {
    private Company company;
    private Calendar date;
    private String agenda;

    //EFFECTS: books the meeting
    public Meeting(Company company, int year, int month, int date) {
        this.date = Calendar.getInstance();
        this.date.set(year, month, date);
        this.company = company;
    }



    public void setMeetingAgenda(String agenda) {
        this.agenda = agenda;

    }

    public void displayBooking() {
    }

    public Calendar getDate() {
        return date;
    }

    public Company getCompany() {
        return company;
    }


    //EFFECTS: changes the meeting time
    public void changeMeetingTime(int year, int month, int date) {
        this.date.set(year, month, date);
    }






}
