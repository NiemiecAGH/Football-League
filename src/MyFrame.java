import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame 
{
    Baza baza;

    public MyFrame(Baza wejscie) 
    {
        super("Rozgrywki piłkarskie");
        baza = wejscie;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocation(450, 150);
        setSize(250, 200);
        setLayout(null);

        JButton admin = new JButton("Panel administratora");
        JButton kibic = new JButton("Panel kibica");
        
        admin.setBounds(25, 30, 200, 50);
        kibic.setBounds(25, 100, 200, 50);
        
        add(admin);
        add(kibic);

        admin.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                setVisible(false);
                admin_panel();
            }
        });

        kibic.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                setVisible(false);
                kibic_panel();
            }
        });
    }
    
    void admin_panel()
    {
        AdminLogin panel = new AdminLogin(baza, this);
    }

    void kibic_panel()
    {
        FanFrame panel = new FanFrame(baza, this);
    }
}