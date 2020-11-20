package ui;

import model.Company;
import model.CompanyIndustryPreferenceOrder;
import model.CompanyList;
import model.CompanySizeRange;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;


// Referenced from https://docs.oracle.com/javase/tutorial/uiswing/examples/components/CheckBoxDemoProject/src/
// components/CheckBoxDemo.java
// A Checkbox window to select the way in which the user wants the companies to be prioritized
public class PreferenceWindow extends JFrame implements ActionListener {


    private Container container;
    private JRadioButton sizeButton;
    private JRadioButton industryButton;
    private ButtonGroup buttons;

    CompanyList listOfCompanies;
    CompanySizeRange range;

    List<Company> prioritizeContacts;
    CompanyIndustryPreferenceOrder order;

    //REQUIRES: listofCompanies has to be valid and range is a valid range
    //EFFECTS: displays a checkbox window that has options for the user to select the way in which the user wants
    // the companies to be prioritized
    PreferenceWindow(CompanyList listOfCompanies, CompanySizeRange range) {

        this.listOfCompanies = listOfCompanies;
        this.range = range;

        setTitle("Select the way in which you would like to prioritize");
        setBounds(50, 50, 400, 100);

        setResizable(false);


        container = getContentPane();
        container.setLayout(null);

        industryButton();

        sizeButton();


        buttons = new ButtonGroup();
        buttons.add(sizeButton);
        buttons.add(industryButton);
        setVisible(true);

    }


    //MODIFIES: this
    //EFFECTS: displays the size button
    private void sizeButton() {
        sizeButton = new JRadioButton("Prioritize by size");
        sizeButton.setFont(new Font("Arial", Font.PLAIN, 15));
        sizeButton.setSelected(false);
        sizeButton.setSize(200, 50);
        sizeButton.setLocation(20, 40);
        sizeButton.addActionListener(this);
        container.add(sizeButton);
    }

    //MODIFIES: this
    //EFFECTS: displays the industry button
    private void industryButton() {
        industryButton = new JRadioButton("Prioritize by industry");
        industryButton.setFont(new Font("Arial", Font.PLAIN, 15));
        industryButton.setSelected(true);
        industryButton.setSize(200, 20);
        industryButton.setLocation(20, 20);
        industryButton.addActionListener(this);
        container.add(industryButton);
    }


    //MODIFIES: this
    //EFFECTS: prioritizes the companies in the backend based on the option the user selects and then prompts another
    // panel that includes a list of companies that are prioritized
    @Override
    public void actionPerformed(ActionEvent e) {
        if (sizeButton.isSelected()) {
            prioritizeContacts = listOfCompanies.prioritizeContactsBasedOnSize(range);

            DisplayPrioritizedCompanies display = new DisplayPrioritizedCompanies(prioritizeContacts);
        } else {
            order = new CompanyIndustryPreferenceOrder();
            prioritizeContacts = listOfCompanies.prioritizeContactsBasedOnIndustry(order);
            DisplayPrioritizedCompanies display = new DisplayPrioritizedCompanies(prioritizeContacts);
        }

    }

}
