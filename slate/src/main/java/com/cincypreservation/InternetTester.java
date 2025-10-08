package com.cincypreservation;
import java.util.*;

import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.*;
import java.awt.event.*;

public class InternetTester implements ActionListener {
    private static JLabel l;
    public InternetTester(){
        initialize();
    }
    public void initialize(){
        // Frame
        JFrame f = new JFrame("Internet Connection Tester");
        f.setSize(400,100);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Button
        JButton b1 = new JButton("Test Internet Connection"); 
        b1.addActionListener(this);
        b1.setActionCommand("Test");
        // Label
        l = new JLabel("Test Internet Connection");
        // Panel
        JPanel p = new JPanel();
        p.add(b1);
        p.add(l);
        // Finalization
        f.add(p);
        f.setVisible(true);
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
            System.out.println("Not Connected to the Internet - Error Code: " + e.getCause());
        }
        return false;
    }
    public static void main(String[] args){
        new InternetTester();
    }
}


