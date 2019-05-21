import java.sql.*;

public class Baza
{

    Connection conn;

    public Baza()
    {
        try 
        {
            conn = DriverManager.getConnection("jdbc:postgresql://pascal.fis.agh.edu.pl:5432/u6polanski?currentSchema=projekt", "u6polanski", "6polanski");
            System.out.println("Nawiązano połączenie z bazą danych!");
        } 
        catch (SQLException e) 
        {
            System.out.println("Nie udało się połączyć z bazą: " + e.getMessage());
        }
    }
}