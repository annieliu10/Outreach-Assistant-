package ui;

import model.Company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DropDownMenuForUpdate extends JFrame implements ActionListener {


    private List<Company> companies;

    private JComboBox cb;

    private JTextArea emptyTextArea;

    private JLabel interestLevel;

    private JLabel selectCompany;
    private JTextField tinterestLevel;

    DropDownMenuForUpdate(List<Company> companies) {
        this.companies = companies;


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




        interestLevel = new JLabel("Company's interest level");
        interestLevel.setFont(new Font("Arial", Font.PLAIN, 12));
        interestLevel.setSize(250, 20);
        interestLevel.setLocation(20, 80);
        add(interestLevel);


        tinterestLevel = new JTextField();
        tinterestLevel.setFont(new Font("Arial", Font.PLAIN, 15));
        tinterestLevel.setSize(200, 20);
        tinterestLevel.setLocation(200, 80);
        add(tinterestLevel);


        add(cb);
        add(label);
        add(b);

        // settings for the frame
        setLayout(null);
        setBounds(300, 90, 500, 160);
        setVisible(true);
        setTitle("Update companies");
        setResizable(false);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        for (Company next : companies) {
            if (String.valueOf(cb.getItemAt(cb.getSelectedIndex())).equals(next.getCompanyName())) {
                next.contacted(Integer.parseInt(tinterestLevel.getText()));
            }
        }

        int a = JOptionPane.showConfirmDialog(this, "Would you like to continue?");
        if (a == JOptionPane.YES_OPTION) {

            DropDownMenuForUpdate selectOption = new DropDownMenuForUpdate(companies);

        }
    }
}
