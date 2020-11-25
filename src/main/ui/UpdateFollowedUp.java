package ui;

import model.Company;
import model.CompanyList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


// A panel that updates the companies that have been followed up physically but not updated to the followed-up status
public class UpdateFollowedUp extends JFrame implements ActionListener {

    private List<Company> companies;
    private CompanyList listOfCompanies;
    private JComboBox cb;


    private JLabel selectCompany;


    //REQUIRES: companyList has to be valid and companies are contacted
    //EFFECTS: updates the companies that have been followed up physically but not yet updated to the followed-up
    // status; displays the companies in a drop down menu
    public UpdateFollowedUp(List<Company> companies, CompanyList companyList) {
        this.companies = companies;
        this.listOfCompanies = companyList;

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

        add(cb);
        add(label);
        add(b);

        settingsForTheFrame();

    }

    //MODIFIES: this
    //EFFECTS: sets the frame for the panel
    private void settingsForTheFrame() {
        // settings for the frame
        setLayout(null);
        setBounds(300, 90, 500, 160);
        setVisible(true);
        setTitle("Update followed up companies");
        setResizable(false);
    }

    //MODIFIES: this
    //EFFECTS: sets a label of "select the company" to display to the user
    private void selectCompany() {
        selectCompany = new JLabel("Select the company");
        selectCompany.setFont(new Font("Arial", Font.PLAIN, 12));
        selectCompany.setSize(250, 20);
        selectCompany.setLocation(20, 50);
        add(selectCompany);
    }


    //MODIFIES: this
    //EFFECTS: updates the company that has been selected in the drop down menu and prompts the user to continue the
    // update in a popup window
    @Override
    public void actionPerformed(ActionEvent e) {
        for (Company next : companies) {
            if (next.getCompanyName().equals(String.valueOf(cb.getItemAt(cb.getSelectedIndex())))) {
                next.followedUp();
            }
        }
        listOfCompanies.updateListsBasedOnFollowedUpCompanies();
        int a = JOptionPane.showConfirmDialog(this, "Would you like to continue?");
        if (a == JOptionPane.YES_OPTION) {
            new UpdateFollowedUp(companies, listOfCompanies);
        }


    }
}
