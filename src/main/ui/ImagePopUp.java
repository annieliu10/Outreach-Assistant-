package ui;

import javax.swing.*;
import java.awt.*;


//A panel that displays an image of a smiley face
public class ImagePopUp extends JFrame {


    //EFFECTS: displays an image of a smiley face in a panel
    ImagePopUp() {
        setBounds(50, 100, 500, 500);
        JLabel background = new JLabel(new ImageIcon("./smiley.jpg"));
        add(background);
        background.setLayout(new FlowLayout());

        setVisible(true);
    }


}
