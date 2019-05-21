import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tabela extends JFrame
{
    Baza baza;
    Tabele tab;
    FanFrame fan;

    public Tabela(Baza bd, Tabele tabfrejm, int ligatab, int akcjatab)
    {
        super("Tabela");
        baza = bd;
        tab = tabfrejm;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocation(100, 100);
        setSize(1000, 600);
        setLayout(null);

        JTextArea raport = new JTextArea();
        JButton wstecz = new JButton("Wstecz");
        
        wstecz.setBounds(450, 540, 100, 30);
        raport.setEditable(false);

        add(wstecz);

        raport.setFont(new Font("Courier", Font.BOLD, 15));
        raport.setText(tabela(ligatab, akcjatab));
        JScrollPane scroll = new JScrollPane(raport);
        scroll.setBounds(10, 10, 980, 520);
        add(scroll);

        wstecz.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                tab.setVisible(true);
                dispose();
            }
        });

    }

    public Tabela(Baza bd, FanFrame fanfrejm, int ligatab, int akcjatab)
    {
        super("Tabela");
        baza = bd;
        fan = fanfrejm;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocation(100, 100);
        setSize(1000, 600);
        setLayout(null);

        JTextArea raport = new JTextArea();
        JButton wstecz = new JButton("Wstecz");
        
        wstecz.setBounds(450, 540, 100, 30);
        raport.setEditable(false);

        add(wstecz);

        raport.setFont(new Font("Courier", Font.BOLD, 15));
        raport.setText(tabela(ligatab, akcjatab));
        JScrollPane scroll = new JScrollPane(raport);
        scroll.setBounds(10, 10, 980, 520);
        add(scroll);

        wstecz.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                fan.setVisible(true);
                dispose();
            }
        });

    }

    public String tabela(int liga, int akcja)
    {
        String wynik = "";
        try 
        {
            String query;

            if (akcja == 1)
            {
                query = "SELECT * FROM tabela_liga(" + liga + ");";
            }
            else if (akcja == 2)
            {
                query = "SELECT * FROM tabela_strzelcy(" + liga + ");";
            }
            else if (akcja == 3)
            {
                query = "SELECT * FROM tabela_bramkarze(" + liga + ");";
            }
            else if (akcja == 4)
            {
                query = "SELECT * FROM pilkarze;";
            }
            else if (akcja == 5)
            {
                query = "SELECT * FROM sedziowie;";
            }
            else if (akcja == 6)
            {
                query = "SELECT * FROM stadiony;";
            }
            else if (akcja == 7)
            {
                query = "SELECT * FROM sztab;";
            }
            else if (akcja == 8)
            {
                query = "SELECT * FROM umowy_sponsorskie;";
            }
            else if (akcja == 9)
            {
                query = "SELECT * FROM mecze(" + liga + ");";
            }
            else query = "";


            Statement pst = baza.conn.createStatement();
            ResultSet rs = pst.executeQuery(query);

            ResultSetMetaData metadata = rs.getMetaData();
            int kolumny = metadata.getColumnCount();
            for (int i = 1; i <= kolumny; i++) 
            {
                if ((akcja == 6) && (i == 1)) wynik += String.format(" %-59s", metadata.getColumnName(i));
                else if (i == 1) wynik += String.format(" %-29s", metadata.getColumnName(i));
                else wynik += String.format("| %-28s", metadata.getColumnName(i));
            }
            wynik += "\n";
            if (akcja == 6) for (int i = 1; i <= 30 * kolumny + 40; i++) wynik += '-';
            else for (int i = 1; i <= 30 * kolumny; i++) wynik += '-';
            wynik += "---";
            wynik += "\n";

            while (rs.next()) 
            {
                String tmp = "";
                for (int i = 1; i <= kolumny; i++) 
                {
                    if ((akcja == 6) && (i == 1)) tmp += String.format("| %-58s", rs.getString(i));
                    else if (i == 1) tmp += String.format(" %-29s", rs.getString(i));
                    else tmp += String.format("| %-28s", rs.getString(i));
                }
                wynik += tmp;
                wynik += "\n";
            }
        }
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "Wystąpił bład podczas łączenia z bazą");
        }
        return wynik;
    }
}