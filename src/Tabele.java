import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tabele extends JFrame 
{
    Baza baza;
    FanFrame frame;
    
    public Tabele(Baza wejscie, FanFrame fanfrejm) 
    {
        super("Tabele");
        baza = wejscie;
        frame = fanfrejm;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocation(350, 150);
        setSize(700, 400);
        setLayout(null);

        JLabel liga1 = new JLabel("1 liga");
        JLabel liga2 = new JLabel("2 liga");
        JLabel liga3 = new JLabel("3 liga");
        JButton tabela1 = new JButton("Tabela");
        JButton tabela2 = new JButton("Tabela");
        JButton tabela3 = new JButton("Tabela");
        JButton strzelcy1 = new JButton("Strzelcy");
        JButton strzelcy2 = new JButton("Strzelcy");
        JButton strzelcy3 = new JButton("Strzelcy");
        JButton bramkarze1 = new JButton("Czyste konta");
        JButton bramkarze2 = new JButton("Czyste konta");
        JButton bramkarze3 = new JButton("Czyste konta");
        JButton mecze1 = new JButton("Mecze");
        JButton mecze2 = new JButton("Mecze");
        JButton mecze3 = new JButton("Mecze");
        JButton wstecz = new JButton("Wstecz");

        liga1.setBounds(25, 20, 200, 20);
        liga2.setBounds(250, 20, 200, 20);
        liga3.setBounds(475, 20, 200, 20);
        tabela1.setBounds(25, 50, 200, 50);
        tabela2.setBounds(250, 50, 200, 50);
        tabela3.setBounds(475, 50, 200, 50);
        strzelcy1.setBounds(25, 110, 200, 50);
        strzelcy2.setBounds(250, 110, 200, 50);
        strzelcy3.setBounds(475, 110, 200, 50);
        bramkarze1.setBounds(25, 170, 200, 50);
        bramkarze2.setBounds(250, 170, 200, 50);
        bramkarze3.setBounds(475, 170, 200, 50);
        mecze1.setBounds(25, 230, 200, 50);
        mecze2.setBounds(250, 230, 200, 50);
        mecze3.setBounds(475, 230, 200, 50);
        wstecz.setBounds(300, 320, 100, 40);

        add(liga1);
        add(liga2);
        add(liga3);
        add(tabela1);
        add(tabela2);
        add(tabela3);
        add(strzelcy1);
        add(strzelcy2);
        add(strzelcy3);
        add(bramkarze1);
        add(bramkarze2);
        add(bramkarze3);
        add(mecze1);
        add(mecze2);
        add(mecze3);
        add(wstecz);

        tabela1.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                tabela_panel(1, 1);
                setVisible(false);
            }
        });

        tabela2.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                tabela_panel(2, 1);
                setVisible(false);
            }
        });

        tabela3.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                tabela_panel(3, 1);
                setVisible(false);
            }
        });

        strzelcy1.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                tabela_panel(1, 2);
                setVisible(false);
            }
        });

        strzelcy2.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                tabela_panel(2, 2);
                setVisible(false);
            }
        });

        strzelcy3.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                tabela_panel(3, 2);
                setVisible(false);
            }
        });

        bramkarze1.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                tabela_panel(1, 3);
                setVisible(false);
            }
        });

        bramkarze2.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                tabela_panel(2, 3);
                setVisible(false);
            }
        });

        bramkarze3.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                tabela_panel(3, 3);
                setVisible(false);
            }
        });

        mecze1.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                tabela_panel(1, 9);
                setVisible(false);
            }
        });

        mecze2.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                tabela_panel(2, 9);
                setVisible(false);
            }
        });

        mecze3.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                tabela_panel(3, 9);
                setVisible(false);
            }
        });

        wstecz.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                frame.setVisible(true);
                dispose();
            }
        });
    }

    public void tabela_panel(int liga, int akcja)
    {
        new Tabela(baza, this, liga, akcja);
    }
}