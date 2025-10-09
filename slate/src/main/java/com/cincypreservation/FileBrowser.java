package com.cincypreservation;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.filechooser.*;
import javax.swing.filechooser.FileFilter;
class filebrowser extends JFrame implements ActionListener {
    static JLabel l;
    filebrowser()
    {
    }
    public static void main(String args[])
    {
        JFrame f = new JFrame("File Chooser - Open Excel File");
        f.setSize(400,400);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton button1 = new JButton("Open File");
        filebrowser f1 = new filebrowser();
        button1.addActionListener(f1);
        JPanel p = new JPanel();
        p.add(button1);
        l = new JLabel("No file selected.");
        p.add(l);
        f.add(p);
        f.setVisible(true);
    }
    public void actionPerformed(ActionEvent evt)
    {
        String com = evt.getActionCommand();
        FileFilter filter = new FileNameExtensionFilter("Excel File",  new String[]{"xlsx","xls"});
        if (com.equals("Open File")){ 
            JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            j.setFileFilter(filter);
            j.addChoosableFileFilter(filter);
            int r = j.showOpenDialog(null);
            if (r == JFileChooser.APPROVE_OPTION){
                l.setText(j.getSelectedFile().getAbsolutePath());
            }
            else {
                l.setText("The user cancelled the operation");
            }
        }   
    }
}
