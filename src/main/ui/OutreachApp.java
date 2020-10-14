package ui;

import model.*;

import java.util.Calendar;
import java.util.List;
import java.util.Scanner;


// Console UI for the outreach tool
public class OutreachApp {
    private CompanyList listOfCompanies;
    private Scanner inputsFromUser;
    private SalesMeetings meetings;
    private int count;
    private int secondLevelCount;

    public OutreachApp() {
        runOutReach();
    }

    // referenced this method from the Teller sample
    private void runOutReach() {
        boolean stayOnConsole = true;
        String enterCommand = null;
        inputsFromUser = new Scanner(System.in);
        init();
        while (stayOnConsole) {
            display();
            enterCommand = inputsFromUser.next();
            if (enterCommand.equals("q")) {
                stayOnConsole = false;
            } else {
                proceedWithCommand(enterCommand);
            }
        }
    }


    private void init() {
        listOfCompanies = new CompanyList();
        meetings = new SalesMeetings();
        count = 0;
        secondLevelCount = 0;
    }

    private void display() {
        System.out.println(("\nSelect one of the following: "));
        if (count == 0) {
            System.out.println("\npr -> Pre-contact");
        } else if (secondLevelCount >= 1) {
            System.out.println("\npr -> Pre-contact");
            System.out.println("\nm -> Mid-contact");
            System.out.println("\npo -> Post-contact");
        } else if (count >= 1) {
            System.out.println("\npr -> Pre-contact");
            System.out.println("\nm -> Mid-contact");
        }
        System.out.println("\nq -> quit");
    }


    private void proceedWithCommand(String enterCommand) {
        if (enterCommand.equals("pr")) {
            preContact();
        } else if (enterCommand.equals("m")) {
            midContact();
        } else if (enterCommand.equals("po")) {
            postContact();
        } else {
            System.out.println("Please select a valid option");
        }
    }

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
            count++;
        } else if (command.equals("s")) {
            prioritizedContact = listOfCompanies.prioritizeContactsBasedOnSize(range);
            count++;
        } else {
            System.out.println("Please select a valid option");
        }
        return prioritizedContact;
    }


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
            listOfCompanies.addCompany(company, range);
            System.out.println("Enter anything to continue or type q to quit.");
            String command = inputsFromUser.next();
            if (command.equals("q")) {
                flag = false;
            }
        }
    }

    private void displayIndustries(String s2, String s3, String s4, String s5) {
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(s4);
        System.out.println(s5);
    }


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


    private void midContact() {
        System.out.println("\nu -> update the companies that have been contacted");
        System.out.println("\nb -> book a new meeting for contacted companies");
        System.out.println("\nv -> view booked meetings");
        System.out.println("\nc -> check which meetings are least spaced out");
        String command = inputsFromUser.next();
        doMidContactOptions(command);

    }

    private void doMidContactOptions(String command) {
        if (command.equals("v")) {
            displayBooking(this.meetings.getSalesMeetings());
            secondLevelCount++;
        } else if (command.equals("b")) {
            bookMeetings();
            secondLevelCount++;
        } else if (command.equals("c")) {
            List<Meeting> squishedMeetings = meetings.checkMostMeetings();
            System.out.println("Here is a list of meetings that are the least spaced out. ");
            displayBooking(squishedMeetings);
            secondLevelCount++;
        } else if (command.equals("u")) {
            updateContacted();
            listOfCompanies.updateListsBasedOnContactStatuses();
            secondLevelCount++;
        } else {
            System.out.println("Please select a valid option");
        }
    }

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

    private void postContact() {
        postContactDisplay("\nu -> update the companies that have been followed up",
                "\np -> prioritize the companies for follow-up", "\nvu -> view the list of companies "
                        + "that haven't been contacted", "\nvc -> view the contacted list of companies",
                "\nvf -> view the list of companies that have been followed up");
        String command = inputsFromUser.next();
        if (command.equals("u")) {
            updateFollowedUpCompanies();
            listOfCompanies.updateListsBasedOnFollowedUpCompanies();
        } else if (command.equals("p")) {
            List<Company> companies = listOfCompanies.prioritizeFollowUp();
            System.out.println("The following is the order in which you should follow up with these companies.");
            printingCompanies(companies);
        } else if (command.equals("vu")) {
            printingCompanies(listOfCompanies.getUnContactedCompanies());
        } else if (command.equals("vc")) {
            printingCompanies(listOfCompanies.getContactedCompanies());
        } else if (command.equals("vf")) {
            printingCompanies(listOfCompanies.getFollowedUpCompanies());
        } else {
            System.out.println("Please select a valid option");
        }
    }

    private void updateFollowedUpCompanies() {
        boolean flag = true;
        while (flag) {
            System.out.println("Enter the company that has been followed up");
            String companyName = inputsFromUser.next();
            for (Company next : listOfCompanies.getUnContactedCompanies()) {
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


    private void postContactDisplay(String s, String s2, String s3, String s4, String s5) {
        System.out.println(s);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(s4);
        System.out.println(s5);
    }


    private void printingCompanies(List<Company> companiesss) {
        for (Company next : companiesss) {
            System.out.println(next.getCompanyName());
        }
    }

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


}
