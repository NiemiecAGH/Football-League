import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminFrame extends JFrame
{
    Baza baza;
    MyFrame frame;

    public AdminFrame(Baza bd, MyFrame majfrejm)
    {
        super("Panel administratora");

        baza = bd;
        frame = majfrejm;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(400, 600);
        setLocation(450, 100);
        setLayout(null);

        JButton sedziowie = new JButton("Dodaj sędziego");
        JButton stadiony = new JButton("Dodaj stadion");
        JButton druzyny = new JButton("Dodaj drużynę");
        JButton sztabowcy = new JButton("Dodaj sztabowca");
        JButton funkcja_sztab = new JButton("Dodaj funkcję sztabowca");
        JButton pilkarze = new JButton("Dodaj piłkarza");
        JButton sponsorzy = new JButton("Dodaj sponsora");
        JButton branza_sponsor = new JButton("Dodaj branżę sponsora");
        JButton sponsoring = new JButton("Dodaj umowę sponsorską");
        JButton mecze = new JButton("Dodaj mecz");
        JButton wstecz = new JButton("Wstecz");

        mecze.setBounds(50, 5, 300, 40);
        druzyny.setBounds(50, 50, 300, 40);
        pilkarze.setBounds(50, 95, 300, 40);
        sedziowie.setBounds(50, 140, 300, 40);
        stadiony.setBounds(50, 185, 300, 40);
        sztabowcy.setBounds(50, 230, 300, 40);
        funkcja_sztab.setBounds(50, 275, 300, 40);
        sponsorzy.setBounds(50, 320, 300, 40);
        branza_sponsor.setBounds(50, 365, 300, 40);
        sponsoring.setBounds(50, 410, 300, 40);
        wstecz.setBounds(150, 520, 100, 40);

        add(sedziowie);
        add(stadiony);
        add(druzyny);
        add(sztabowcy);
        add(pilkarze);
        add(sponsorzy);
        add(sponsoring);
        add(mecze);
        add(funkcja_sztab);
        add(branza_sponsor);
        add(wstecz);

        wstecz.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                frame.setVisible(true);
                dispose();
            }
        });

        branza_sponsor.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                setVisible(false);
                branza_panel();
            }
        });

        funkcja_sztab.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                setVisible(false);
                funkcja_panel();
            }
        });

        stadiony.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                setVisible(false);
                stadion_panel();
            }
        });

        sponsorzy.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                setVisible(false);
                sponsor_panel();
            }
        });

        sponsoring.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                setVisible(false);
                sponsoring_panel();
            }
        });

        sztabowcy.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                setVisible(false);
                sztabowiec_panel();
            }
        });

        sedziowie.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                setVisible(false);
                sedzia_panel();
            }
        });

        druzyny.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                setVisible(false);
                druzyna_panel();
            }
        });

        pilkarze.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                setVisible(false);
                pilkarz_panel();
            }
        });

        mecze.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                setVisible(false);
                mecz_panel();
            }
        });

    }

    void branza_panel()
    {
        AddSponsorFunc panel = new AddSponsorFunc(baza, this);
    }

    void funkcja_panel()
    {
        AddSztabFunc panel = new AddSztabFunc(baza, this);
    }

    void stadion_panel()
    {
        AddStadion panel = new AddStadion(baza, this);
    }

    void sponsor_panel()
    {
        AddSponsor panel = new AddSponsor(baza, this);
    }

    void sponsoring_panel()
    {
        AddUmowa panel = new AddUmowa(baza, this);
    }

    void sztabowiec_panel()
    {
        AddSztab panel = new AddSztab(baza, this);
    }

    void sedzia_panel()
    {
        AddSedzia panel = new AddSedzia(baza, this);
    }

    void druzyna_panel()
    {
        AddDruzyna panel = new AddDruzyna(baza, this);
    }

    void pilkarz_panel()
    {
        AddPilkarz panel = new AddPilkarz(baza, this);
    }

    void mecz_panel()
    {
        MeczeMenu panel = new MeczeMenu(baza, this);
    }
}