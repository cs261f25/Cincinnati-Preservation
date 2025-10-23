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
    private JLabel l;
    private JFrame f;
    private JPanel p;
    private JButton b1;
    private String filedirectory = "-1";
    public FileBrowser()
    {
    }
    
    public void initialize(){
        f = new JFrame("File Chooser");
        b1 = new JButton("Browse Files");
        b1.addActionListener(this);
        p = new JPanel();
        l = new JLabel("no file selected");
        p.add(b1);
        p.add(l);

        f.add(p);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        displayGUI();
    }

    private void displayGUI(){
        f.pack();
        f.setSize(400,400);
        f.setVisible(true);
    }

    private String getFileDirectory(){
        return filedirectory;
    }

    @Override
    public void actionPerformed(ActionEvent evt)
    {
        // Captures actions in the GUI.
        Object o = evt.getSource();
        FileFilter filter = new FileNameExtensionFilter("Excel File",  new String[]{"xls","xlsx"});
        if (o == b1){ 
            JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            j.addChoosableFileFilter(filter);
            int r = j.showOpenDialog(null);
            if (r == JFileChooser.APPROVE_OPTION){
                filedirectory = j.getSelectedFile().getAbsolutePath();
                l.setText("File Selected = " + filedirectory);
            }
            else {
                l.setText("The user cancelled the operation.");
            }
        }   
    }
}
