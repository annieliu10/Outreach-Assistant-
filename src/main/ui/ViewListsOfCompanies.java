package ui;

import model.Company;
import model.CompanyList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


//A panel that displays companies with different statuses (uncontacted, contacted and followed-up)
public class ViewListsOfCompanies extends JFrame implements ActionListener {


    private CompanyList companyList;

    private JComboBox cb;

    private JTextArea emptyTextArea;


    //REQUIRES: companyList has to be valid
    //EFFECTS: displays to the user the a list of companies with the selected state (uncontacted, contacted,
    // followed-up)
    public ViewListsOfCompanies(CompanyList companyList) {
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


    //MODIFIES: this
    //EFFECTS: creates an empty text area that shows the list of companies
    private void emptyTextArea() {
        emptyTextArea = new JTextArea();
        emptyTextArea.setFont(new Font("Arial", Font.PLAIN, 15));
        emptyTextArea.setSize(300, 250);
        emptyTextArea.setLocation(50, 100);
        emptyTextArea.setLineWrap(true);
        add(emptyTextArea);
    }



    //MODIFIES: this
    //EFFECTS: displays the selected group of companies based on user's selection in the drop down menu
    // if the user selects "un-contacted companies", then a list of companies that haven't been contacted is displayed
    // if the user selects "contacted companies", then a list of companies that have been contacted but not followed up
    // is displayed
    // if the user selects "followed-up companies", then a list of companies that have been followed up is displayed
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
