
package com.mba.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author VISHAL
 */
public class FormServerCategorySalesPerDay extends JFrame implements ActionListener
{
    private JPanel panel;
    private JLabel label;

    public FormServerCategorySalesPerDay()
    {
//        PieChart p =new PieChart();
        panel = new JPanel();
        label = new JLabel();
        add(panel);
    }

    public void actionPerformed(ActionEvent e)
    {

    }
}
