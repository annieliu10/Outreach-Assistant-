package ui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import static jdk.nashorn.internal.runtime.GlobalFunctions.parseInt;


//https://www.javatpoint.com/java-swing
//https://www.tutorialspoint.com/how-to-add-background-image-to-jframe-in-java
public class WorkingGUI extends JFrame implements ActionListener {
    JMenu menu;
    JMenu midContact;
    JMenu postContact;
    JMenuItem preContact, i2, i4, i7, i8, i9, i10, updateContact, book, check, updateFollowUp,
            prioritizeFollowUp;



    private CompanyList companyList;




    WorkingGUI() {
        companyList = new CompanyList();


        setTitle("AIESEC IGT Operations");
        setLayout(new FlowLayout());


        JLabel background = new JLabel(new ImageIcon("./aiesecImage.jpg"));
        add(background);
        background.setLayout(new FlowLayout());

        setItems();

        setAction();


        setFieldAndLabels();

        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setFieldAndLabels() {


    }

    private void setAction() {
        preContact.addActionListener(this);
        updateContact.addActionListener(this);
        book.addActionListener(this);
        check.addActionListener(this);
        prioritizeFollowUp.addActionListener(this);
        updateFollowUp.addActionListener(this);
        i4.addActionListener(this);

        i7.addActionListener(this);
        i8.addActionListener(this);
        i9.addActionListener(this);
        i10.addActionListener(this);
    }


    private void setItems() {
        JMenuBar mb = new JMenuBar();

        menu = new JMenu("Select one of the following: ");


        preContact = new JMenuItem("Pre-Contact");


        midContact = new JMenu("Mid-Contact");
        updateContact = new JMenuItem("update the companies that have been contacted");
        book = new JMenuItem("book a new meeting for contacted companies");
        check = new JMenuItem("check which meetings are least spaced out");


        postContact = new JMenu("Post-Contact");
        prioritizeFollowUp = new JMenuItem("prioritize the companies for follow-up");
        updateFollowUp = new JMenuItem("update the companies that have been followed up");


        i4 = new JMenuItem("View lists of companies");
        i7 = new JMenuItem("View booked meetings");
        i8 = new JMenuItem("Load data");
        i9 = new JMenuItem("Save data");
        i10 = new JMenuItem("Quit");


        midContact.add(updateContact);
        midContact.add(book);
        midContact.add(check);

        postContact.add(prioritizeFollowUp);
        postContact.add(updateFollowUp);


        menu.add(preContact);
        menu.add(midContact);
        menu.add(postContact);
        menu.add(i4);
        menu.add(i7);
        menu.add(i8);
        menu.add(i9);
        menu.add(i10);


       /* //a group of radio button menu items
        menu.addSeparator();
        ButtonGroup group = new ButtonGroup();
        rbMenuItem = new JRadioButtonMenuItem("A radio button menu item");
        rbMenuItem.setSelected(true);
        rbMenuItem.setMnemonic(KeyEvent.VK_R);
        group.add(rbMenuItem);
        menu.add(rbMenuItem);

        rbMenuItem = new JRadioButtonMenuItem("Another one");
        rbMenuItem.setMnemonic(KeyEvent.VK_O);
        group.add(rbMenuItem);
        menu.add(rbMenuItem);*/


        mb.add(menu);

        setJMenuBar(mb);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == preContact) {
            String lowerBound = JOptionPane.showInputDialog("Enter the lower bound");
            String upperBound = JOptionPane.showInputDialog("Enter the upper bound");

            CompanySizeRange range = new CompanySizeRange(Integer.parseInt(lowerBound), Integer.parseInt(upperBound));

            MyFrame f = new MyFrame(range, companyList);


        } else if (e.getSource() == i4) {
            new ViewListsOfCompanies(companyList);
        } else if (e.getSource() == updateContact) {
            new DropDownMenuForUpdate(companyList.getUnContactedCompanies());
        } else if (e.getSource() == prioritizeFollowUp) {
            List<Company> companies = companyList.prioritizeFollowUp();
            DisplayPrioritizedCompanies displayPrioritizedCompanies = new DisplayPrioritizedCompanies(companies);
        } else if (e.getSource() == updateFollowUp) {
            new DropDownMenuForUpdate(companyList.getContactedCompanies());
        }
    }



}




