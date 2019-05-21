import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class AddSponsor extends JFrame 
{
    Baza baza;
    AdminFrame frame;
    JTextField nazwa;
    JComboBox branza;
    ArrayList<NazwaID> lista;
    int i;

    public AddSponsor(Baza wejscie, AdminFrame majfrejm) 
    {
        super("Dodawanie sponsora");
        baza = wejscie;
        frame = majfrejm;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(500, 200);
        setLocation(300, 300);
        setLayout(null);

        JButton dodaj = new JButton("Dodaj");
        JButton wstecz = new JButton("Wstecz");
        JLabel nazwalabel = new JLabel("Nazwa");
        JLabel branzalabel = new JLabel("Branża");
        branza = new JComboBox();
        nazwa = new JTextField();

        lista = new ArrayList<NazwaID>();

        i = 0;

        try 
        { 
            PreparedStatement pst = baza.conn.prepareStatement("SELECT branza, id_branza FROM sponsor_branza;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pst.executeQuery();
            while (rs.next())  
            {
                String nazwa = rs.getString("branza");
                int id = Integer.parseInt(rs.getString("id_branza"));
                lista.add(new NazwaID(id, nazwa));
                branza.addItem(lista.get(i).nazwa);
                i++; 
            }
            rs.close();
            pst.close();    
        }
        catch(SQLException e)  
        {
            System.out.println("Blad podczas przetwarzania danych:"+e) ;   
        }

        nazwalabel.setBounds(30, 30, 100, 30);
        nazwa.setBounds(140, 30, 350, 30);
        branzalabel.setBounds(30, 70, 100, 30);
        branza.setBounds(140, 70, 350, 30);
        dodaj.setBounds(150, 130, 90, 30);
        wstecz.setBounds(260, 130, 90, 30);
        
        add(dodaj);
        add(wstecz);
        add(nazwa);
        add(nazwalabel);
        add(branza);
        add(branzalabel);

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
                int rows, id;
                id = 0;
                for (int k = 0; k < i; k ++)
                {
                    if (lista.get(k).nazwa.equals(branza.getSelectedItem())) id = lista.get(k).id;
                }
                try 
                {
                    if(nazwa.getText().trim().isEmpty()) throw new MyException();
                    if (id == 0) throw new Exception();
                    PreparedStatement pst = baza.conn.prepareStatement("INSERT INTO sponsor (nazwa, branza) VALUES ('" + nazwa.getText() + "', " + id + ");");
                    rows = pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Pomyślnie dodano sponsora");
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
                    JOptionPane.showMessageDialog(null, "Proszę podać nazwę");
                }
                catch (Exception e) 
                {
                    JOptionPane.showMessageDialog(null, "Coś się popsuło - niestety nie wiem co :(");
                }
            }
        });
    }
}