package com.cincypreservation;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    final static String TITLE = "Inspection Tracker App";
    final static Dimension FRAME_DIMENSION = new Dimension(600,600);
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
                public void run(){
                    UIManager.put("swing.boldMetal",Boolean.FALSE);
                    createAndShowGUI();
                }
            });
    }

    public static void createAndShowGUI(){
        PropertySelector ps = new PropertySelector();
    }
}