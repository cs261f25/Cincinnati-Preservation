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
public class FileBrowser extends JPanel implements ActionListener {
    private JLabel l;
    private JButton b1;
    private String filedirectory = "-1";
    public FileBrowser()
    {
        initialize();
    }
    
    public void initialize(){
        b1 = new JButton("Browse Files");
        b1.addActionListener(this);
        l = new JLabel("no file selected");
        add(b1);
        add(l);
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
