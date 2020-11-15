package ui;

import model.Company;
import model.Meeting;
import model.SalesMeetings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Book;
import java.util.List;

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
    private String dates[]
            = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18",
            "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};

    private String months[]
            = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October",
            "November", "December"};

    private String years[]
            = {"2020", "2021", "2022"};


    BookMeetings(List<Company> companies, SalesMeetings meetings) {
        this.companies = companies;
        this.meetings = meetings;

        label();

        submitButton();

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
        setTitle("Update companies");
        setResizable(false);
    }

    private void label() {
        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setSize(400, 100);
    }

    private void submitButton() {
        JButton b = new JButton("Submit");
        b.setBounds(220, 110, 75, 20);
        b.addActionListener(this);
    }

    private void displayMeetings() {
        tout = new JTextArea();
        tout.setFont(new Font("Arial", Font.PLAIN, 12));
        tout.setSize(300, 400);
        tout.setLocation(400, 50);
        tout.setLineWrap(true);
        tout.setEditable(false);
        add(tout);
    }

    private void setResponse() {
        res = new JLabel("");
        res.setFont(new Font("Arial", Font.PLAIN, 20));
        res.setSize(500, 25);
        res.setLocation(100, 300);
        add(res);
    }

    private void submitAndReset() {
        sub = new JButton("Submit");
        sub.setFont(new Font("Arial", Font.PLAIN, 12));
        sub.setSize(100, 20);
        sub.setLocation(20, 200);
        sub.addActionListener(this);
        add(sub);

        reset = new JButton("Reset");
        reset.setFont(new Font("Arial", Font.PLAIN, 12));
        reset.setSize(100, 20);
        reset.setLocation(130, 200);
        reset.addActionListener(this);
        add(reset);
    }

    private void selectCompany() {
        selectCompany = new JLabel("Select the company");
        selectCompany.setFont(new Font("Arial", Font.PLAIN, 12));
        selectCompany.setSize(250, 20);
        selectCompany.setLocation(20, 50);
        add(selectCompany);
    }

    private void selectMeetingTime() {
        dob = new JLabel("Meeting Time");
        dob.setFont(new Font("Arial", Font.PLAIN, 12));
        dob.setSize(100, 20);
        dob.setLocation(20, 100);
        add(dob);

        date = new JComboBox(dates);
        date.setFont(new Font("Arial", Font.PLAIN, 12));
        date.setSize(50, 20);
        date.setLocation(200, 100);
        add(date);

        month = new JComboBox(months);
        month.setFont(new Font("Arial", Font.PLAIN, 12));
        month.setSize(60, 20);
        month.setLocation(250, 100);
        add(month);

        year = new JComboBox(years);
        year.setFont(new Font("Arial", Font.PLAIN, 15));
        year.setSize(60, 20);
        year.setLocation(320, 100);
        add(year);
    }

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

            boolean flag = bookMeeting(companyName, dates, months, years);
            if (!flag) {
                res.setText("The company for which you want to book a meeting isn't in the contacted list.");
            }
            int a = JOptionPane.showConfirmDialog(this, "Would you like to continue?");
            if (a == JOptionPane.YES_OPTION) {

                new BookMeetings(companies, meetings);

            }

        } else {
            resetEverything();
        }


    }

    private void resetEverything() {
        String def = "";
        cb.setSelectedIndex(0);
        res.setText(def);
        tout.setText(def);
        date.setSelectedIndex(0);
        month.setSelectedIndex(0);
        year.setSelectedIndex(0);
    }

    private boolean bookMeeting(String companyName, Integer dates, String months, Integer years) {
        boolean flag = false;
        for (Company next : companies) {
            if (next.getCompanyName().equals(companyName)) {
                flag = true;
                Meeting meeting = new Meeting(next, years, months, dates);
                boolean result = meetings.addMeeting(meeting);
                if (result) {
                    res.setText("Booking was successful!");
                } else {
                    res.setText("Booking was unsuccessful because two meetings are on the same day!");
                }
            }
        }
        return flag;
    }
}
