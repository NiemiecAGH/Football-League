import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class AddPilkarz extends JFrame 
{
    Baza baza;
    AdminFrame frame;
    JTextField imie;
    JTextField nazwisko;
    JComboBox poz;
    JComboBox dru;
    ArrayList<NazwaID> pozy;
    ArrayList<NazwaID> druz;
    int i;
    int j;

    public AddPilkarz(Baza wejscie, AdminFrame majfrejm) 
    {
        super("Dodawanie piłkarza");
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
        JLabel pozycjalabel = new JLabel("Pozycja");
        JLabel druzynalabel = new JLabel("Drużyna");
        poz = new JComboBox();
        dru = new JComboBox();
        imie = new JTextField();
        nazwisko = new JTextField();

        pozy = new ArrayList<NazwaID>();
        druz = new ArrayList<NazwaID>();

        i = 0;
        j = 0;

        try 
        { 
            PreparedStatement pst = baza.conn.prepareStatement("SELECT nazwa, id_druzyna FROM druzyna;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pst.executeQuery();
            while (rs.next())  
            {
                String nazwa = rs.getString("nazwa");
                int id = Integer.parseInt(rs.getString("id_druzyna"));
                druz.add(new NazwaID(id, nazwa));
                dru.addItem(druz.get(i).nazwa);
                i++; 
            }
            rs.close();
            pst.close();    
        }
        catch(SQLException e)  
        {
            System.out.println("Blad podczas przetwarzania danych:" + e) ;   
        }

        try 
        { 
            PreparedStatement pst = baza.conn.prepareStatement("SELECT pozycja, id_pozycja FROM pozycja;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pst.executeQuery();
            while (rs.next())  
            {
                String nazwa = rs.getString("pozycja");
                int id = Integer.parseInt(rs.getString("id_pozycja"));
                pozy.add(new NazwaID(id, nazwa));
                poz.addItem(pozy.get(j).nazwa);
                j++; 
            }
            rs.close();
            pst.close();    
        }
        catch(SQLException e)  
        {
            System.out.println("Blad podczas przetwarzania danych:" + e) ;   
        }

        imielabel.setBounds(30, 30, 100, 30);
        imie.setBounds(140, 30, 350, 30);
        nazwiskolabel.setBounds(30, 70, 100, 30);
        nazwisko.setBounds(140, 70, 350, 30);
        druzynalabel.setBounds(30, 110, 100, 30);
        dru.setBounds(140, 110, 350, 30);
        pozycjalabel.setBounds(30, 150, 100, 30);
        poz.setBounds(140, 150, 350, 30);
        dodaj.setBounds(150, 280, 90, 30);
        wstecz.setBounds(260, 280, 90, 30);
        
        add(dodaj);
        add(wstecz);
        add(imielabel);
        add(imie);
        add(nazwiskolabel);
        add(nazwisko);
        add(pozycjalabel);
        add(poz);
        add(druzynalabel);
        add(dru);

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
                int rows, idd, idp, bramkarz;
                idd = 0;
                idp = 0;
                bramkarz = 0;
                for (int k = 0; k < i; k ++)
                {
                    if (druz.get(k).nazwa.equals(dru.getSelectedItem())) idd = druz.get(k).id;
                }
                for (int k = 0; k < j; k ++)
                {
                    if (pozy.get(k).nazwa.equals(poz.getSelectedItem())) idp = pozy.get(k).id;
                }
                try 
                {
                    if (imie.getText().trim().isEmpty() || nazwisko.getText().trim().isEmpty()) throw new MyException("Proszę wypełnić wszystkie pola");
                    if ((idp == 0 || (idd == 0))) throw new Exception();
                    PreparedStatement pst;
                    ResultSet rs;
                    if (idp == 1)
                    {
                        pst = baza.conn.prepareStatement("SELECT * FROM pilkarz JOIN druzyna ON pilkarz.id_druzyna = druzyna.id_druzyna where pozycja = 1 AND druzyna.id_druzyna = " + idd + ";");
                        rs = pst.executeQuery();
                        while (rs.next()) bramkarz++;
                    }
                    if (bramkarz > 0) throw new MyException("W tej drużynie jest już bramkarz");
                    pst = baza.conn.prepareStatement("INSERT INTO pilkarz (imie, nazwisko, pozycja, id_druzyna) VALUES ('" + imie.getText() + "', '" + nazwisko.getText() + "', " + idp + ", " + idd + ");");
                    rows = pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Pomyślnie dodano piłkarza");
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
                    JOptionPane.showMessageDialog(null, e.kom());
                }
                catch (Exception e) 
                {
                    JOptionPane.showMessageDialog(null, "Coś się popsuło - niestety nie wiem co :(");
                }
            }
        });
    }
}