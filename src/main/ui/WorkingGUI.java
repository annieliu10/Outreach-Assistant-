package ui;

import model.CompanyList;
import model.CompanySizeRange;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import static jdk.nashorn.internal.runtime.GlobalFunctions.parseInt;


//https://www.javatpoint.com/java-swing
public class WorkingGUI extends JFrame implements ActionListener {
    JMenu menu;
    JMenu midContact;
    JMenu postContact;
    JMenuItem preContact, i2, i4, i5, i6, i7, i8, i9, i10, updateContact, book, check, updateFollowUp,
            prioritizeFollowUp;

    JRadioButtonMenuItem rbMenuItem;

    private JLabel lowerBoundLabel;
    private JLabel upperBoundLabel;
    private JLabel companyNameLabel;
    private JTextField preferenceLowerBound;
    private JTextField preferenceUpperBound;
    private JTextField companyName;

    private CompanyList companyList;

    WorkingGUI() {
        companyList = new CompanyList();

        setTitle("JMenuBar_test");
        setLayout(new FlowLayout());


        setItems();

        setAction();


        setFieldAndLabels();

        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setFieldAndLabels() {
        /*lowerBoundLabel = new JLabel("Enter lower bound ");
        lowerBoundLabel.setBounds(50, 50, 100, 30);
        upperBoundLabel = new JLabel("Enter upper bound");
        upperBoundLabel.setBounds(50, 100, 100, 30);
        companyNameLabel = new JLabel("Enter company name");
        companyNameLabel.setBounds(50, 150, 100, 30);

        add(lowerBoundLabel);
        add(upperBoundLabel);
        add(companyNameLabel);


        preferenceLowerBound = new JTextField(10);
        add(preferenceLowerBound);
        preferenceUpperBound = new JTextField(10);
        add(preferenceUpperBound);
        companyName = new JTextField(10);
        add(companyName);*/

    }

    private void setAction() {
        preContact.addActionListener(this);
        updateContact.addActionListener(this);


        book.addActionListener(this);
        check.addActionListener(this);
        prioritizeFollowUp.addActionListener(this);
        updateFollowUp.addActionListener(this);
        i4.addActionListener(this);
        i5.addActionListener(this);
        i6.addActionListener(this);
        i7.addActionListener(this);
        i8.addActionListener(this);
        i9.addActionListener(this);
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


        i4 = new JMenuItem("View the list of companies that have not been contacted");
        i5 = new JMenuItem("View the contacted list of companies");
        i6 = new JMenuItem("View the followed-up companies");
        i7 = new JMenuItem("View booked meetings");
        i8 = new JMenuItem("Load data");
        i9 = new JMenuItem("Save data");
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
        menu.add(i5);
        menu.add(i6);
        menu.add(i7);
        menu.add(i8);
        menu.add(i9);
        menu.add(i10);


        //a group of radio button menu items
        menu.addSeparator();
        ButtonGroup group = new ButtonGroup();
        rbMenuItem = new JRadioButtonMenuItem("A radio button menu item");
        rbMenuItem.setSelected(true);
        rbMenuItem.setMnemonic(KeyEvent.VK_R);
        group.add(rbMenuItem);
        menu.add(rbMenuItem);

        rbMenuItem = new JRadioButtonMenuItem("Another one");
        rbMenuItem.setMnemonic(KeyEvent.VK_O);
        group.add(rbMenuItem);
        menu.add(rbMenuItem);


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




        } else if (e.getSource() == updateContact) {
            companyName.setText("Update Contact");
        } else if (e.getSource() == book) {
            companyName.setText("booking");
        } else if (e.getSource() == check) {
            companyName.setText("check");
        } else if (e.getSource() == prioritizeFollowUp) {
            companyName.setText("follow up");
        }
    }


}




