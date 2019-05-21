import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddSponsorFunc extends JFrame 
{
    Baza baza;
    AdminFrame frame;
    JTextField branza;

    public AddSponsorFunc(Baza wejscie, AdminFrame majfrejm) 
    {
        super("Dodawanie branży sponsora");
        baza = wejscie;
        frame = majfrejm;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(500, 150);
        setLocation(300, 300);
        setLayout(null);

        JButton dodaj = new JButton("Dodaj");
        JButton wstecz = new JButton("Wstecz");
        JLabel dodaj_branze = new JLabel("Branża");
        branza = new JTextField();

        dodaj_branze.setBounds(30, 30, 100, 30);
        branza.setBounds(140, 30, 330, 30);
        dodaj.setBounds(150, 80, 90, 30);
        wstecz.setBounds(260, 80, 90, 30);
        
        add(dodaj);
        add(wstecz);
        add(dodaj_branze);
        add(branza);

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
                int rows;
                try 
                {
                    if(branza.getText().trim().isEmpty()) throw new MyException();
                    PreparedStatement pst = baza.conn.prepareStatement("INSERT INTO sponsor_branza (branza) VALUES ('" + branza.getText() + "');");
                    rows = pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Pomyślnie dodano branżę");
                    pst.close();
                    dispose();
                    frame.setVisible(true);
                }
                catch (SQLException e)
                {
                    JOptionPane.showMessageDialog(null, "Błąd poczas dodawania:\n" + e.getMessage());
                }
                catch (MyException e)
                {
                    JOptionPane.showMessageDialog(null, "Proszę podać jakąś branżę");
                }
                catch (Exception e) 
                {
                    JOptionPane.showMessageDialog(null, "Coś się popsuło - niestety nie wiem co :(");
                }
            }
        });
    }
}