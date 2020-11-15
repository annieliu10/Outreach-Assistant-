package ui;

import model.Company;
import model.CompanyList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ViewListsOfCompanies extends JFrame implements ActionListener {


    private CompanyList companyList;

    private JComboBox cb;

    private JTextArea emptyTextArea;

    ViewListsOfCompanies(CompanyList companyList) {
        this.companyList = companyList;

        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);


        label.setSize(400, 100);

        JButton b = new JButton("Show");
        b.setBounds(300, 50, 75, 20);
        b.addActionListener(this);

        String[] companyLabels;
        companyLabels = new String[]{"Un-contacted companies", "Contacted companies", "Followed-up companies"};


        cb = new JComboBox(companyLabels);
        cb.setBounds(50, 50, 200, 20);

        emptyTextArea();


        add(cb);
        add(label);
        add(b);

        // settings for the frame
        setLayout(null);
        setBounds(300, 90, 500, 400);
        setVisible(true);
        setTitle("List of companies");
        setResizable(false);
    }

    private void emptyTextArea() {
        emptyTextArea = new JTextArea();
        emptyTextArea.setFont(new Font("Arial", Font.PLAIN, 15));
        emptyTextArea.setSize(300, 250);
        emptyTextArea.setLocation(50, 100);
        emptyTextArea.setLineWrap(true);
        add(emptyTextArea);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Hi");
        String theList = String.valueOf(cb.getItemAt(cb.getSelectedIndex()));
        System.out.println(theList);

        ArrayList<String> companyNames = new ArrayList<>();
        if (theList.equals("Un-contacted companies")) {
            List<Company> unContacted = companyList.getUnContactedCompanies();
            for (Company next : unContacted) {
                companyNames.add(next.getCompanyName());
            }
        } else if (theList.equals("Contacted companies")) {
            List<Company> contacted = companyList.getContactedCompanies();
            for (Company next : contacted) {
                companyNames.add(next.getCompanyName());
            }
        } else {
            List<Company> followedUp = companyList.getFollowedUpCompanies();
            for (Company next : followedUp) {
                companyNames.add(next.getCompanyName());
            }
        }


        emptyTextArea.setText(String.valueOf(companyNames));

    }
}
