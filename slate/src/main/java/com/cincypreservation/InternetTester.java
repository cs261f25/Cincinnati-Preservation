package com.cincypreservation;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.*;
import java.awt.event.*;

public class InternetTester extends JPanel implements ActionListener {
    private static JLabel l;
    public InternetTester(){
        initialize();
    }
    public void initialize(){
        // Button
        JButton b1 = new JButton("Test Internet Connection"); 
        b1.addActionListener(this);
        b1.setActionCommand("Test");
        // Label
        l = new JLabel("Test Internet Connection");
        // Panel
        add(b1);
        add(l);
    }
    public void actionPerformed(ActionEvent evt){
        String com = evt.getActionCommand();
        if(com.equals("Test")){
            if(!TestConnection()){
                l.setText("Internet Not Connected");
            } else {
                l.setText("Internet Connected");
            }
        }
    }
    private static boolean TestConnection() {
        try {
            URL url = new URL("https://www.google.com/");
            URLConnection connection = url.openConnection();
            connection.connect();

            System.out.println("Connection Successful");
            return true;  
        }
        catch (Exception e) {
            System.out.println("Not Connected to the Internet");
        }
        return false;
    }
    public static void main(String[] args){
        new InternetTester();
    }
}


