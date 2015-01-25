

package com.mba.login;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author VISHAL
 */
public class FormServerLogin extends JFrame implements ActionListener
{
    String path = "E:/PROJECT/CODE/MBAServerend/assets/login.png";
    private JPanel panel = new JPanel();
    private JLabel label = new JLabel(new ImageIcon(path));
    private JTextField username = new JTextField(10);
    private JTextField password = new JTextField(10);
    private JLabel userlabel = new JLabel("Username : ");
    private JLabel passlabel = new JLabel("Password : ");
    private JButton submit = new JButton("Sign In");

    public FormServerLogin()
    {
        label.setHorizontalAlignment(JLabel.CENTER);
        setSize(315,730);
        panel.setLayout(null);
        setLocation(500,10);
        setTitle("MBA LOGIN:");
        setVisible(true);
        add(panel);
//        panel.setComponentZOrder(label, 5);
//        panel.setComponentZOrder(userlabel, 1);
//        panel.setComponentZOrder(passlabel, 1);
//        panel.setComponentZOrder(username, 1);
//        panel.setComponentZOrder(password, 1);
        
//        addComponent(panel,label,0,0,300,694);
        addComponent(panel, userlabel, 50, 100, 100, 25);
        addComponent(panel, username, 150, 100, 100, 25);
        addComponent(panel, passlabel, 50, 150, 100, 25);
        addComponent(panel, password, 150, 150, 100, 25);
        addComponent(panel, submit, 100, 550, 100, 25);


        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent evt)
            {
                System.exit(0);
            }
        });
    }

    public void actionPerformed(ActionEvent e)
    {

    }
    
    

    public static void main(String args[])
    {
        FormServerLogin f = new FormServerLogin();
    }

    public void addComponent(Container container,Component c,int x,int y,int width,int height)
    {
        c.setBounds(x,y,width,height);
        container.add(c);
    }
}
