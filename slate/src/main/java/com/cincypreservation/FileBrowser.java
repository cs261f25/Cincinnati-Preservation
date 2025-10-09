package com.cincypreservation;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.filechooser.*;
import javax.swing.filechooser.FileFilter;
class filebrowser extends JFrame implements ActionListener {
    static JLabel l;
    filebrowser()
    {
    }
    public static void main(String args[])
    {
        JFrame f = new JFrame("File Chooser");
        f.setSize(400,400);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton button1 = new JButton("Browse Files");
        filebrowser f1 = new filebrowser();
        button1.addActionListener(f1);
        JPanel p = new JPanel();
        p.add(button1);
        l = new JLabel("no file selected");
        p.add(l);
        f.add(p);
        f.setVisible(true);
    }
    public void actionPerformed(ActionEvent evt)
    {
        // Captures actions in the GUI.
        String com = evt.getActionCommand();
        if (com.equals("Browse Files")){ 
            JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            int r = j.showOpenDialog(null);
            if (r == JFileChooser.APPROVE_OPTION){
                l.setText(j.getSelectedFile().getAbsolutePath());
            }
            else {
                l.setText("The user cancelled the operation.");
            }
        }   
    }
}
