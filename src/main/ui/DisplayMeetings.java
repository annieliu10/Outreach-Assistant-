package ui;

import model.Company;
import model.Meeting;
import model.SalesMeetings;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DisplayMeetings extends JFrame {


    private Container container;

    private JLabel res;

    private JTextArea emptyTextArea;

    private List<Meeting> meetings;

    DisplayMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
        emptyTextArea = new JTextArea();
        emptyTextArea.setFont(new Font("Arial", Font.PLAIN, 15));
        emptyTextArea.setSize(300, 300);
        emptyTextArea.setLocation(50, 100);
        emptyTextArea.setLineWrap(true);
        add(emptyTextArea);


        setLayout(null);
        setBounds(300, 90, 500, 500);
        setVisible(true);
        setTitle("Booked meetings");


        setResizable(false);
        ArrayList<String> textToBeDisplayed = new ArrayList<>();

        for (Meeting next : meetings) {
            textToBeDisplayed.add("\n" + next.getCompany().getCompanyName() + " , " + next.getDate().get(Calendar.YEAR)
                    + "," + (next.getDate().get(Calendar.MONTH) + 1) + ","
                    + next.getDate().get(Calendar.DATE));
        }


        textToBeDisplayed.toString();
        emptyTextArea.setText(String.valueOf(textToBeDisplayed));
    }

}


