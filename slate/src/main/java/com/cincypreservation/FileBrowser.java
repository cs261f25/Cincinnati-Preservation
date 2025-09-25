package com.cincypreservation;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.filechooser.*;
class filebrowser extends JFrame implements ActionListener {
    static JLabel l;
    filebrowser()
    {
    }
    public static void main(String args[])
    {
        JFrame f = new JFrame("File chooser to select directories");
        f.setSize(400,400);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton button1 = new JButton("Save");
        JButton button2 = new JButton("Open");
        filebrowser f1 = new filebrowser();
        button1.addActionListener(f1);
        button2.addActionListener(f1);
        JPanel p = new JPanel();
        p.add(button1);
        p.add(button2);
        l = new JLabel("no file selected");
        p.add(l);
        f.add(p);
        f.setVisible(true);
    }
    public void actionPerformed(ActionEvent evt)
    {
        String com = evt.getActionCommand();
        if (com.equals("save")){
            JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int r = j.showSaveDialog(null);
            if (r == JFileChooser.APPROVE_OPTION){
                l.setText(j.getSelectedFile().getAbsolutePath());
            }
            else{
                l.setText("The user cancelled the operation");
            }

        }
        else {
            JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int r = j.showOpenDialog(null);
            if r == JFileChooser.APPROVE_OPTION){
                l.setText(j.getSelectedFile().getAbsolutePath());
            }
            else {
                l.setText("The user cancelled the operation");
            }
        }
    }
}
