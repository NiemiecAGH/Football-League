import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class AddSztab extends JFrame 
{
    Baza baza;
    AdminFrame frame;
    JTextField imie;
    JTextField nazwisko;
    JComboBox fun;
    JComboBox dru;
    ArrayList<NazwaID> funk;
    ArrayList<NazwaID> druz;
    int i;
    int j;

    public AddSztab(Baza wejscie, AdminFrame majfrejm) 
    {
        super("Dodawanie sztabowca");
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
        JLabel funkcjalabel = new JLabel("Funkcja");
        JLabel druzynalabel = new JLabel("Drużyna");
        fun = new JComboBox();
        dru = new JComboBox();
        imie = new JTextField();
        nazwisko = new JTextField();

        funk = new ArrayList<NazwaID>();
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
            PreparedStatement pst = baza.conn.prepareStatement("SELECT funkcja, id_funkcja FROM sztabowiec_funkcja;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pst.executeQuery();
            while (rs.next())  
            {
                String nazwa = rs.getString("funkcja");
                int id = Integer.parseInt(rs.getString("id_funkcja"));
                funk.add(new NazwaID(id, nazwa));
                fun.addItem(funk.get(j).nazwa);
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
        funkcjalabel.setBounds(30, 150, 100, 30);
        fun.setBounds(140, 150, 350, 30);
        dodaj.setBounds(150, 280, 90, 30);
        wstecz.setBounds(260, 280, 90, 30);
        
        add(dodaj);
        add(wstecz);
        add(imielabel);
        add(imie);
        add(nazwiskolabel);
        add(nazwisko);
        add(funkcjalabel);
        add(fun);
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
                int rows, idd, idf;
                idd = 0;
                idf = 0;
                for (int k = 0; k < i; k ++)
                {
                    if (druz.get(k).nazwa.equals(dru.getSelectedItem())) idd = druz.get(k).id;
                }
                for (int k = 0; k < j; k ++)
                {
                    if (funk.get(k).nazwa.equals(fun.getSelectedItem())) idf = funk.get(k).id;
                }
                try 
                {
                    if (imie.getText().trim().isEmpty() || nazwisko.getText().trim().isEmpty()) throw new MyException();
                    if ((idf == 0 || (idd == 0))) throw new Exception();
                    PreparedStatement pst = baza.conn.prepareStatement("INSERT INTO sztabowiec (imie, nazwisko, funkcja, id_druzyna) VALUES ('" + imie.getText() + "', '" + nazwisko.getText() + "', " + idf + ", " + idd + ");");
                    rows = pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Pomyślnie dodano sztabowca");
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