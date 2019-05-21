import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminLogin extends JFrame 
{
    Baza baza;
    MyFrame frame;
    JTextField login;
    JPasswordField pass;

    public AdminLogin(Baza wejscie, MyFrame majfrejm) 
    {
        super("Panel administratora");
        baza = wejscie;
        frame = majfrejm;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(300, 150);
        setLocation(450, 300);
        setLayout(null);

        JButton zaloguj = new JButton("Zaloguj");
        JButton wstecz = new JButton("Wstecz");
        login = new JTextField();
        pass = new JPasswordField();
        JLabel uzytkownik = new JLabel("Użytkownik");
        JLabel haslo = new JLabel("Hasło");

        uzytkownik.setBounds(10, 10, 140, 20);
        haslo.setBounds(10, 40, 140, 20);
        login.setBounds(160, 10, 100, 20);
        pass.setBounds(160, 40, 100, 20);
        zaloguj.setBounds(10, 70, 110, 20);
        wstecz.setBounds(180, 70, 110, 20);

        add(uzytkownik);
        add(haslo);
        add(login);
        add(pass);
        add(zaloguj);
        add(wstecz);

        wstecz.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                frame.setVisible(true);
                dispose();
            }
        });

        zaloguj.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                if(login.getText().equals("admin") && pass.getText().equals("nimda"))
                {
                    dispose();
                    admin_panel();
                }
                else JOptionPane.showMessageDialog(null, "Niepoprawne dane logowania");
            }
        });
    }
    
    void admin_panel()
    {
        AdminFrame panel = new AdminFrame(baza, frame);
    }
}