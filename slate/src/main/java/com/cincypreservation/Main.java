package com.cincypreservation;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Inspection Tracker App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,400);
        JToolBar tb = new JToolBar("Functions");
        
        JButton cameraButton = new JButton("Open Camera");
        JButton formButton = new JButton("Create new Form");
        JButton internetButton = new JButton("Test Internet");
        JButton fileButton = new JButton("Open File");
        JButton fileReaderButton = new JButton("Read Excel File");

        tb.add(fileReaderButton);
        tb.add(cameraButton);
        tb.add(formButton);
        tb.add(internetButton);
        tb.add(fileButton);
        
        frame.add(tb, BorderLayout.NORTH);
        frame.pack();
        frame.setVisible(true);

        cameraButton.addActionListener(e -> {
            LaunchCamera lc = new LaunchCamera();
            lc.main(new String[1]);
        });

        formButton.addActionListener(e -> {
            Form f = new Form();
            f.main(new String[1]);
        });

        internetButton.addActionListener(e -> {
            InternetTester it = new InternetTester();
            it.main(new String[1]);
        });

        fileButton.addActionListener(e -> {
            FileBrowser fb = new FileBrowser();
            fb.initialize();
        });

        fileReaderButton.addActionListener(e -> {
            FileReader fr = new FileReader();
            fr.main(new String[1]);
        });
    }
}