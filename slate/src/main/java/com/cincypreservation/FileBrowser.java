package com.cincypreservation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
class FileBrowser extends JFrame implements ActionListener {
    static JLabel l;
    FileBrowser()
    {
    }
    public static void main(String args[])
    {
        JFrame f = new JFrame("File Chooser");
        f.setSize(400,400);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton button1 = new JButton("Browse Files");
        FileBrowser f1 = new FileBrowser();
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
        FileFilter filter = new FileNameExtensionFilter("Excel File",  new String[]{"xls","xlsx"});
        if (com.equals("Browse Files")){ 
            JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            j.addChoosableFileFilter(filter);
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
