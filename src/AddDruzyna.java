import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class AddDruzyna extends JFrame 
{
    Baza baza;
    AdminFrame frame;
    JTextField nazwa;
    JTextField miasto;
    JComboBox sta;
    JComboBox lig;
    ArrayList<NazwaID> stad;
    int i;

    public AddDruzyna(Baza wejscie, AdminFrame majfrejm) 
    {
        super("Dodawanie drużyny");
        baza = wejscie;
        frame = majfrejm;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(600, 350);
        setLocation(300, 250);
        setLayout(null);

        JButton dodaj = new JButton("Dodaj");
        JButton wstecz = new JButton("Wstecz");
        JLabel nazwalabel = new JLabel("Nazwa");
        JLabel miastolabel = new JLabel("Miasto");
        JLabel stadionlabel = new JLabel("Stadion");
        JLabel ligalabel = new JLabel("Liga");
        sta = new JComboBox();
        nazwa = new JTextField();
        miasto = new JTextField();

        String [] str = {"1", "2", "3"};

        lig = new JComboBox(str);

        stad = new ArrayList<NazwaID>();

        i = 0;

        try 
        { 
            PreparedStatement pst = baza.conn.prepareStatement("SELECT nazwa, id_stadion FROM stadion;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pst.executeQuery();
            while (rs.next())  
            {
                String nazwa = rs.getString("nazwa");
                int id = Integer.parseInt(rs.getString("id_stadion"));
                stad.add(new NazwaID(id, nazwa));
                sta.addItem(stad.get(i).nazwa);
                i++; 
            }
            rs.close();
            pst.close();    
        }
        catch(SQLException e)  
        {
            System.out.println("Blad podczas przetwarzania danych:" + e) ;   
        }

        nazwalabel.setBounds(30, 30, 100, 30);
        nazwa.setBounds(140, 30, 450, 30);
        miastolabel.setBounds(30, 70, 100, 30);
        miasto.setBounds(140, 70, 450, 30);
        ligalabel.setBounds(30, 110, 100, 30);
        lig.setBounds(140, 110, 450, 30);
        stadionlabel.setBounds(30, 150, 110, 30);
        sta.setBounds(140, 150, 450, 30);
        dodaj.setBounds(200, 280, 90, 30);
        wstecz.setBounds(310, 280, 90, 30);
        
        add(dodaj);
        add(wstecz);
        add(nazwalabel);
        add(nazwa);
        add(miastolabel);
        add(miasto);
        add(stadionlabel);
        add(sta);
        add(ligalabel);
        add(lig);

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
                int rows, id, ligga;
                String tmp = "" + lig.getSelectedItem();
                ligga = Integer.parseInt(tmp);
                id = 0;
                for (int k = 0; k < i; k ++)
                {
                    if (stad.get(k).nazwa.equals(sta.getSelectedItem())) id = stad.get(k).id;
                }
                try 
                {
                    if (nazwa.getText().trim().isEmpty() || miasto.getText().trim().isEmpty()) throw new MyException();
                    if (id == 0) throw new Exception();
                    PreparedStatement pst = baza.conn.prepareStatement("INSERT INTO druzyna (nazwa, miasto, liga, id_stadion) VALUES ('" + nazwa.getText() + "', '" + miasto.getText() + "', " + ligga + ", " + id + ");");
                    rows = pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Pomyślnie dodano drużynę");
                    pst.close();
                    dispose();
                    frame.setVisible(true);
                }
                catch (MyException e)
                {
                    JOptionPane.showMessageDialog(null, "Proszę wypełnić wszystkie pola");
                }
                catch (SQLException e)
                {
                    JOptionPane.showMessageDialog(null, "Wystąpił błąd: " + e.getMessage());
                }
                catch (Exception e) 
                {
                    JOptionPane.showMessageDialog(null, "Coś się popsuło - niestety nie wiem co :(");
                }
            }
        });
    }
}