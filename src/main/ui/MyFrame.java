package ui;

import model.Company;
import model.CompanyList;
import model.CompanySizeRange;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static java.lang.Integer.parseInt;


//https://www.geeksforgeeks.org/java-swing-simple-user-registration-form/
class MyFrame extends JFrame
        implements ActionListener {

    // Components of the Form
    private Container c;
    private JLabel title;
    private JLabel name;
    private JTextField tname;
    private JLabel size;
    private JTextField tsize;
    private JLabel employerName;
    private JTextField temployerName;
    private JLabel industry;
    private JRadioButton infoTech;
    private JRadioButton marketing;
    private JRadioButton engineering;
    private JRadioButton business;
    private ButtonGroup industryP;
    private JLabel dob;
    private JComboBox date;
    private JComboBox month;
    private JComboBox year;

    private JButton sub;
    private JButton reset;
    private JTextArea tout;
    private JLabel res;
    private JTextArea resadd;

    private CompanyList listOfCompanies;
    private CompanySizeRange range;


    // constructor, to initialize the components
    // with default values.
    public MyFrame(CompanySizeRange companySizeRange, CompanyList companyList) {
        this.range = companySizeRange;
        this.listOfCompanies = companyList;

        setTitle("Fill out company information");
        setBounds(300, 90, 900, 600);

        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        title = new JLabel("Company information form");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(600, 30);
        title.setLocation(300, 30);
        c.add(title);

        companyName();
        companySize();
        employerName();
        industrySelect();
        submitAndRest();
        emptyTextArea();

        responseText();

        setVisible(true);
    }

    private void responseText() {
        res = new JLabel("");
        res.setFont(new Font("Arial", Font.PLAIN, 20));
        res.setSize(500, 25);
        res.setLocation(100, 500);
        c.add(res);

        resadd = new JTextArea();
        resadd.setFont(new Font("Arial", Font.PLAIN, 15));
        resadd.setSize(200, 75);
        resadd.setLocation(580, 175);
        resadd.setLineWrap(true);
        c.add(resadd);
    }

    private void emptyTextArea() {
        tout = new JTextArea();
        tout.setFont(new Font("Arial", Font.PLAIN, 15));
        tout.setSize(300, 400);
        tout.setLocation(500, 100);
        tout.setLineWrap(true);
        tout.setEditable(false);
        c.add(tout);
    }

    private void submitAndRest() {
        sub = new JButton("Submit");
        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setSize(100, 20);
        sub.setLocation(150, 450);
        sub.addActionListener(this);
        c.add(sub);

        reset = new JButton("Reset");
        reset.setFont(new Font("Arial", Font.PLAIN, 15));
        reset.setSize(100, 20);
        reset.setLocation(270, 450);
        reset.addActionListener(this);
        c.add(reset);
    }

    private void industrySelect() {
        industry = new JLabel("Select industry");
        industry.setFont(new Font("Arial", Font.PLAIN, 15));
        industry.setSize(500, 20);
        industry.setLocation(80, 200);
        c.add(industry);

        selectIT();

        selectMarketing();

        selectEng();

        selectBusiness();

        industryP = new ButtonGroup();
        industryP.add(infoTech);
        industryP.add(marketing);
        industryP.add(engineering);
        industryP.add(business);
    }

    private void selectBusiness() {
        business = new JRadioButton("Business Admin");
        business.setFont(new Font("Arial", Font.PLAIN, 15));
        business.setSelected(false);
        business.setSize(400, 20);
        business.setLocation(200, 290);
        c.add(business);
    }

    private void selectEng() {
        engineering = new JRadioButton("Eng");
        engineering.setFont(new Font("Arial", Font.PLAIN, 15));
        engineering.setSelected(false);
        engineering.setSize(300, 20);
        engineering.setLocation(200, 260);
        c.add(engineering);
    }

    private void selectMarketing() {
        marketing = new JRadioButton("Marketing");
        marketing.setFont(new Font("Arial", Font.PLAIN, 15));
        marketing.setSelected(false);
        marketing.setSize(300, 20);
        marketing.setLocation(200, 230);
        c.add(marketing);
    }

    private void selectIT() {
        infoTech = new JRadioButton("IT");
        infoTech.setFont(new Font("Arial", Font.PLAIN, 15));
        infoTech.setSelected(true);
        infoTech.setSize(50, 20);
        infoTech.setLocation(200, 200);
        c.add(infoTech);
    }

    private void employerName() {
        employerName = new JLabel("Employer Name");
        employerName.setFont(new Font("Arial", Font.PLAIN, 15));
        employerName.setSize(300, 20);
        employerName.setLocation(80, 160);
        c.add(employerName);

        temployerName = new JTextField();
        temployerName.setFont(new Font("Arial", Font.PLAIN, 15));
        temployerName.setSize(150, 20);
        temployerName.setLocation(200, 160);
        c.add(temployerName);
    }

    private void companySize() {
        size = new JLabel("Company Size");
        size.setFont(new Font("Arial", Font.PLAIN, 15));
        size.setSize(300, 20);
        size.setLocation(80, 125);
        c.add(size);

        tsize = new JTextField();
        tsize.setFont(new Font("Arial", Font.PLAIN, 15));
        tsize.setSize(150, 20);
        tsize.setLocation(200, 125);
        c.add(tsize);
    }

    private void companyName() {
        name = new JLabel("Company Name");
        name.setFont(new Font("Arial", Font.PLAIN, 15));
        name.setSize(300, 20);
        name.setLocation(80, 100);
        c.add(name);


        tname = new JTextField();
        tname.setFont(new Font("Arial", Font.PLAIN, 15));
        tname.setSize(190, 20);
        tname.setLocation(200, 100);
        c.add(tname);
    }

    // method actionPerformed()
    // to get the action performed
    // by the user and act accordingly
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sub) {
            new ImagePopUp();
            String industryInput = displayCompanyInfo();

            Company company = new Company(parseInt(tsize.getText()), industryInput, tname.getText(),
                    temployerName.getText());
            listOfCompanies.addNewCompany(company, range);

            tout.setEditable(false);
            res.setText("Company added successfully..");

            popUpToContinue();
        } else if (e.getSource() == reset) {
            resetTheForm();
        }
    }



    private String displayCompanyInfo() {
        String data = "Name : "
                + tname.getText() + "\n";
        String data1 = null;
        String industryInput = null;
        if (infoTech.isSelected()) {
            data1 = "Industry : IT" + "\n";
            industryInput = "Information Technology";
        } else if (marketing.isSelected()) {
            data1 = "Industry : Marketing" + "\n";
            industryInput = "Marketing";
        } else if (engineering.isSelected()) {
            data1 = "Industry : Engineering" + "\n";
            industryInput = "Engineering";
        } else if (business.isSelected()) {
            data1 = "Industry : Business Admin";
            industryInput = "Business Admin/ Project MGMT";
        }
        String data4 = "Company Size :" + tsize.getText() + "\n";
        String data5 = "Employer name :" + temployerName.getText() + "\n";
        tout.setText(data + data4 + data5 + data1);
        return industryInput;
    }

    private void popUpToContinue() {
        int a = JOptionPane.showConfirmDialog(this, "Would you like to continue?");
        if (a == JOptionPane.NO_OPTION) {

            PreferenceWindow selectOption = new PreferenceWindow(listOfCompanies, range);

        } else {
            MyFrame f = new MyFrame(range, listOfCompanies);
        }
    }

    private void resetTheForm() {
        String def = "";
        tname.setText(def);
        temployerName.setText(def);
        tsize.setText(def);
        res.setText(def);
        tout.setText(def);
        resadd.setText(def);
    }

}
