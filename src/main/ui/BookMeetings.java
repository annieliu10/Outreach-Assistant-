package ui;

import model.Company;
import model.Meeting;
import model.SalesMeetings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


// A panel that books a meeting for the companies that have been contacted
public class BookMeetings extends JFrame implements ActionListener {


    private List<Company> companies;
    private SalesMeetings meetings;
    private JComboBox cb;

    private JLabel selectCompany;

    private JLabel dob;
    private JComboBox date;
    private JComboBox month;
    private JComboBox year;

    private JTextArea tout;
    private JButton sub;
    private JButton reset;

    private JLabel res;
    private String[] dates;

    {
        dates = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
                "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
    }

    private String[] months;

    {
        months = new String[]{"January", "February", "March", "April",
                "May", "June", "July", "August", "September",
                "October", "November", "December"};
    }

    private String[] years = {"2020", "2021", "2022"};


    //REQUIRES: companies have to be in a contacted state
    //EFFECTS: performs the booking if allowed and informs the user of booking failure if meetings are on the
    // same day; displays the contacted companies that are ready to be booked, as well as booking times and the booking
    // success message to the user
    BookMeetings(List<Company> companies, SalesMeetings meetings) {
        this.companies = companies;
        this.meetings = meetings;

        String[] companyLabels;

        companyLabels = new String[]{""};
        cb = new JComboBox(companyLabels);
        for (Company next : companies) {
            cb.addItem(next.getCompanyName());
        }

        add(cb);
        cb.setBounds(200, 50, 200, 20);

        selectCompany();
        selectMeetingTime();

        submitAndReset();

        setResponse();

        displayMeetings();


        // settings for the frame
        setLayout(null);
        setBounds(300, 90, 800, 450);
        setVisible(true);
        setTitle("Book Meetings");
        setResizable(false);
    }


    //MODIFIES: this
    //EFFECTS: creates the empty space to display the booked meetings in the empty space on the right
    private void displayMeetings() {
        tout = new JTextArea();
        tout.setFont(new Font("Arial", Font.PLAIN, 12));
        tout.setSize(300, 200);
        tout.setLocation(400, 50);
        tout.setLineWrap(true);
        tout.setEditable(false);
        add(tout);
    }

    //MODIFIES: this
    //EFFECTS: creates a label to set the booking response message to the user
    private void setResponse() {
        res = new JLabel("");
        res.setFont(new Font("Arial", Font.PLAIN, 15));
        res.setSize(500, 25);
        res.setLocation(100, 300);
        add(res);
    }


    //MODIFIES: this
    //EFFECTS: creates submit and reset buttons to the user
    private void submitAndReset() {
        sub = new JButton("Submit");
        sub.setFont(new Font("Arial", Font.PLAIN, 12));
        sub.setSize(100, 20);
        sub.setLocation(20, 250);
        sub.addActionListener(this);
        add(sub);

        reset = new JButton("Reset");
        reset.setFont(new Font("Arial", Font.PLAIN, 12));
        reset.setSize(100, 20);
        reset.setLocation(130, 250);
        reset.addActionListener(this);
        add(reset);
    }

    //MODIFIES: this
    //EFFECTS: creates the label "select the company"
    private void selectCompany() {
        selectCompany = new JLabel("Select the company");
        selectCompany.setFont(new Font("Arial", Font.PLAIN, 12));
        selectCompany.setSize(250, 20);
        selectCompany.setLocation(20, 50);
        add(selectCompany);
    }

    //MODIFIES: this
    //EFFECTS: creates the label "meeting time" as well as the drop-down box for selecting the calendar booking times
    private void selectMeetingTime() {
        dob = new JLabel("Meeting Time");
        dob.setFont(new Font("Arial", Font.PLAIN, 12));
        dob.setSize(100, 20);
        dob.setLocation(20, 100);
        add(dob);

        date = new JComboBox(dates);
        date.setFont(new Font("Arial", Font.PLAIN, 12));
        date.setSize(100, 20);
        date.setLocation(200, 100);
        add(date);

        month = new JComboBox(months);
        month.setFont(new Font("Arial", Font.PLAIN, 12));
        month.setSize(100, 20);
        month.setLocation(200, 150);
        add(month);

        year = new JComboBox(years);
        year.setFont(new Font("Arial", Font.PLAIN, 12));
        year.setSize(100, 20);
        year.setLocation(200, 200);
        add(year);
    }


    //MODIFIES: this
    //EFFECTS: displays the success message/response once the user selects a booking time for the company for which
    // they want to book and clicks submit; books the meetings in the backend if it's successful; resets user input
    // if the user clicks on the reset button

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sub) {
            String scheduledDate = "Meeting Time : "
                    + (String) date.getSelectedItem()
                    + "/" + (String) month.getSelectedItem()
                    + "/" + (String) year.getSelectedItem()
                    + "\n";

            String companyName = String.valueOf(cb.getItemAt(cb.getSelectedIndex()));
            tout.setText(companyName + "\n" + scheduledDate);
            Integer dates = Integer.parseInt((String) date.getSelectedItem());
            String months = (String) month.getSelectedItem();
            Integer years = Integer.parseInt((String) year.getSelectedItem());

            bookMeeting(companyName, dates, months, years);
            int a = JOptionPane.showConfirmDialog(this, "Would you like to continue?");
            if (a == JOptionPane.YES_OPTION) {

                new BookMeetings(meetings.filterBookedMeetingsInContacted(companies), meetings);

            }

        } else {
            resetEverything();
        }


    }

    //MODIFIES: this
    //EFFECTS: resets user input
    private void resetEverything() {
        String def = "";
        cb.setSelectedIndex(0);
        res.setText(def);
        tout.setText(def);
        date.setSelectedIndex(0);
        month.setSelectedIndex(0);
        year.setSelectedIndex(0);
    }

    //MODIFIES: this
    //EFFECTS: books a meeting and displays the booking success/failure message to the user
    private void bookMeeting(String companyName, Integer dates, String months, Integer years) {
        for (Company next : companies) {
            if (next.getCompanyName().equals(companyName)) {
                Meeting meeting = null;

                meeting = new Meeting(next, years, months, dates);

                boolean result = meetings.addMeeting(meeting);
                if (result) {
                    res.setText("Booking was successful!");
                } else {
                    res.setText("Booking was unsuccessful because two meetings are on the same day!");
                }
            }
        }
    }
}
