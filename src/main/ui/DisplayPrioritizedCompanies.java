package ui;

import model.Company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DisplayPrioritizedCompanies extends JFrame {


    private List<Company> companies;

    private Container container;

    private JLabel res;

    private JTextArea emptyTextArea;


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
