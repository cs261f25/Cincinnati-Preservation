package com.cincypreservation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class TabbedMenu extends JPanel {
    public TabbedMenu()
    {    
        setLayout(new GridLayout(1,1));
        JTabbedPane tabbedPane = new JTabbedPane();

        JComponent panel1 = new FileReader();
        tabbedPane.addTab("Tab 1 - File Reader", panel1);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        JComponent panel2 = new InternetTester();
        tabbedPane.addTab("Tab 2 - Internet Tester", panel2);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        JComponent panel3 = new PropertySelector();
        tabbedPane.addTab("Tab 3 - Property Selector", panel3);
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        JComponent panel4 = new ButtonPanel();
        tabbedPane.addTab("Tab 4 - Buttons", panel4);
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);

        add(tabbedPane);
    } 
}
