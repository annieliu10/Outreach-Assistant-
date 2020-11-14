package ui;

import javax.swing.*;
import java.awt.*;

public class ImagePopUp extends JFrame {


    ImagePopUp() {
        setBounds(50, 100, 500, 500);
        JLabel background = new JLabel(new ImageIcon("./smiley.jpg"));
        add(background);
        background.setLayout(new FlowLayout());

        setVisible(true);
    }


}
