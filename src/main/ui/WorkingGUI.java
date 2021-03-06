package ui;

import exceptions.InvalidSize;
import model.*;
import persistence.CompanyListReader;
import persistence.CompanyListWriter;
import persistence.MeetingListReader;
import persistence.MeetingsListWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


//The main page of the application with a menu and a AIESEC background
//https://www.javatpoint.com/java-swing
//https://www.tutorialspoint.com/how-to-add-background-image-to-jframe-in-java

public class WorkingGUI extends JFrame implements ActionListener {
    JMenu menu;
    JMenu midContact;
    JMenu postContact;
    JMenuItem preContact;
    JMenuItem viewCompanies;
    JMenuItem viewMeetings;

    JMenuItem updateContact;
    JMenuItem book;
    JMenuItem check;
    JMenuItem updateFollowUp;
    JMenuItem prioritizeFollowUp;

    private static final String STORAGE = "./data/companyList.json";
    private static final String STORAGE2 = "./data/meetingsList.json";

    private CompanyList companyList;
    private SalesMeetings meetings;

    private CompanyListReader companyListReader;
    private CompanyListWriter companyListWriter;
    private MeetingsListWriter meetingsListWriter;
    private MeetingListReader meetingListReader;


    //EFFECTS: displays the menu of options the user can choose from, title, background image of AIESEC
    public WorkingGUI() {
        init();

        loadDataPopUpWindow();

        setTitle("AIESEC IGT Operations");
        setLayout(new FlowLayout());

        JLabel background = new JLabel(new ImageIcon("./aiesecImage.jpg"));
        add(background);
        background.setLayout(new FlowLayout());

        setItems();

        setAction();

        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        windowCloseSave();

    }

    private void windowCloseSave() {
        addWindowListener(new WindowListener() {
            public void windowOpened(WindowEvent e) {
            }

            public void windowClosing(WindowEvent e) {
                saveDataPopUpWindow();
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
    }


    //MODIFIES: this
    //EFFECTS: constructs an empty company list, meetings list, as well as the designated storage routes
    private void init() {
        companyList = new CompanyList();
        meetings = new SalesMeetings();
        companyListWriter = new CompanyListWriter(STORAGE);
        companyListReader = new CompanyListReader(STORAGE);
        meetingsListWriter = new MeetingsListWriter(STORAGE2);
        meetingListReader = new MeetingListReader(STORAGE2);
    }


    //MODIFIES: this
    //EFFECTS: sets action listeners for the options chosen by the user in the menu
    private void setAction() {
        preContact.addActionListener(this);
        updateContact.addActionListener(this);
        book.addActionListener(this);
        check.addActionListener(this);
        prioritizeFollowUp.addActionListener(this);
        updateFollowUp.addActionListener(this);
        viewCompanies.addActionListener(this);

        viewMeetings.addActionListener(this);


    }


    //MODIFIES: this
    //EFFECTS: displays texts for the menu options
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
        viewCompanies = new JMenuItem("View lists of companies");
        viewMeetings = new JMenuItem("View booked meetings");

        addMenuItems();

        mb.add(menu);

        setJMenuBar(mb);
    }

    //MODIFIES: this
    //EFFECTS: adds the menu items to the menu
    private void addMenuItems() {
        midContact.add(updateContact);
        midContact.add(book);
        midContact.add(check);
        postContact.add(prioritizeFollowUp);
        postContact.add(updateFollowUp);
        menu.add(preContact);
        menu.add(midContact);
        menu.add(postContact);
        menu.add(viewCompanies);
        menu.add(viewMeetings);

    }


    //MODIFIES: this
    //EFFECTS: performs the option that is selected by the user
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == preContact) {
            preContactPane();
        } else if (e.getSource() == viewCompanies) {
            new ViewListsOfCompanies(companyList);
        } else if (e.getSource() == updateContact) {
            new UpdateContacted(companyList.getUnContactedCompanies(), companyList);
        } else if (e.getSource() == prioritizeFollowUp) {
            List<Company> companies = companyList.prioritizeFollowUp();
            DisplayPrioritizedCompanies displayPrioritizedCompanies = new DisplayPrioritizedCompanies(companies);
        } else if (e.getSource() == updateFollowUp) {
            new UpdateFollowedUp(companyList.getContactedCompanies(), companyList);
        } else if (e.getSource() == book) {
            new BookMeetings(companyList.getContactedCompanies(), meetings);
        } else if (e.getSource() == viewMeetings) {
            new DisplayMeetings(meetings.getSalesMeetings());
        } else if (e.getSource() == check) {
            List<Meeting> squishedMeetings = meetings.checkMostMeetings();
            new DisplayMeetings(squishedMeetings);
        }
    }

    //MODIFIES: this
    //EFFECTS: prompts the user to input the lower and upper bound for the range of company sizes
    private void preContactPane() {
        String lowerBound = JOptionPane.showInputDialog("Enter the lower bound");
        String upperBound = JOptionPane.showInputDialog("Enter the upper bound");
        CompanySizeRange range = null;
        try {
            range = new CompanySizeRange(Integer.parseInt(lowerBound), Integer.parseInt(upperBound));
        } catch (InvalidSize invalidSize) {
            invalidSize.getMessage();
        }
        CompanyForm f = new CompanyForm(range, companyList);
    }


    //MODIFIES: this
    //EFFECTS: displays a popup window asking whether the user wants to load stored data from before
    public void loadDataPopUpWindow() {
        int a = JOptionPane.showConfirmDialog(this, "Would you like to load the data?");

        if (a == JOptionPane.YES_OPTION) {
            try {
                CompanyList companies = companyListReader.read();
                this.companyList = companies;
                this.meetings = meetingListReader.read();

            } catch (IOException e) {
                errorFrame("Unable to load data");
            }
        }

    }

    //MODIFIES: this
    //EFFECTS: displays the option to save data when the user quits the application
    public void saveDataPopUpWindow() {
        int a = JOptionPane.showConfirmDialog(this, "Would you like to save the data?");
        if (a == JOptionPane.YES_OPTION) {
            try {
                companyListWriter.open();
                companyListWriter.write(companyList);
                companyListWriter.close();
                meetingsListWriter.open();
                meetingsListWriter.write(meetings);
                meetingsListWriter.close();
            } catch (FileNotFoundException e) {
                errorFrame("Unable to save data");
            }

        }
    }

    //MODIFIES: this
    //EFFECTS: displays error message if data is not loaded or saved properly.
    private void errorFrame(String s) {
        JFrame frame = new JFrame();
        frame.setTitle("Error message");
        frame.setBounds(100, 100, 300, 200);
        JLabel error = new JLabel(s);
        error.setFont(new Font("Arial", Font.PLAIN, 15));
        error.setSize(300, 20);
        error.setLocation(10, 10);
        frame.add(error);
    }
}




