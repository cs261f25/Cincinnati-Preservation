package com.cincypreservation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class TabbedMenu extends JPanel {
    public TabbedMenu()
    {    
        setLayout(new GridLayout(1,1));
        JTabbedPane tabbedPane = new JTabbedPane();

        FormPanel formPanel = new FormPanel();
        JComponent panel1 = formPanel;
        tabbedPane.addTab("File Writer", panel1);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        JComponent panel2 = formPanel.new FeaturePanel();
        tabbedPane.addTab("Feature Adder", panel2);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        JComponent panel3 = new PropertySelector();
        tabbedPane.addTab("Property Selector", panel3);
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        JComponent panel4 = new InternetTester();
        tabbedPane.addTab("Internet Tester", panel4);
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);

        JComponent panel5 = new ButtonPanel();
        tabbedPane.addTab("Buttons", panel5);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_5);

        add(tabbedPane);
    } 
}
