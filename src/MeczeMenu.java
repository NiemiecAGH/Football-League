import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MeczeMenu extends JFrame 
{
    Baza baza;
    AdminFrame frame;

    public MeczeMenu(Baza wejscie, AdminFrame adfrejm) 
    {
        super("Wybierz ligę");
        baza = wejscie;
        frame = adfrejm;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocation(450, 150);
        setSize(250, 220);
        setLayout(null);

        JButton liga1 = new JButton("1 liga");
        JButton liga2 = new JButton("2 liga");
        JButton liga3 = new JButton("3 liga");
        
        liga1.setBounds(25, 10, 200, 50);
        liga2.setBounds(25, 70, 200, 50);
        liga3.setBounds(25, 130, 200, 50);
        
        add(liga1);
        add(liga2);
        add(liga3);

        liga1.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                dispose();
                mecz_panel(1);
            }
        });

        liga2.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                dispose();
                mecz_panel(2);
            }
        });

        liga3.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                dispose();
                mecz_panel(3);
            }
        });
    }
    
    void mecz_panel(int liga)
    {
        AddMecz panel = new AddMecz(baza, frame, liga);
    }
}