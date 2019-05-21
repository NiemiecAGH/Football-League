import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class AddSedzia extends JFrame 
{
    Baza baza;
    AdminFrame frame;
    JTextField imie;
    JTextField nazwisko;
    JTextField miasto;
    JComboBox uprawnienia;

    public AddSedzia(Baza wejscie, AdminFrame majfrejm) 
    {
        super("Dodawanie sędziego");
        baza = wejscie;
        frame = majfrejm;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(500, 350);
        setLocation(300, 300);
        setLayout(null);

        JButton dodaj = new JButton("Dodaj");
        JButton wstecz = new JButton("Wstecz");
        JLabel imielabel = new JLabel("Imię");
        JLabel nazwiskolabel = new JLabel("Nazwisko");
        JLabel miastolabel = new JLabel("Miasto");
        JLabel uprawnienialabel = new JLabel("Uprawnienia");
        imie = new JTextField();
        nazwisko = new JTextField();
        miasto = new JTextField();

        String[] upr = {"1", "2", "3"};

        uprawnienia = new JComboBox(upr);

        imielabel.setBounds(30, 30, 100, 30);
        imie.setBounds(140, 30, 350, 30);
        nazwiskolabel.setBounds(30, 70, 100, 30);
        nazwisko.setBounds(140, 70, 350, 30);
        miastolabel.setBounds(30, 110, 100, 30);
        miasto.setBounds(140, 110, 350, 30);
        uprawnienialabel.setBounds(30, 150, 100, 30);
        uprawnienia.setBounds(140, 150, 350, 30);
        dodaj.setBounds(150, 280, 90, 30);
        wstecz.setBounds(260, 280, 90, 30);
        
        add(dodaj);
        add(wstecz);
        add(imielabel);
        add(imie);
        add(nazwiskolabel);
        add(nazwisko);
        add(uprawnienialabel);
        add(uprawnienia);
        add(miastolabel);
        add(miasto);

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
                int rows, liga;
                String tmp = "" + uprawnienia.getSelectedItem();
                liga = Integer.parseInt(tmp);
                try 
                {
                    if (imie.getText().trim().isEmpty() || nazwisko.getText().trim().isEmpty() || miasto.getText().trim().isEmpty()) throw new MyException();
                    PreparedStatement pst = baza.conn.prepareStatement("INSERT INTO sedzia (imie, nazwisko, miasto, uprawnienia) VALUES ('" + imie.getText() + "', '" + nazwisko.getText() + "', '" + miasto.getText() + "', " + liga + ");");
                    rows = pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Pomyślnie dodano sędziego");
                    pst.close();
                    dispose();
                    frame.setVisible(true);
                }
                catch (SQLException e)
                {
                    JOptionPane.showMessageDialog(null, "Wystąpił błąd: " + e.getMessage());
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