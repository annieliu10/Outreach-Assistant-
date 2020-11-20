package ui;

import model.Company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


//A panel that displays the list of companies that were prioritized in the order in which they should be contacted
public class DisplayPrioritizedCompanies extends JFrame {


    private List<Company> companies;

    private JTextArea emptyTextArea;


    //REQUIRES: companies either have not been contacted before or have been contacted but not followed up
    //EFFECTS: displays the list of companies that have been prioritized in the order in which they should be contacted
    // in an empty text area
    DisplayPrioritizedCompanies(List<Company> companies) {

        this.companies = companies;

        emptyTextArea = new JTextArea();
        emptyTextArea.setFont(new Font("Arial", Font.PLAIN, 15));
        emptyTextArea.setSize(300, 300);
        emptyTextArea.setLocation(50, 100);
        emptyTextArea.setLineWrap(true);
        add(emptyTextArea);


        setLayout(null);
        setBounds(300, 90, 500, 500);
        setVisible(true);
        setTitle("List of companies you should contact based on this order");


        setResizable(false);


        ArrayList<String> textToBeDisplayed = new ArrayList<>();

        int i = 1;
        for (Company next : companies) {
            textToBeDisplayed.add("\n" + i + " . " + next.getCompanyName());
            i++;
        }

        textToBeDisplayed.toString();
        emptyTextArea.setText(String.valueOf(textToBeDisplayed));
    }
}
