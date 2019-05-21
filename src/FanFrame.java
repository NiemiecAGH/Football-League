import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FanFrame extends JFrame 
{
    Baza baza;
    MyFrame frame;
    
    public FanFrame(Baza wejscie, MyFrame majfrejm) 
    {
        super("Panel kibica");
        baza = wejscie;
        frame = majfrejm;
        majfrejm.setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocation(350, 150);
        setSize(400, 500);
        setLayout(null);

        JButton tabele = new JButton("Tabele");
        JButton pilkarze = new JButton("Piłkarze");
        JButton sedziowie = new JButton("Sędziowie");
        JButton stadiony = new JButton("Stadiony");
        JButton umowy = new JButton("Umowy sponsorskie");
        JButton sztab = new JButton("Sztabowcy");
        JButton wstecz = new JButton("Wstecz");

        tabele.setBounds(50, 10, 300, 50);
        pilkarze.setBounds(50, 65, 300, 50);
        sedziowie.setBounds(50, 120, 300, 50);
        stadiony.setBounds(50, 175, 300, 50);
        sztab.setBounds(50, 230, 300, 50);
        umowy.setBounds(50, 285, 300, 50);
        wstecz.setBounds(150, 420, 100, 40);

        add(tabele);
        add(sedziowie);
        add(stadiony);
        add(wstecz);
        add(pilkarze);
        add(sztab);
        add(umowy);

        wstecz.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                dispose();
                frame.setVisible(true);
            }
        });

        tabele.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                setVisible(false);
                tabele_panel();
            }
        });

        sedziowie.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                setVisible(false);
                tabela_panel(5);
            }
        });

        stadiony.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                setVisible(false);
                tabela_panel(6);
            }
        });

        pilkarze.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                setVisible(false);
                tabela_panel(4);
            }
        });

        sztab.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                setVisible(false);
                tabela_panel(7);
            }
        });

        umowy.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                setVisible(false);
                tabela_panel(8);
            }
        });
    }
    
    void tabele_panel()
    {
        Tabele panel = new Tabele(baza, this);
    }

    void tabela_panel(int akcja)
    {
        Tabela panel = new Tabela(baza, this, 1, akcja);
    }
}