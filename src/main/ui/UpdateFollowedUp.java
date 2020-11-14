package ui;

import model.Company;
import model.CompanyList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UpdateFollowedUp extends JFrame implements ActionListener {

    private List<Company> companies;
    private CompanyList listOfCompanies;
    private JComboBox cb;

    private JTextArea emptyTextArea;


    private JLabel selectCompany;

    UpdateFollowedUp(List<Company> companies, CompanyList companyList) {
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

        selectCompany = new JLabel("Select the company");
        selectCompany.setFont(new Font("Arial", Font.PLAIN, 12));
        selectCompany.setSize(250, 20);
        selectCompany.setLocation(20, 50);
        add(selectCompany);


        add(cb);
        add(label);
        add(b);

        // settings for the frame
        setLayout(null);
        setBounds(300, 90, 500, 160);
        setVisible(true);
        setTitle("Update followed up companies");
        setResizable(false);

    }


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
