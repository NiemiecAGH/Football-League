import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStadion extends JFrame 
{
    Baza baza;
    AdminFrame frame;
    JTextField nazwa;
    JTextField miasto;
    JTextField adres;
    JTextField pojemnosc;

    public AddStadion(Baza wejscie, AdminFrame majfrejm) 
    {
        super("Dodawanie stadionu");
        baza = wejscie;
        frame = majfrejm;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(500, 300);
        setLocation(300, 300);
        setLayout(null);

        JButton dodaj = new JButton("Dodaj");
        JButton wstecz = new JButton("Wstecz");
        JLabel nazwalabel = new JLabel("Nazwa");
        JLabel miastolabel = new JLabel("Miasto");
        JLabel adreslabel = new JLabel("Adres");
        JLabel pojemnosclabel = new JLabel("Pojemność");
        nazwa = new JTextField();
        miasto = new JTextField();
        adres = new JTextField();
        pojemnosc = new JTextField();

        nazwalabel.setBounds(30, 30, 100, 30);
        miastolabel.setBounds(30, 70, 100, 30);
        adreslabel.setBounds(30, 110, 100, 30);
        pojemnosclabel.setBounds(30, 150, 100, 30);
        nazwa.setBounds(140, 30, 350, 30);
        miasto.setBounds(140, 70, 350, 30);
        adres.setBounds(140, 110, 350, 30);
        pojemnosc.setBounds(140, 150, 350, 30);
        dodaj.setBounds(150, 220, 90, 30);
        wstecz.setBounds(260, 220, 90, 30);
        
        add(dodaj);
        add(wstecz);
        add(nazwa);
        add(nazwalabel);
        add(miasto);
        add(miastolabel);
        add(adres);
        add(adreslabel);
        add(pojemnosc);
        add(pojemnosclabel);

        wstecz.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                frame.setVisible(true);
                dispose();
            }
        });

        dodaj.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                int poj, rows;
                try 
                {
                    if(nazwa.getText().trim().isEmpty() || miasto.getText().trim().isEmpty() || adres.getText().trim().isEmpty()) throw new MyException();
                    poj = Integer.parseInt(pojemnosc.getText());
                    PreparedStatement pst = baza.conn.prepareStatement("INSERT INTO stadion (nazwa, miasto, adres, pojemnosc) VALUES ('" + nazwa.getText() + "', '" + miasto.getText() + "', '" + adres.getText() + "', " + poj + ");");
                    rows = pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Pomyślnie dodano stadion");
                    pst.close();
                    dispose();
                    frame.setVisible(true);
                } 
                catch (NumberFormatException e)
                {
                    JOptionPane.showMessageDialog(null, "Proszę podać poprawną pojemność");
                }
                catch (SQLException e)
                {
                    JOptionPane.showMessageDialog(null, "Błąd poczas dodawania:\n" + e.getMessage());
                }
                catch (MyException e)
                {
                    JOptionPane.showMessageDialog(null, "Proszę wypełnić wszystkie pola");
                }
                catch (Exception e) 
                {
                    JOptionPane.showMessageDialog(null, "Coś się popsuło - niestety nie wiem co :(");
                }
            }
        });
    }
}