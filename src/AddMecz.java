import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class AddMecz extends JFrame 
{
    Baza baza;
    AdminFrame frame;
    JTextField bramkigosp;
    JTextField bramkigosc;
    JTextField frekwencja;
    JComboBox dru2;
    JComboBox dru1;
    JComboBox sed;
    ArrayList<NazwaID> druz1;
    ArrayList<NazwaID> druz2;
    ArrayList<NazwaID> pilkarze1;
    ArrayList<NazwaID> pilkarze2;
    ArrayList<NazwaID> sedziowie;
    int i;
    int j;
    int n;
    int liga;
    int pilkarzegosp;
    int pilkarzegosc;

    public AddMecz(Baza wejscie, AdminFrame majfrejm, int a) 
    {
        super("Dodawanie meczu");
        baza = wejscie;
        frame = majfrejm;
        liga = a;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(600, 400);
        setLocation(300, 200);
        setLayout(null);
        
        JButton dodaj = new JButton("Dodaj");
        JButton wstecz = new JButton("Wstecz");
        JLabel gospodarzlabel = new JLabel("Gospodarze");
        JLabel gosclabel = new JLabel("Goście");
        JLabel bramkigosplabel = new JLabel("Bramki gospodarzy");
        JLabel bramkigosclabel = new JLabel("Bramki gości");
        JLabel frekwencjalabel = new JLabel("Frekwencja w %");
        JLabel sedzialabel = new JLabel("Sędzia");
        sed = new JComboBox();
        dru2 = new JComboBox();
        dru1 = new JComboBox();
        bramkigosp = new JTextField();
        bramkigosc = new JTextField();
        frekwencja = new JTextField();

        druz1 = new ArrayList<NazwaID>();
        druz2 = new ArrayList<NazwaID>();
        pilkarze1 = new ArrayList<NazwaID>();
        pilkarze2 = new ArrayList<NazwaID>();
        sedziowie = new ArrayList<NazwaID>();

        i = 0;
        j = 0;
        n = 0;

        try 
        { 
            PreparedStatement pst = baza.conn.prepareStatement("SELECT nazwa, id_druzyna FROM druzyna WHERE liga = " + liga + ";", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pst.executeQuery();
            while (rs.next())  
            {
                String nazwa = rs.getString("nazwa");
                int id = Integer.parseInt(rs.getString("id_druzyna"));
                druz1.add(new NazwaID(id, nazwa));
                dru1.addItem(druz1.get(i).nazwa);
                druz2.add(new NazwaID(id, nazwa));
                dru2.addItem(druz2.get(j).nazwa);
                i++; 
                j++;
            }
            pst = baza.conn.prepareStatement("SELECT id_sedzia, imie, nazwisko FROM sedzia WHERE uprawnienia <= " + liga + ";", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = pst.executeQuery();
            while (rs.next())
            {
                String nazwa = rs.getString("imie") + " " + rs.getString("nazwisko");
                int id = Integer.parseInt(rs.getString("id_sedzia"));
                sedziowie.add(new NazwaID(id, nazwa));
                sed.addItem(sedziowie.get(n).nazwa);
                n++;
            }
            rs.close();
            pst.close();    
        }
        catch(SQLException e)  
        {
            System.out.println("Blad podczas przetwarzania danych:" + e) ;   
        }

        gospodarzlabel.setBounds(30, 30, 200, 30);
        dru1.setBounds(240, 30, 350, 30);
        bramkigosplabel.setBounds(30, 70, 200, 30);
        bramkigosp.setBounds(240, 70, 350, 30);
        bramkigosclabel.setBounds(30, 110, 200, 30);
        bramkigosc.setBounds(240, 110, 350, 30);
        gosclabel.setBounds(30, 150, 200, 30);
        dru2.setBounds(240, 150, 350, 30);
        frekwencjalabel.setBounds(30, 190, 200, 30);
        frekwencja.setBounds(240, 190, 350, 30);
        sedzialabel.setBounds(30, 230, 200, 30);
        sed.setBounds(240, 230, 350, 30);
        dodaj.setBounds(200, 330, 90, 30);
        wstecz.setBounds(310, 330, 90, 30);
        
        add(dodaj);
        add(wstecz);
        add(gospodarzlabel);
        add(dru1);
        add(bramkigosplabel);
        add(bramkigosp);
        add(bramkigosclabel);
        add(bramkigosc);
        add(gosclabel);
        add(dru2);
        add(frekwencjalabel);
        add(frekwencja);
        add(sedzialabel);
        add(sed);

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
                pilkarzegosp = 0;
                pilkarzegosc = 0;
                pilkarze1.clear();
                pilkarze2.clear();
                int rows, id1, id2, strzelec, gole1, gole2, kibice, ids, bramkarz1, bramkarz2;
                double frek;
                id1 = 0;
                id2 = 0;
                ids = 0;
                strzelec = 0;
                gole1 = 0;
                gole2 = 0;
                bramkarz1 = 0;
                bramkarz2 = 0;
                for (int k = 0; k < i; k ++)
                {
                    if (druz1.get(k).nazwa.equals(dru1.getSelectedItem())) id1 = druz1.get(k).id;
                }
                for (int k = 0; k < j; k ++)
                {
                    if (druz2.get(k).nazwa.equals(dru2.getSelectedItem())) id2 = druz2.get(k).id;
                }
                for (int k = 0; k < n; k ++)
                {
                    if (sedziowie.get(k).nazwa.equals(sed.getSelectedItem())) ids = sedziowie.get(k).id;
                }
                try 
                {
                    frek = Double.parseDouble(frekwencja.getText()) / 100;
                    gole1 = Integer.parseInt(bramkigosp.getText());
                    gole2 = Integer.parseInt(bramkigosc.getText());
                    if ((frek < 0) || (frek > 100)) throw new MyException("Proszę podać frekwencję z zakresu od 0 do 100");
                    if ((gole1 < 0) || (gole2 < 0)) throw new MyException("Proszę podać nieujemną liczbę bramek");
                    if ((id1 == 0) || (id2 == 0) || (ids == 0)) throw new Exception();
                    if (id1 == id2) throw new MyException("Drużyna nie może grać przeciwko sobie samej");
                    PreparedStatement pst;
                    ResultSet rs;
                    pst = baza.conn.prepareStatement("SELECT pojemnosc FROM druzyna JOIN stadion ON druzyna.id_stadion = stadion.id_stadion WHERE id_druzyna = " + id1 + ";");
                    rs = pst.executeQuery();
                    String pojemnosc = "";
                    while (rs.next()) pojemnosc += rs.getString("pojemnosc");
                    kibice = (int) (frek * Double.parseDouble(pojemnosc));
                    pst = baza.conn.prepareStatement("SELECT pozycja, id_pilkarz FROM pilkarz WHERE id_druzyna = " + id1 + ";");
                    rs = pst.executeQuery();
                    while (rs.next())  
                    {
                        String nazwa = rs.getString("pozycja");
                        int id = Integer.parseInt(rs.getString("id_pilkarz"));
                        if (!nazwa.equals("1"))
                        {
                            pilkarze1.add(new NazwaID(id, nazwa));
                            pilkarzegosp++;
                        }
                        else bramkarz1 = 1;
                    }
                    pst = baza.conn.prepareStatement("SELECT pozycja, id_pilkarz FROM pilkarz WHERE id_druzyna = " + id2 + ";");
                    rs = pst.executeQuery();
                    while (rs.next())  
                    {
                        String nazwa = rs.getString("pozycja");
                        int id = Integer.parseInt(rs.getString("id_pilkarz"));
                        if (!nazwa.equals("1"))
                        {
                            pilkarze2.add(new NazwaID(id, nazwa));
                            pilkarzegosc++;
                        }
                        else bramkarz2 = 1;
                    }
                    if ((bramkarz1 == 0) || (bramkarz2 == 0)) throw new MyException("Brakuje bramkarzy w tych drużynach");
                    if((pilkarzegosc < 1) || (pilkarzegosp < 1)) throw new MyException("Brakuje zawodników z pola w tych drużynach");
                    Random gen = new Random();
                    
                    pst = baza.conn.prepareStatement("INSERT INTO mecz (id_gospodarz, id_gosc, id_sedzia, bramki_gospodarz, bramki_gosc, frekwencja) VALUES (" + id1 + ", " + id2 + ", " + ids + ", " + gole1 + ", " + gole2 + ", " + kibice + ");");
                    rows = pst.executeUpdate();
                    if (gole1 > gole2)
                    {
                        pst = baza.conn.prepareStatement("UPDATE druzyna SET punkty = punkty + 3 WHERE id_druzyna = " + id1 + ";");
                        rows = pst.executeUpdate();
                    }
                    else if (gole1 < gole2)
                    {
                        pst = baza.conn.prepareStatement("UPDATE druzyna SET punkty = punkty + 3 WHERE id_druzyna = " + id2 + ";");
                        rows = pst.executeUpdate();
                    }
                    else if (gole1 == gole2)
                    {
                        pst = baza.conn.prepareStatement("UPDATE druzyna SET punkty = punkty + 1 WHERE id_druzyna = " + id1 + ";");
                        rows = pst.executeUpdate();
                        pst = baza.conn.prepareStatement("UPDATE druzyna SET punkty = punkty + 1 WHERE id_druzyna = " + id2 + ";");
                        rows = pst.executeUpdate();
                    }
                    if (gole1 > 0)
                    {
                        pst = baza.conn.prepareStatement("UPDATE druzyna SET bramki_zdobyte = bramki_zdobyte + " + gole1 + " WHERE id_druzyna = " + id1 + ";");
                        rows = pst.executeUpdate();
                        pst = baza.conn.prepareStatement("UPDATE druzyna SET bramki_stracone = bramki_stracone + " + gole1 + " WHERE id_druzyna = " + id2 + ";");
                        rows = pst.executeUpdate();
                    }
                    else
                    {
                        pst = baza.conn.prepareStatement("SELECT id_pilkarz FROM pilkarz JOIN druzyna ON pilkarz.id_druzyna = druzyna.id_druzyna WHERE pozycja = 1 AND pilkarz.id_druzyna = " + id2 + ";");
                        rs = pst.executeQuery();
                        String tmp = "";
                        while(rs.next()) tmp += rs.getString("id_pilkarz");
                        pst = baza.conn.prepareStatement("UPDATE pilkarz SET czyste_konta = czyste_konta + 1 WHERE id_pilkarz = " + tmp + ";");
                        rows = pst.executeUpdate();
                    }
                    if (gole2 > 0)
                    {
                        pst = baza.conn.prepareStatement("UPDATE druzyna SET bramki_zdobyte = bramki_zdobyte + " + gole2 + " WHERE id_druzyna = " + id2 + ";");
                        rows = pst.executeUpdate();
                        pst = baza.conn.prepareStatement("UPDATE druzyna SET bramki_stracone = bramki_stracone + " + gole2 + " WHERE id_druzyna = " + id1 + ";");
                        rows = pst.executeUpdate();
                    }
                    else
                    {
                        pst = baza.conn.prepareStatement("SELECT id_pilkarz FROM pilkarz JOIN druzyna ON pilkarz.id_druzyna = druzyna.id_druzyna WHERE pozycja = 1 AND pilkarz.id_druzyna = " + id1 + ";");
                        rs = pst.executeQuery();
                        String tmp = "";
                        while(rs.next()) tmp += rs.getString("id_pilkarz");
                        pst = baza.conn.prepareStatement("UPDATE pilkarz SET czyste_konta = czyste_konta + 1 WHERE id_pilkarz = " + tmp + ";");
                        rows = pst.executeUpdate();
                    }
                    int tmp = gole1;
                    while(tmp > 0)
                    {
                        int id = pilkarze1.get(gen.nextInt(pilkarzegosp)).id;
                        pst = baza.conn.prepareStatement("UPDATE pilkarz SET bramki = bramki + 1 WHERE id_pilkarz = " + id + ";");
                        rows = pst.executeUpdate();
                        tmp--;
                    }
                    tmp = gole2;
                    while(tmp > 0)
                    {
                        int id = pilkarze2.get(gen.nextInt(pilkarzegosc)).id;
                        pst = baza.conn.prepareStatement("UPDATE pilkarz SET bramki = bramki + 1 WHERE id_pilkarz = " + id + ";");
                        rows = pst.executeUpdate();
                        tmp--;
                    }
                    pst = baza.conn.prepareStatement("UPDATE druzyna SET różnica_bramek = bramki_zdobyte - bramki_stracone;");
                    rows = pst.executeUpdate();
                    pst = baza.conn.prepareStatement("UPDATE sedzia SET ilosc_meczow = ilosc_meczow + 1 WHERE id_sedzia = " + ids + ";");
                    rows = pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Pomyślnie dodano mecz");
                    pst.close();
                    dispose();
                    frame.setVisible(true);
                }
                catch (SQLException e)
                {
                    JOptionPane.showMessageDialog(null, "Ten mecz już się odbył");
                }
                catch (MyException e)
                {
                    JOptionPane.showMessageDialog(null, e.kom());
                }
                catch (NumberFormatException e)
                {
                    JOptionPane.showMessageDialog(null, "Proszę podać prawidłowe wartości");
                }
                catch (Exception e) 
                {
                    JOptionPane.showMessageDialog(null, "Coś się popsuło - niestety nie wiem co :(");
                }
            }
        });

    }
}