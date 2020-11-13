package ui;

import model.Company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DisplayPrioritizedCompanies extends JFrame {


    private List<Company> companies;

    private Container container;

    private JLabel res;

    private JTextArea emptyTextArea;



    public DisplayPrioritizedCompanies(List<Company> companies) {

        this.companies = companies;


        /*JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setSize(400, 100);
        JButton b = new JButton("Show");
        b.setBounds(400, 50, 75, 20);
        String companyLabels[] = {"Un-contacted companies", "Contacted companies", "Followed-up companies"};

        JComboBox cb = new JComboBox(companyLabels);
        cb.setBounds(50, 50, 200, 20);
        add(cb);
        add(label);
        add(b);
        setLayout(null);
        setBounds(300, 90, 900, 900);


        setTitle("List of companies");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

*/
        emptyTextArea = new JTextArea();
        emptyTextArea.setFont(new Font("Arial", Font.PLAIN, 15));
        emptyTextArea.setSize(300, 300);
        emptyTextArea.setLocation(50, 100);
        emptyTextArea.setLineWrap(true);
        add(emptyTextArea);






        setLayout(null);
        setBounds(300, 90, 500, 500);
        setVisible(true);
        setTitle("List of companies you should contact based on this order");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);


        ArrayList<String> textToBeDisplayed = new ArrayList<>();

        int i = 1;
        for (Company next : companies) {
            textToBeDisplayed.add(i + " . " + next.getCompanyName());
            i++;
        }



        textToBeDisplayed.toString();
        emptyTextArea.setText(String.valueOf(textToBeDisplayed));


    }


}
