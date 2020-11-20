package ui;

import model.Company;
import model.CompanyList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


//A drop-down menu that shows the list of companies that have not been contacted so that the user can update the
// contacted ones
public class DropDownMenuForUpdate extends JFrame implements ActionListener {

    private List<Company> companies;
    private CompanyList listOfCompanies;
    private JComboBox cb;

    private JLabel interestLevel;

    private JLabel selectCompany;
    private JTextField tinterestLevel;

    //REQUIRES: companies are in the list of uncontacted companies and but have been contacted in person
    //EFFECTS: creates a drop down menu that displays the uncontacted companies for the users to select
    DropDownMenuForUpdate(List<Company> companies, CompanyList listOfCompanies) {
        this.companies = companies;
        this.listOfCompanies = listOfCompanies;

        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);

        label.setSize(400, 100);

        JButton b = new JButton("Submit");
        b.setBounds(220, 110, 75, 20);
        b.addActionListener(this);
        String[] companyLabels;
        companyLabels = new String[]{""};

        cb = new JComboBox(companyLabels);
        for (Company next : companies) {
            cb.addItem(next.getCompanyName());
        }
        cb.setBounds(200, 50, 200, 20);

        selectCompany();
        enterInterest();

        add(cb);
        add(label);
        add(b);

        settingsForTheFrame();
    }

    //MODIFIES: this
    //EFFECTS: sets the panel
    private void settingsForTheFrame() {
        // settings for the frame
        setLayout(null);
        setBounds(300, 90, 500, 160);
        setVisible(true);
        setTitle("Update contacted companies");
        setResizable(false);
    }

    //MODIFIES: this
    //EFFECTS: sets the label "Company's interest level" on the panel for the user to see what they need to input
    private void enterInterest() {
        interestLevel = new JLabel("Company's interest level(1-10)");
        interestLevel.setFont(new Font("Arial", Font.PLAIN, 12));
        interestLevel.setSize(250, 20);
        interestLevel.setLocation(20, 80);
        add(interestLevel);

        tinterestLevel = new JTextField();
        tinterestLevel.setFont(new Font("Arial", Font.PLAIN, 15));
        tinterestLevel.setSize(200, 20);
        tinterestLevel.setLocation(200, 80);
        add(tinterestLevel);
    }

    //MODIFIES: this
    //EFFECTS: sets the label "Select the company" on the panel for display
    private void selectCompany() {
        selectCompany = new JLabel("Select the company");
        selectCompany.setFont(new Font("Arial", Font.PLAIN, 12));
        selectCompany.setSize(250, 20);
        selectCompany.setLocation(20, 50);
        add(selectCompany);
    }


    //MODIFIES: this
    //EFFECTS: updates the company that is selected in the backend and prompts the user to see whether they want to
    // continue update
    @Override
    public void actionPerformed(ActionEvent e) {
        for (Company next : companies) {
            if (String.valueOf(cb.getItemAt(cb.getSelectedIndex())).equals(next.getCompanyName())) {
                next.contacted(Integer.parseInt(tinterestLevel.getText()));
            }
        }
        listOfCompanies.updateListsBasedOnContactStatuses();
        int a = JOptionPane.showConfirmDialog(this, "Would you like to continue?");
        if (a == JOptionPane.YES_OPTION) {

            DropDownMenuForUpdate selectOption = new DropDownMenuForUpdate(companies, listOfCompanies);

        }
    }
}
