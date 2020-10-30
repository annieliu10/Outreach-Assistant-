package ui;

import model.*;
import persistence.CompanyListReader;
import persistence.CompanyListWriter;
import persistence.MeetingListReader;
import persistence.MeetingsListWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;


// Console UI for the outreach tool

public class OutreachApp {
    private static final String STORAGE = "./data/companyList.json";
    private static final String STORAGE2 = "./data/meetingsList.json";
    private CompanyList listOfCompanies;
    private Scanner inputsFromUser;
    private SalesMeetings meetings;

    private CompanyListWriter companyListWriter;
    private CompanyListReader companyListReader;
    private MeetingsListWriter meetingsListWriter;
    private MeetingListReader meetingsListReader;

    //EFFECTS: runs the outreach interface
    public OutreachApp() {
        runOutReach();
    }

    //MODIFIES: this
    //EFFECTS: displays the menu only if the user doesn't press quit
    //automatically saves and loads data
    // referenced this method from the Teller sample
    //https://github.students.cs.ubc.ca/CPSC210/TellerApp
    private void runOutReach() {
        boolean stayOnConsole = true;
        String enterCommand = null;
        inputsFromUser = new Scanner(System.in);
        init();
        loadCompanyListData();
        loadMeetingsListData();
        while (stayOnConsole) {
            display();
            enterCommand = inputsFromUser.next();
            if (enterCommand.equals("q")) {
                saveCompanyListData();
                saveMeetingsListData();
                stayOnConsole = false;
            } else {
                proceedWithCommand(enterCommand);
            }
        }
    }


    //MODIFIES: this
    //EFFECTS: constructs the empty list, meetings and counts for this application
    private void init() {
        listOfCompanies = new CompanyList();
        meetings = new SalesMeetings();

        companyListWriter = new CompanyListWriter(STORAGE);
        companyListReader = new CompanyListReader(STORAGE);
        meetingsListWriter = new MeetingsListWriter(STORAGE2);
        meetingsListReader = new MeetingListReader(STORAGE2);
    }

    //EFFECTS: displays the menu
    private void display() {
        System.out.println(("\nSelect one of the following: "));
        System.out.println("\npr -> Pre-contact");
        System.out.println("\nm -> Mid-contact");
        System.out.println("\npo -> Post-contact");
        System.out.println("\nvu -> View the list of companies that have not been contacted");
        System.out.println("\nvc -> view the contacted list of companies");
        System.out.println("\nvf -> view the followed-up companies");
        System.out.println("\nv -> view booked meetings");
        System.out.println("\nq -> quit");
    }


    //MODIFIES: this
    //EFFECTS: backend operation is returned with different commands
    private void proceedWithCommand(String enterCommand) {
        if (enterCommand.equals("pr")) {
            preContact();
        } else if (enterCommand.equals("m")) {
            midContact();
        } else if (enterCommand.equals("po")) {
            postContact();
        } else if (enterCommand.equals("vu")) {
            printingCompanies(listOfCompanies.getUnContactedCompanies());
        } else if (enterCommand.equals("vc")) {
            printingCompanies(listOfCompanies.getContactedCompanies());
        } else if (enterCommand.equals("vf")) {
            printingCompanies(listOfCompanies.getFollowedUpCompanies());
        } else if (enterCommand.equals("v")) {
            displayBooking(this.meetings.getSalesMeetings());
        } else {
            System.out.println("Please select a valid option");
        }
    }

    //MODIFIES: this
    //EFFECTS: does the pre-contact operation
    private void preContact() {
        CompanySizeRange range = gatherCompanyInfo();
        CompanyIndustryPreferenceOrder order = new CompanyIndustryPreferenceOrder();
        System.out.println("Select how you would like to prioritize the company contact list. ");
        System.out.println("\ni -> by industry");
        System.out.println("\ns -> by size (the smaller, the better)");
        String command = inputsFromUser.next();
        List<Company> prioritizedContact = null;
        prioritizedContact = prioritizeContacting(order, command, range, prioritizedContact);
        System.out.println("The following is the order in which you should contact these companies.");
        printingCompanies(prioritizedContact);

    }

    //EFFECTS: prioritizes the contact list based on user's command input (whether it be size or industry)
    private List<Company> prioritizeContacting(CompanyIndustryPreferenceOrder order, String command,
                                               CompanySizeRange range, List<Company> prioritizedContact) {
        if (command.equals("i")) {
            System.out.println("Type p to enter your preference order for the industries or d for the default order");
            String command1 = inputsFromUser.next();
            if (command1.equals("p")) {
                displayIndustries("\ni -> Information Technology",
                        "\nb -> Business Admin/ Project MGMT", "\nm -> Marketing", "\ne -> Engineering");
                System.out.println("Type in order and press return for each");
                String industry1 = convertFromCommandToString(inputsFromUser.next());
                String industry2 = convertFromCommandToString(inputsFromUser.next());
                String industry3 = convertFromCommandToString(inputsFromUser.next());
                String industry4 = convertFromCommandToString(inputsFromUser.next());
                order.changePreferenceOrder(industry1, industry2, industry3, industry4);
            }
            prioritizedContact = listOfCompanies.prioritizeContactsBasedOnIndustry(order);

        } else if (command.equals("s")) {
            prioritizedContact = listOfCompanies.prioritizeContactsBasedOnSize(range);

        } else {
            System.out.println("Please select a valid option");
        }
        return prioritizedContact;
    }


    //MODIFIES: this
    //EFFECTS: gathers information from the user about the company as well as their preference for the size range
    private CompanySizeRange gatherCompanyInfo() {
        System.out.println("We would like to know your preference for company sizes. " + "Please enter a range: ");
        System.out.println("Lower Bound: ");
        int lowerBound = inputsFromUser.nextInt();
        System.out.println("Upper Bound: ");
        int upperBound = inputsFromUser.nextInt();
        CompanySizeRange range = new CompanySizeRange(lowerBound, upperBound);
        addingCompanies(range);
        return range;
    }

    //MODIFIES: this
    //EFFECTS: adds companies to the list of companies
    private void addingCompanies(CompanySizeRange range) {
        boolean flag = true;
        System.out.println("Please enter company information below");
        while (flag) {
            System.out.println("Enter company size: ");
            int size = inputsFromUser.nextInt();
            System.out.println("Select the industry the company is in");
            displayIndustries("\ni -> Information Technology",
                    "\nb -> Business Admin/ Project MGMT", "\nm -> Marketing", "\ne -> Engineering");
            String industry = inputsFromUser.next();
            industry = convertFromCommandToString(industry);
            System.out.println("Enter the company name: ");
            String companyName = inputsFromUser.next();
            System.out.println("Enter the recruiter name: ");
            String employerName = inputsFromUser.next();
            Company company = new Company(size, industry, companyName, employerName);
            listOfCompanies.addNewCompany(company, range);
            System.out.println("Enter anything to continue or type q to quit.");
            String command = inputsFromUser.next();
            if (command.equals("q")) {
                flag = false;
            }
        }
    }

    //EFFECTS: displays industries in the interface
    private void displayIndustries(String s2, String s3, String s4, String s5) {
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(s4);
        System.out.println(s5);
    }


    //EFFECTS: converts from user command input to actual word strings for the methods to use
    private String convertFromCommandToString(String command) {
        if (command.equals("i")) {
            return "Information Technology";
        } else if (command.equals("b")) {
            return "Business Admin/ Project MGMT";
        } else if (command.equals("m")) {
            return "Marketing";
        }
        return "Engineering";
    }


    //MODIFIES: this
    //EFFECTS: displays the mid-contact option menus and does mid-contact operations for the user
    private void midContact() {
        System.out.println("\nu -> update the companies that have been contacted");
        System.out.println("\nb -> book a new meeting for contacted companies");
        System.out.println("\nc -> check which meetings are least spaced out");
        String command = inputsFromUser.next();
        doMidContactOptions(command);
    }


    //MODIFIES: this
    //EFFECTS: does different operations based on user input for mid-contact section
    private void doMidContactOptions(String command) {
        if (command.equals("b")) {
            System.out.println("The list of contacted companies that haven't been booked are as follows:");
            //make sure they haven't been booked already
            printingCompanies(meetings.filterBookedMeetingsInContacted(listOfCompanies.getContactedCompanies()));
            bookMeetings();

        } else if (command.equals("c")) {
            List<Meeting> squishedMeetings = meetings.checkMostMeetings();
            System.out.println("Here is a list of meetings that are the least spaced out. ");
            displayBooking(squishedMeetings);

        } else if (command.equals("u")) {
            updateContacted();
            listOfCompanies.updateListsBasedOnContactStatuses();

        } else {
            System.out.println("Please select a valid option");
        }
    }


    //MODIFIES: this
    //EFFECTS: updates the list of companies that have been contacted
    private void updateContacted() {
        boolean flag = true;
        while (flag) {
            System.out.println("Enter the company that has been contacted");
            String companyName = inputsFromUser.next();
            System.out.println("Enter the company's interest level on a scale of 1 to 10: ");
            int interestLevel = inputsFromUser.nextInt();
            for (Company next : listOfCompanies.getUnContactedCompanies()) {
                if (next.getCompanyName().equals(companyName)) {
                    next.contacted(interestLevel);
                }
            }
            System.out.println("Enter c to continue or q to quit");
            String commandss = inputsFromUser.next();
            if (commandss.equals("q")) {
                flag = false;
            }
        }
    }


    //EFFECTS: prompts user input for meeting information when booking meetings and quits operation if user types quit
    private void bookMeetings() {
        boolean flag = true;
        while (flag) {
            System.out.println("Enter the company name you want to book a meeting for: ");
            String companyName = inputsFromUser.next();
            System.out.println("Enter the year: ");
            int year = inputsFromUser.nextInt();
            System.out.println("Enter the month in full: ");
            String month = inputsFromUser.next();
            System.out.println("Enter the date: ");
            int date = inputsFromUser.nextInt();
            addBookingInner(companyName, year, month, date);
            System.out.println("Type c to continue adding or q to quit");
            String command3 = inputsFromUser.next();
            if (command3.equals("q")) {
                flag = false;
            }
        }
    }


    //MODIFIES: this
    //EFFECTS: does each individual booking for one company at a time given the company information as well as date
    private void addBookingInner(String companyName, int year, String month, int date) {
        boolean flag = false;
        for (Company next : listOfCompanies.getContactedCompanies()) {
            if (next.getCompanyName().equals(companyName)) {
                flag = true;
                Meeting meeting = new Meeting(next, year, month, date);
                boolean result = meetings.addMeeting(meeting);
                if (result) {
                    System.out.println("Booking was successful");
                } else {
                    System.out.println("Booking was unsuccessful");
                }
            }
        }
        if (!flag) {
            System.out.println("The company for which you want to book a meeting isn't in the contacted list.");
        }
    }

    //MODIFIES: this
    //EFFECTS: does the post contact operation
    private void postContact() {
        postContactDisplay("\nu -> update the companies that have been followed up",
                "\np -> prioritize the companies for follow-up");
        String command = inputsFromUser.next();
        if (command.equals("u")) {
            updateFollowedUpCompanies();
            listOfCompanies.updateListsBasedOnFollowedUpCompanies();

        } else if (command.equals("p")) {
            List<Company> companies = listOfCompanies.prioritizeFollowUp();
            System.out.println("The following is the order in which you should follow up with these companies.");
            printingCompanies(companies);

        } else {
            System.out.println("Please select a valid option");
        }
    }

    //MODIFIES: this
    // EFFECTS: updates the companies that have been followed up
    private void updateFollowedUpCompanies() {
        boolean flag = true;
        while (flag) {
            System.out.println("Enter the company that has been followed up");
            String companyName = inputsFromUser.next();
            for (Company next : listOfCompanies.getContactedCompanies()) {
                if (next.getCompanyName().equals(companyName)) {
                    next.followedUp();
                }
            }
            System.out.println("Enter c to continue or q to quit");
            String commandss = inputsFromUser.next();
            if (commandss.equals("q")) {
                flag = false;
            }
        }
    }


    //EFFECTS: displays the options for post-contact
    private void postContactDisplay(String s, String s2) {
        System.out.println(s);
        System.out.println(s2);

    }


    //EFFECTS: prints company names given companiesss
    private void printingCompanies(List<Company> companiesss) {
        for (Company next : companiesss) {
            System.out.println(next.getCompanyName());
        }
    }

    //EFFECTS; displays booking information given meetings
    private void displayBooking(List<Meeting> meetings) {
        int count = 1;
        for (Meeting next : meetings) {
            System.out.println("Meeting" + count + ":");
            System.out.println(next.getCompany().getCompanyName());
            System.out.println(next.getDate().get(Calendar.YEAR) + "," + (next.getDate().get(Calendar.MONTH) + 1) + ","
                    + next.getDate().get(Calendar.DATE));
            count++;
        }
    }


    //EFFECTS: save the data to file
    private void saveCompanyListData() {
        try {
            companyListWriter.open();
            companyListWriter.write(listOfCompanies);
            companyListWriter.close();
            System.out.println("Saved company list data");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to this file");
        }

    }


    private void saveMeetingsListData() {
        try {
            meetingsListWriter.open();
            meetingsListWriter.write(meetings);
            meetingsListWriter.close();
            System.out.println("Saved meetings data");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to this file");
        }

    }

    private void loadCompanyListData() {
        try {
            listOfCompanies = companyListReader.read();
            System.out.println("Loaded company list data");
        } catch (IOException e) {
            System.out.println("Unable to load data");
        }
    }

    private void loadMeetingsListData() {
        try {
            meetings = meetingsListReader.read();
            System.out.println("Loaded meetings data");
        } catch (IOException e) {
            System.out.println("Unable to load data");
        }
    }
}
