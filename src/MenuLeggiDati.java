import java.sql.*;
import java.util.*;

public class MenuLeggiDati {
  Connection con;
  int indice;
    
    //costruttore
  
    public MenuLeggiDati(Connection con){
      this.con=con;
      while(true){  
        Console.scriviStringa("\n  MENU SELEZIONE DATI");
        Console.scriviStringa("1) Leggi dati atleti \n2) "
             + "Leggi dati gare\n3) Leggi specialita'\n4) Leggi a quale gare "
             + "partecipano gli atleti\n5) Punteggio complessivo di un atleta in una singola specialità\n6) Vai al menù principale\n");
        indice = Console.leggiIntero();
        switch (indice){
           case 1:
                Console.scriviStringa("DATI DEI VARI ATLETI PRESENTI IN MEMORIA");
                leggiAtleti();
                break;
           case 2:
                Console.scriviStringa("DATI DELLE  VARIE GARE PRESENTI IN MEMORIA");
                leggiGara();
                break;    
           case 3:
                Console.scriviStringa("SPECIALITA' A CUI PARTECIPANO GLI ATLETI");
                leggiSpecialita();
                break; 
           case 4:
                Console.scriviStringa("ATLETI CHE PARTECIPANO ALLE VARIE GARE CON"
                        + " LE RELATIVE SPECIALITA'");
                garaAtleti();
                break;
           case 5:
                Console.scriviStringa("ATLETI:PUNTEGGIO COMPLESSIVO");
                punteggioComplessivo();
                break;    
           case 6:
                Menu menu = new Menu(con);
                break;
           
        }
      }
    }
    
    public void leggiAtleti(){
        //Console.scriviStringa("sono in leggi atleti");
        char[] vett;
        Atleta a = new Atleta();           
        try{
            //scansione della tabella atleta
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select nome,cognome,nazionalita,"
                    + "ida from atleta");
            while(rs.next()) {
                a.setNome(rs.getString("nome")); 
                a.setCognome(rs.getString("cognome")); 
                a.setNazionalita(rs.getString("nazionalita")); 
                a.setIDa(rs.getInt("ida"));
                Console.scriviStringa("------------------------------");
                Console.scriviStringa(a.toString()); 
               
                //scansione della tabella specialita
                Statement st1 = con.createStatement();
                String s = new String("select specialita.sesso,specialita.tipospecialita from specialita,"
                    + " atleta, atlespe  where atleta.ida = " + a.getIDa() + " and atleta.ida=atlespe.idatleta and "
                    + "atlespe.idspecialita = specialita.ids" );
            
                //Console.scriviStringa(s);
                ResultSet rs1 = st1.executeQuery(s);
                boolean b = true;
                while(rs1.next()) {
                    //scrivi il sesso dell'atleta una sola volta
                    if (b){
                        vett = rs1.getString("sesso").toCharArray();
                        String str = new String ("Sesso: " + vett [0] + 
                                "\n\nSpecialità praticate: " );
                        
                        Console.scriviStringa(str);
                        b = false;
                    }
                    //scrivi tutte le specialità praticate dall'atleta
                    Console.scriviStringa(rs1.getString("tipospecialita"));
                    
                }
            Console.scriviStringa("");                            
            } 
            
        }
        catch (SQLException se){
            Console.scriviStringa(" SQL exception " +se.getMessage());
        }
    }
    
    
    
    public void leggiGara(){
        char[] vet;
        Gara a = new Gara();
        int i;
        try{  
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select localita,dislivello,"
                    + "lunghezza,numeroporte,giorno,mese,anno,idg  from gara");
            while(rs.next()) {
                a.setLocalita(rs.getString("localita")); 
                a.setDislivello(rs.getInt("dislivello"));
                a.setLunghezza(rs.getInt("lunghezza"));
                a.setNumeroPorte(rs.getInt("numeroporte"));
                a.setGiorno(rs.getInt("giorno"));
                a.setMese(rs.getInt("mese"));
                a.setAnno(rs.getInt("anno"));
                a.setIdg(rs.getInt("idg"));
                
                Console.scriviStringa("------------------------------");
                Console.scriviStringa(a.toString());
                
                //scansione della tabella specialita
                Statement st1 = con.createStatement();
                String s = new String("select specialita.sesso,specialita.tipospecialita from specialita,"
                    + " gara, gaspe  where gara.idg = " + a.getIdg() + " and gara.idg=gaspe.idgar and "
                    + "gaspe.idspec = specialita.ids" );
            
                //Console.scriviStringa(s);
                ResultSet rs1 = st1.executeQuery(s);
                boolean b = true;
                while(rs1.next()) {
                    //scrivi il sesso degli atleti che partecipano alla gara una sola volta
                    if (b){
                        vet = rs1.getString("sesso").toCharArray();
                        String str = new String ("Sesso degli atleti: " + vet [0] + 
                                "\n\nSpecialità: " );
                        Console.scriviStringa(str);
                        b = false;
                    }
                    //scrivi tutte le specialità praticate dall'atleta
                    Console.scriviStringa(rs1.getString("tipospecialita"));
                }
                Console.scriviStringa(""); 
            }                    
        }
        catch (SQLException se){
            Console.scriviStringa(" SQL exception " +se.getMessage());
        }
    }
        
    public void leggiSpecialita(){
        char[]c; 
        Specialita a = new Specialita();           
        try{  
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select sesso,tipospecialita,"
                    + "ids from specialita");
            while(rs.next()) {
                c=rs.getString("sesso").toCharArray();
                a.setSesso(c[0]); 
                a.setTipoSpecialita(rs.getString("tipospecialita")); 
                a.setIDs(rs.getInt("ids")); 
                Console.scriviStringa(a.toString());                          
            }                    
        }
        catch (SQLException se){
            Console.scriviStringa(" SQL exception " +se.getMessage());
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
                
                
                Console.scriviStringa("------------------------------");
                Console.scriviStringa("GARA");                
                Console.scriviStringa(a.toString());
                
                //scansione della tabella partecipazione
                Statement st1 = con.createStatement();
                String s = new String("select  "
                        + "specialita.tipospecialita,specialita.sesso, atleta.nome, atleta.cognome, partecipazione.punteggio "
                        + " from atleta, specialita,partecipazione where partecipazione.idgar "
                        + "= " + a.getIdg() + 
                        " and atleta.ida=partecipazione.idatl and specialita.ids=partecipazione.idsp"); 
            
                //Console.scriviStringa(s);
                ResultSet rs1 = st1.executeQuery(s);
                
                while(rs1.next()) {
                    //spec.setSesso(rs1.getString("sesso"));
                    int punt;
                    spec.setTipoSpecialita(rs1.getString("tipospecialita"));                    
                    atl.setNome(rs1.getString("nome"));
                    atl.setCognome(rs1.getString("cognome"));
                    punt=rs1.getInt("punteggio"); 
                    Console.scriviStringa("Specialità: " + spec.getTipoSpecialita()
                            + "      Nome: " + atl.getNome() + " "
                            + atl.getCognome() + "       Punteggio: " + punt);
                            
                    
                    
                }
                Console.scriviStringa(""); 
            }                    
        }
        catch (SQLException se){
            Console.scriviStringa(" SQL exception " +se.getMessage());
        }
    }
    
    
    public void punteggioComplessivo(){
        char[] vett;
        Atleta a = new Atleta();
        Specialita spec = new Specialita();
        try{
            //scansione della tabella atleta
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select nome,cognome,nazionalita,"
                    + "ida from atleta");
            while(rs.next()) {
                a.setNome(rs.getString("nome")); 
                a.setCognome(rs.getString("cognome")); 
                a.setNazionalita(rs.getString("nazionalita")); 
                a.setIDa(rs.getInt("ida"));
                Console.scriviStringa("------------------------------");
                Console.scriviStringa(a.toString()); 
               
                //scansione della tabella specialita
                Statement st1 = con.createStatement();
                String s = new String("select specialita.ids,specialita.sesso,specialita.tipospecialita from specialita,"
                    + " atleta, atlespe  where atleta.ida = " + a.getIDa() + " and atleta.ida=atlespe.idatleta and "
                    + "atlespe.idspecialita = specialita.ids" );
            
                //Console.scriviStringa(s);
                ResultSet rs1 = st1.executeQuery(s);
                boolean b = true;
                while(rs1.next()) {
                    spec.setIDs(rs1.getInt("ids"));
                    spec.setTipoSpecialita(rs1.getString("tipospecialita"));
                    //Console.scriviStringa(spec.getTipoSpecialita());
                    Statement st2 = con.createStatement();
                    String s2 = new String("select punteggio from partecipazione "
                            + "where idatl=" + a.getIDa() + " and idsp=" + spec.getIDs());
                    ResultSet rs2 = st2.executeQuery(s2);
                    int tot=0;
                    while(rs2.next()) {                        
                        tot+=rs2.getInt("punteggio");                        
                    }
                    Console.scriviStringa("Punteggio totale nella specialità "
                            + spec.getTipoSpecialita() + ": " + tot);
                }
            }
        }
        catch (SQLException se){
            Console.scriviStringa(" SQL exception " +se.getMessage());
        }
    }
}

