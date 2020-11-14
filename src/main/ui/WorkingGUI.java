package ui;

import model.*;
import persistence.CompanyListReader;
import persistence.CompanyListWriter;
import persistence.MeetingListReader;
import persistence.MeetingsListWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static jdk.nashorn.internal.runtime.GlobalFunctions.parseInt;


//https://www.javatpoint.com/java-swing
//https://www.tutorialspoint.com/how-to-add-background-image-to-jframe-in-java
public class WorkingGUI extends JFrame implements ActionListener {
    JMenu menu;
    JMenu midContact;
    JMenu postContact;
    JMenuItem preContact, i2, i4, i7, i10, updateContact, book, check, updateFollowUp,
            prioritizeFollowUp;

    private static final String STORAGE = "./data/companyList.json";
    private static final String STORAGE2 = "./data/meetingsList.json";

    private CompanyList companyList;

    private CompanyListReader companyListReader;
    private CompanyListWriter companyListWriter;
    private MeetingsListWriter meetingsListWriter;
    private MeetingListReader meetingListReader;

    WorkingGUI() {
        init();

        companyList = loadDataPopUpWindow();


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

    private void init() {
        companyList = new CompanyList();

        companyListWriter = new CompanyListWriter(STORAGE);
        companyListReader = new CompanyListReader(STORAGE);
        meetingsListWriter = new MeetingsListWriter(STORAGE2);
        meetingListReader = new MeetingListReader(STORAGE2);
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

        menu.add(i10);



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
        } else if (e.getSource() == i10) {
            saveDataPopUpWindow();
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        }


    }


    public CompanyList loadDataPopUpWindow() {
        int a = JOptionPane.showConfirmDialog(this, "Would you like to load the data?");

        if (a == JOptionPane.YES_OPTION) {
            try {
                CompanyList companies = companyListReader.read();
                return companies;
            } catch (IOException e) {
                JFrame frame = new JFrame();
                frame.setTitle("Error message");
                frame.setBounds(100, 100, 300, 200);
                JLabel error = new JLabel("Unable to load data");
                error.setFont(new Font("Arial", Font.PLAIN, 15));
                error.setSize(300, 20);
                error.setLocation(10, 10);
                frame.add(error);

            }
        }


        return new CompanyList();
    }

    public void saveDataPopUpWindow() {
        int a = JOptionPane.showConfirmDialog(this, "Would you like to save the data?");
        if (a == JOptionPane.YES_OPTION) {
            try {
                companyListWriter.open();
                companyListWriter.write(companyList);
                companyListWriter.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
}




