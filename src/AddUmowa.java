import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class AddUmowa extends JFrame 
{
    Baza baza;
    AdminFrame frame;
    JTextField kwota;
    JComboBox spo;
    JComboBox dru;
    ArrayList<NazwaID> spon;
    ArrayList<NazwaID> druz;
    int i;
    int j;

    public AddUmowa(Baza wejscie, AdminFrame majfrejm) 
    {
        super("Dodawanie umowy sponsorskiej");
        baza = wejscie;
        frame = majfrejm;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(500, 300);
        setLocation(300, 300);
        setLayout(null);

        JButton dodaj = new JButton("Dodaj");
        JButton wstecz = new JButton("Wstecz");
        JLabel kwotalabel = new JLabel("Kwota");
        JLabel sponsorlabel = new JLabel("Sponsor");
        JLabel druzynalabel = new JLabel("Drużyna");
        spo = new JComboBox();
        dru = new JComboBox();
        kwota = new JTextField();

        spon = new ArrayList<NazwaID>();
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
            PreparedStatement pst = baza.conn.prepareStatement("SELECT nazwa, id_sponsor FROM sponsor;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pst.executeQuery();
            while (rs.next())  
            {
                String nazwa = rs.getString("nazwa");
                int id = Integer.parseInt(rs.getString("id_sponsor"));
                spon.add(new NazwaID(id, nazwa));
                spo.addItem(spon.get(j).nazwa);
                j++; 
            }
            rs.close();
            pst.close();    
        }
        catch(SQLException e)  
        {
            System.out.println("Blad podczas przetwarzania danych:" + e) ;   
        }

        kwotalabel.setBounds(30, 30, 100, 30);
        kwota.setBounds(140, 30, 350, 30);
        sponsorlabel.setBounds(30, 70, 100, 30);
        spo.setBounds(140, 70, 350, 30);
        druzynalabel.setBounds(30, 110, 100, 30);
        dru.setBounds(140, 110, 350, 30);
        dodaj.setBounds(150, 230, 90, 30);
        wstecz.setBounds(260, 230, 90, 30);
        
        add(dodaj);
        add(wstecz);
        add(kwotalabel);
        add(kwota);
        add(sponsorlabel);
        add(spo);
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
                int rows, idd, ids, kw;
                idd = 0;
                ids = 0;
                for (int k = 0; k < i; k ++)
                {
                    if (druz.get(k).nazwa.equals(dru.getSelectedItem())) idd = druz.get(k).id;
                }
                for (int k = 0; k < j; k ++)
                {
                    if (spon.get(k).nazwa.equals(spo.getSelectedItem())) ids = spon.get(k).id;
                }
                try 
                {
                    if ((ids == 0 || (idd == 0))) throw new Exception();
                    kw = Integer.parseInt(kwota.getText()); 
                    PreparedStatement pst = baza.conn.prepareStatement("INSERT INTO sponsoring (id_sponsor, id_druzyna, kwota) VALUES (" + ids + ", " + idd + ", " + kw + ");");
                    rows = pst.executeUpdate();
                    pst = baza.conn.prepareStatement("UPDATE druzyna SET budzet = budzet + " + kw + " WHERE id_druzyna = " + idd + ";");
                    rows = pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Pomyślnie dodano umowę");
                    pst.close();
                    dispose();
                    frame.setVisible(true);
                }
                catch (NumberFormatException e)
                {
                    JOptionPane.showMessageDialog(null, "Proszę podać poprawną kwotę");
                }
                catch (SQLException e)
                {
                    JOptionPane.showMessageDialog(null, "Wybrane podmioty mają już ważną umowę sponsorską");
                }
                catch (Exception e) 
                {
                    JOptionPane.showMessageDialog(null, "Coś się popsuło - niestety nie wiem co :(");
                }
            }
        });
    }
}