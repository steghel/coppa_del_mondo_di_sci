import java.sql.*;
import java.util.*;
public class MenuInterrogazioni {  
    private int indice;
    private Connection con;
    
    public MenuInterrogazioni (Connection con){
        this.con=con;
        while (true){
            Console.scriviStringa("\n  MENU INTERROGAZIONI");
            Console.scriviStringa("1) Quali atleti partecipano ad una certa gara?\n2) "
                + "atleta:gare a cui partecipa\n3)Torna al menù principale\n");
            indice = Console.leggiIntero();
            switch (indice){
                case 1:
                    Console.scriviStringa("in costruzione");
                    garaAtleti();
                    break;
                case 2:
                    Console.scriviStringa("in costruzione");
                    atletaGare();
                    break;
                case 3:
                    Menu menu = new Menu(con);
                    break;
            }
        }
    }
    
    
    public void garaAtleti(){
        int cont=0;
        char[] vet;
        Gara a = new Gara();
        Atleta atl = new Atleta();
        Specialita spec = new Specialita();
        int i;
        try{  
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select localita,dislivello,"
                    + "lunghezza,numeroporte,giorno,mese,anno,idg  "
                    + "from gara");
            while(rs.next()) {
                cont=0;
                a.setLocalita(rs.getString("localita")); 
                a.setDislivello(rs.getInt("dislivello"));
                a.setLunghezza(rs.getInt("lunghezza"));
                a.setNumeroPorte(rs.getInt("numeroporte"));
                a.setGiorno(rs.getInt("giorno"));
                a.setMese(rs.getInt("mese"));
                a.setAnno(rs.getInt("anno"));
                a.setIdg(rs.getInt("idg"));
                
                Console.scriviStringa("");
                Console.scriviStringa("GARA");                
                Console.scriviStringa(a.toString());
                //scansione della tabella partecipazione
                Statement st1 = con.createStatement();
                String s = new String("select  "
                        + "specialita.tipospecialita, atleta.nome, atleta.cognome "
                        + " from atleta, specialita,partecipazione where partecipazione.idgar "
                        + "= " + a.getIdg() + 
                        " and atleta.ida=partecipazione.idatl and specialita.ids=partecipazione.idsp"); 
            
                //Console.scriviStringa(s);
                ResultSet rs1 = st1.executeQuery(s);
                
                while(rs1.next()) {
                    //spec.setSesso(rs1.getString("nome"));
                    spec.setTipoSpecialita(rs1.getString("tipospecialita"));                    
                    atl.setNome(rs1.getString("nome"));
                    atl.setCognome(rs1.getString("cognome"));
                    Console.scriviStringa("Specialità: " + spec.getTipoSpecialita()
                            + "      Nome: " + atl.getNome() + " "
                            + atl.getCognome());
                    
                    
                    
                }
                Console.scriviStringa(""); 
            }                    
        }
        catch (SQLException se){
            Console.scriviStringa(" SQL exception " +se.getMessage());
        }
    }
    
    public void atletaGare(){
        
    }
}


