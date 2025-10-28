package com.cincypreservation;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel implements ActionListener {
    private JButton b1,b2; 
    private ArrayList<JButton> buttons = new ArrayList<>();
    private final static Dimension BUTTON_DIMENSIONS = new Dimension(200,50);
    public ButtonPanel(){
        initialize();
    }
    private void initialize(){
        b1 = initializeButton(b1, "Button 1 - Launch Camera");
        b2 = initializeButton(b2, "Button 2 - File Reader");
        // Adds all the buttons 
        for (JButton button : buttons){
            add(button);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o == b1) {
            LaunchCamera launchcamera = new LaunchCamera();
            launchcamera.main(null);
        } else if (o == b2) {
            FileReader fileReader = new FileReader();
            fileReader.main(null);
        }
    }
    /**
     * This helper method intializates given JButton objects with a given label.
     * It also stores buttons it initializes into an unfixed size Arraylist, to be called later.
     */
    private JButton initializeButton(JButton b,String label){
        b = new JButton(label);
        b.setPreferredSize(BUTTON_DIMENSIONS);
        b.setActionCommand(label);
        b.addActionListener(this);
        buttons.add(b);
        return b;
    }
}
