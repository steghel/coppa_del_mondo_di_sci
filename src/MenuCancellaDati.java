import java.sql.*;
import java.util.*;

public class MenuCancellaDati {
    int indice;
    Connection con;
    
    public MenuCancellaDati (Connection con){
        this.con=con;
        while (true){
            Console.scriviStringa("\n  MENU CANCELLA DATI");
            Console.scriviStringa("1) Cancella dati atleta \n2) "
                + "Cancella dati gara\n3) Cancella specialità\n4) Cancella la partecipazione"
                    + " di un atleta ad una gara"
                    + "\n5) Torna al menù principale\n");
            indice = Console.leggiIntero();
            switch (indice){
                case 1:
                    cancellaDatiAtleta();
                    break;
                case 2:
                    cancellaDatiGara();
                    break;
                case 3:
                    cancellaSpecialita();
                    break;
                case 4:
                    cancellaPartecipazione();
                    break;    
                case 5:
                    Menu menu = new Menu(con);
                    break;
                default:
                    continue;
            }
        }
    }


    public void cancellaDatiAtleta(){
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
                Console.scriviStringa("ATLETA");
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
                                "\nSpecialità: " );
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
        
        
        
        Console.scriviStringa("Scrivi il codice dell'atleta da cancellare");
        int i=Console.leggiIntero();
        try{       
            Statement st = con.createStatement();
            String str = new String("delete from partecipazione where idatl="+ i + ";");
            Console.scriviStringa(str);
            st.executeUpdate(str);            
        }
        catch (SQLException se){
            Console.scriviStringa(" SQL exception " +se.getMessage());            
        }
        
        try{       
            Statement st = con.createStatement();
            String str = new String("delete from atlespe where idatleta="+ i + ";");
            Console.scriviStringa(str);
            st.executeUpdate(str);            
        }
        catch (SQLException se){
            Console.scriviStringa(" SQL exception " +se.getMessage());            
        }
        try{       
            Statement st1 = con.createStatement();
            String str1 = new String("delete from atleta where ida="+ i + ";");
            Console.scriviStringa(str1);
            st1.executeUpdate(str1);            
        }
        catch (SQLException se){
            Console.scriviStringa(" SQL exception " +se.getMessage());            
        }
    }
public void cancellaSpecialita(){
        
        Specialita a = new Specialita();           
        try{
            
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select sesso,tipospecialita,"
                    + "ids from specialita");
            while(rs.next()) {
                String s=rs.getString("sesso");                
                a.setSesso(s.charAt(0)); 
                a.setTipoSpecialita(rs.getString("tipospecialita"));               
                a.setIDs(rs.getInt("ids"));
                Console.scriviStringa("SPECIALITA'");
                Console.scriviStringa(a.toString()); 
            } 
        }      
                
        catch (SQLException se){
            Console.scriviStringa(" SQL exception " +se.getMessage());            
        }
        
        Console.scriviStringa("Scrivi il codice della specialità da cancellare");
        int i=Console.leggiIntero();
        try{       
            Statement st = con.createStatement();
            String str = new String("delete from partecipazione where "
                    + "partecipazione.idatl  in (select atlespe.idatleta"
                    + " from atlespe where idspecialita=" + i + ")");
            Console.scriviStringa(str);
            st.executeUpdate(str); 
            String str1 = new String("delete from atlespe where idspecialita="+ i + ";");
            //Console.scriviStringa(str1);
            st.executeUpdate(str1);          
            String str2 = new String("delete from gaspe where idspec="+ i + ";");
            //Console.scriviStringa(str1);
            st.executeUpdate(str2);          
            String str3 = new String("delete from specialita where ids="+ i + ";");
            //Console.scriviStringa(str2);
            st.executeUpdate(str3);            
        }
        catch (SQLException se){
            Console.scriviStringa(" SQL exception " +se.getMessage());            
        }
}
        
   
    
public void cancellaDatiGara(){
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
                Console.scriviStringa("GARA");
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
                                "\nSpecialità: " );
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
        
        Console.scriviStringa("Scrivi il codice della gara da cancellare");
        i=Console.leggiIntero();
        try{       
            Statement st = con.createStatement();
            String str = new String("delete from partecipazione where idgar="+ i + ";");
            Console.scriviStringa(str);
            st.executeUpdate(str);            
        }
        catch (SQLException se){
            Console.scriviStringa(" SQL exception " +se.getMessage());            
        }
        
        try{       
            Statement st = con.createStatement();
            String str = new String("delete from gaspe where idgar="+ i + ";");
            Console.scriviStringa(str);
            st.executeUpdate(str);            
        }
        catch (SQLException se){
            Console.scriviStringa(" SQL exception " +se.getMessage());            
        }
        try{       
            Statement st1 = con.createStatement();
            String str1 = new String("delete from gara where idg="+ i + ";");
            Console.scriviStringa(str1);
            st1.executeUpdate(str1);            
        }
        catch (SQLException se){
            Console.scriviStringa(" SQL exception " +se.getMessage());            
        }
    }



    public void cancellaPartecipazione(){
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
                Console.scriviStringa("GARA");
                Console.scriviStringa(a.toString());
                //scansione della tabella partecipazione
                Statement st1 = con.createStatement();
                String s = new String("select  "
                        + "specialita.tipospecialita,specialita.ids, atleta.nome, atleta.cognome,atleta.ida "
                        + " from atleta, specialita,partecipazione where partecipazione.idgar "
                        + "= " + a.getIdg() + 
                        " and atleta.ida=partecipazione.idatl and specialita.ids=partecipazione.idsp"); 
            
                //Console.scriviStringa(s);           
                ResultSet rs1 = st1.executeQuery(s);
                
                while(rs1.next()) {
                    //spec.setSesso(rs1.getString("nome"));
                    spec.setTipoSpecialita(rs1.getString("tipospecialita"));   
                    spec.setIDs(rs1.getInt("ids"));
                    atl.setNome(rs1.getString("nome"));
                    atl.setCognome(rs1.getString("cognome"));
                    atl.setIDa(rs1.getInt("ida"));
                    Console.scriviStringa("Specialità: " + spec.getTipoSpecialita()
                            + " Nome: " + atl.getNome() + " "
                            + atl.getCognome());
                    
                    /********************************************************/
                    //Vuoi cancellare  questa gara praticata dall'atleta?
                    
                    
                    Console.scriviStringa("Vuoi cancellare  questa gara "
                            + "praticata dall'atleta? " );
                    Console.scriviStringa("s o n");
                        char c1 = 'p';
                        while(c1 != 's' && c1 != 'n'){
                            c1 = Console.leggiCarattere();
                                                
                            if (c1 == 's'){
                                String s1 = new String("delete from"
                                    + " partecipazione where partecipazione.idgar="
                                    + a.getIdg() + " and partecipazione.idatl=" 
                                    + atl.getIDa() + " and partecipazione.idsp="
                                    + spec.getIDs());
                                st.executeUpdate(s1);
                            
                            }
                        }
                        
                    
                }   
            }
        }      
        
        catch (SQLException se){
            Console.scriviStringa(" SQL exception " +se.getMessage());
        }    
    
    }
}

    