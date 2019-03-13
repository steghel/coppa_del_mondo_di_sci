import java.sql.*;
import java.util.*;

public class Connessione {
    String url = null;
    Connection con = null;
    
    public void effettuaConnessione() {    
        try {
            Class.forName("org.postgresql.Driver");
            Console.scriviStringa("Driver postgre caricato");
            url = "jdbc:postgresql://localhost:5432/progetto8";
            Properties props = new Properties();
            props.setProperty("user","postgres");
            props.setProperty("password","paperino");
            Console.scriviStringa("in attesa della connessione al database");
            con = DriverManager.getConnection(url,props);
            Console.scriviStringa("Connesso al database");
        } 
        catch (ClassNotFoundException cnfe) {
            System.out.println("Class not found exception: " + cnfe.getMessage());
	}
        catch (SQLException se) {
            System.out.println("SQL exception: "+ se.getMessage());
        }
    }
        
        public Connection getConnessione(){
            return con;
        }
    
}
