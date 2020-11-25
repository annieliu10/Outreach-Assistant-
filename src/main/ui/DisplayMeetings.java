package ui;

import model.Company;
import model.Meeting;
import model.SalesMeetings;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


//A panel that displays the list of booked meetings to the user
public class DisplayMeetings extends JFrame {


    private JTextArea emptyTextArea;




    //REQUIRES: the meetings have to be valid and booked
    //EFFECTS: displays the list of meetings that were booked successfully in a white text area
    DisplayMeetings(List<Meeting> meetings) {

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


