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
        JFrame f = new JFrame("File Chooser - Display Photos");
        f.setSize(400,400);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton button1 = new JButton("Display");
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
        FileFilter filter = new FileNameExtensionFilter("Picture File",  new String[]{"jpg","jpeg","png"});
        if (com.equals("Display")){ 
            if(!testImages(l)){
                l.setText("Image failed to load - file path might be invalid");
            }
        } 
        else {
            // Open Button
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
    private boolean testImages(JLabel l){
        final String IMG_PATH = l.getText();
        if (IMG_PATH.contains(".jpg")){
            try{
                BufferedImage img = ImageIO.read(new File(IMG_PATH));
                ImageIcon icon = new ImageIcon(img);
                JLabel photolabel = new JLabel(icon);
                JOptionPane.showMessageDialog(null, photolabel);
                return true;
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return false;
    }
}
