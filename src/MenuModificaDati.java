

import java.sql.*;
import java.util.*;

public class MenuModificaDati {
    int indice;
    Connection con;
    
    public MenuModificaDati (Connection con){
        this.con=con;
        while (true){
            Console.scriviStringa("\n  MENU MODIFICA DATI");
            Console.scriviStringa("1) Modifica dati atleta \n2) "
                + "modifica dati gara\n3) Torna al menù principale\n");
            indice = Console.leggiIntero();
            switch (indice){
                case 1:
                    
                    modificaDatiAtleta();
                    break;
                case 2:
                    
                    modificaDatiGara();
                    break;                
                case 3:
                    Menu menu = new Menu(con);
                    break;
                default:
                    continue;
            }
        }
    }
    
    public void MenuModificaDatiAtleta (Atleta a,char sesso){
        this.con=con;
        //while(true){
                Console.scriviStringa("\nDell'atleta " + a.getNome() + " " +
                        a.getCognome() + " cosa vuoi modificare?");
                Console.scriviStringa("1) Nome \n2) "
                        + "Cognome\n3) Nazionalità\n"
                        + "4) inserisci nuove specialità a cui partecipa l'atleta"
                        + "\n5) Visualizza il prossimo atleta\n6) Torna al menù principal");
                 int i = Console.leggiIntero();
                 switch (i){
                        case 1:
                            modificaNome(a.getIDa());
                            Console.scriviStringa("\n");
                            break;
                        
                        case 2:
                            modificaCognome(a.getIDa());
                            Console.scriviStringa("\n");
                            break;
                            
                        case 3:
                            modificaNazionalita(a.getIDa());
                            Console.scriviStringa("\n");
                            break;
                            
                        case 4:
                            nuoveSpecialità(a,a.getIDa(),sesso);
                            Console.scriviStringa("\n");
                            break;           
                          
                        case 5:                            
                            //visualizza atleta successivo
                            Console.scriviStringa("\n");
                            break;
                            
                       case 6:
                            Menu menu = new Menu(con);
                            break;                                             
                  }
    }
    
    public void MenuModificaDatiGara (Gara a,char sesso){
        this.con=con;
        //while(true){
                Console.scriviStringa("\nDella gara svolta in località " + a.getLocalita()
                        + " cosa vuoi modificare?");
                Console.scriviStringa("1) Localita \n2) "
                        + "Dislivello\n3) Lunghezza\n"
                        + "4) NumeroPorte\n5) Giorno\n6) Mese\n7) Anno"
                        + "\n8) Inserisci nuove specialità della gara\n9) "
                        + "Visualizza la prossima gara\n10) Torna al menù principale");
                 int i = Console.leggiIntero();
                 switch (i){
                        case 1:
                            modificaLocalita(a.getIdg());
                            Console.scriviStringa("\n");
                            break;
                        
                        case 2:
                            modificaDislivello(a.getIdg());
                            Console.scriviStringa("\n");
                            break;
                            
                        case 3:
                            modificaLunghezza(a.getIdg());
                            Console.scriviStringa("\n");
                            break;
                            
                        case 4:
                            modificaNumeroPorte(a.getIdg());
                            Console.scriviStringa("\n");
                            break;
                            
                        case 5:
                            modificaGiorno(a.getIdg());
                            Console.scriviStringa("\n");
                            break;
                            
                        case 6:
                            modificaMese(a.getIdg());
                            Console.scriviStringa("\n");
                            break;
                            
                        case 7:
                            modificaAnno(a.getIdg());
                            Console.scriviStringa("\n");
                            break;    
                        
                        case 8:
                            nuoveSpecialitaGara(a,a.getIdg(),sesso);
                            Console.scriviStringa("\n");
                            break;
                            
                       case 9:                            
                            break;
                            
                       case 10:
                            Menu menu = new Menu(con);
                            break;                                             
                  }
    }
    
    public void modificaDatiAtleta(){
        char[] vett;
        char sesso='p';
        Atleta a = new Atleta();
        Specialita spe = new Specialita();
        
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
                Console.scriviStringa("DATI DELL'ATLETA");
                Console.scriviStringa(a.toString()); 
                
                //scansione della tabella specialita
                Statement st1 = con.createStatement();
                String s = new String("select specialita.sesso,specialita.tipospecialita,specialita.ids from specialita,"
                    + " atleta, atlespe  where atleta.ida = " + a.getIDa() + " and atleta.ida=atlespe.idatleta and "
                    + "atlespe.idspecialita = specialita.ids" );
            
                //Console.scriviStringa(s);
                ResultSet rs1 = st1.executeQuery(s);
                boolean b = true;
                while(rs1.next()) {
                                
                    //scrivi il sesso dell'atleta una sola volta
                    if (b){
                        vett = rs1.getString("sesso").toCharArray();
                        sesso = vett[0];
                        String str = new String ("Sesso: " + vett [0] + 
                                "\nSpecialità praticate dall'atleta: " );
                        Console.scriviStringa(str);
                        b = false;
                    }
                    //scrivi tutte le specialità praticate dall'atleta
                    Console.scriviStringa(rs1.getString("tipospecialita"));
                }    
                MenuModificaDatiAtleta(a,sesso);
            }           
        }
        catch (SQLException se){
            Console.scriviStringa(" SQL exception " +se.getMessage());
        }
    }
    
    public void modificaNome(int ida){
       try{           
            Console.scriviStringa("Scrivi il nuovo nome:");
            String nuovoNome = Console.leggiStringa();
            Statement st1 = con.createStatement();
            String s = new String("update atleta set nome = '" + nuovoNome + "'"
            + " where ida=" + ida );
            //Console.scriviStringa(s);            
            st1.executeUpdate(s);
       }
            
       catch (SQLException se){
            Console.scriviStringa(" SQL exception " +se.getMessage());
       }
            
    }
    
    
        
    public void modificaCognome(int ida){
       try{           
            Console.scriviStringa("Scrivi il nuovo cognome:");
            String nuovoCognome = Console.leggiStringa();
            Statement st1 = con.createStatement();
            String s = new String("update atleta set cognome = '" + nuovoCognome + "'"
            + " where ida=" + ida );
            //Console.scriviStringa(s);            
            st1.executeUpdate(s);
       }
            
       catch (SQLException se){
            Console.scriviStringa(" SQL exception " +se.getMessage());
       }
            
    }
    
    public void modificaNazionalita(int ida){
       try{           
            Console.scriviStringa("Scrivi la nuova nazionalità:");
            String nuovaNazionalita = Console.leggiStringa();
            Statement st1 = con.createStatement();
            String s = new String("update atleta set cognome = '" + nuovaNazionalita + "'"
            + " where ida=" + ida );
            //Console.scriviStringa(s);            
            st1.executeUpdate(s);
       }
            
       catch (SQLException se){
            Console.scriviStringa(" SQL exception " +se.getMessage());
       }
            
    }
    
    public void nuoveSpecialità(Atleta a,int ida,char sesso){
        int ids=1;
        try{  
            
            String s = new String("select specialita.tipospecialita,specialita.sesso,specialita.ids  from"
                    + " specialita where specialita.sesso='" + sesso + "' and specialita.ids not in "
                    + " (select specialita.ids from atleta,atlespe,"
                    + "specialita where atleta.ida=" + ida + " and atleta.ida=atlespe.idatleta and "
                    + "atlespe.idspecialita=specialita.ids)");
            //Console.scriviStringa(s); 
            Statement st1 = con.createStatement();
            ResultSet rs2 = st1.executeQuery(s);
            //Console.scriviStringa("prima del while di rs2");
            while(rs2.next()) {
                //Console.scriviStringa("dopo il while di rs2");
                ids = rs2.getInt("ids");
               
                /*Console.scriviStringa(rs2.getString("tipospecialita"));*/
            
                /*Console.scriviStringa("Questo atleta partecipa a questa "
                            + "specialità?   " );*/
                Console.scriviStringa("L'atleta " + a.getNome() + " " + a.getCognome()
                        +  " partecipa alla specialità " + rs2.getString("tipospecialita") +
                        "?");
                Console.scriviStringa("s o n");
                char c1 = 'p';
                while(c1 != 's' && c1 != 'n'){
                    c1 = Console.leggiCarattere();
                }                    
                if (c1 == 's'){
                    String datoDaInserire1 = new String("insert into atlespe"
                        + " (idatleta,idspecialita) values ("  
                        + ida + "," + ids + ")");
                    Console.scriviStringa(datoDaInserire1);
                    Statement st2 = con.createStatement();
                    st2.executeUpdate(datoDaInserire1);
                    Console.scriviStringa("");
                    Console.scriviStringa("");
                }
            }
        }
                
                
        catch (SQLException se){
            Console.scriviStringa(" SQL exception " +se.getMessage());
       }
        
    }
    
    public void modificaDatiGara(){
        char[] vett;
        char sesso='p';
        Gara a = new Gara();
        Specialita spe = new Specialita();
        try{
            //scansione della tabella atleta
            Statement st2 = con.createStatement();
            String strg = new String("select localita,dislivello,lunghezza,numeroporte,"
                    + "giorno,mese,anno,idg from gara");
            
            ResultSet rs = st2.executeQuery(strg);
            while(rs.next()) {
                a.setLocalita(rs.getString("localita")); 
                a.setDislivello(rs.getInt("dislivello")); 
                a.setLunghezza(rs.getInt("lunghezza"));
                a.setNumeroPorte(rs.getInt("numeroporte"));
                a.setGiorno(rs.getInt("giorno"));
                a.setGiorno(rs.getInt("mese"));
                a.setAnno(rs.getInt("anno"));                
                a.setIdg(rs.getInt("idg"));
                Console.scriviStringa("DATI DELLA GARA");
                Console.scriviStringa(a.toString()); 
                
                //scansione della tabella specialita
                Statement st1 = con.createStatement();
                String s = new String("select specialita.sesso,specialita.tipospecialita,specialita.ids from specialita,"
                    + " gara, gaspe  where gara.idg = " + a.getIdg() + " and gara.idg=gaspe.idgar and "
                    + "gaspe.idspec = specialita.ids" );
            
                //Console.scriviStringa(s);
                ResultSet rs1 = st1.executeQuery(s);
                boolean b = true;
                while(rs1.next()) {
                                
                    //scrivi il sesso dell'atleta una sola volta
                    if (b){
                        vett = rs1.getString("sesso").toCharArray();
                        sesso = vett[0];
                        String str = new String ("Sesso: " + vett [0] + 
                                "\nSpecialità: " );
                        Console.scriviStringa(str);
                        b = false;
                    }
                    //scrivi tutte le specialità praticate dall'atleta
                    Console.scriviStringa(rs1.getString("tipospecialita"));
                }    
                MenuModificaDatiGara(a,sesso);
            }           
        }
        catch (SQLException se){
            Console.scriviStringa(" SQL exception " +se.getMessage());
        }
    }
    
    public void modificaLocalita(int idg){
       try{           
            Console.scriviStringa("Scrivi la nuova località:");
            String nuovaLocalita = Console.leggiStringa();
            Statement st1 = con.createStatement();
            String s = new String("update gara set localita = '" + nuovaLocalita + "'"
            + " where idg=" + idg );
            //Console.scriviStringa(s);            
            st1.executeUpdate(s);
       }
            
       catch (SQLException se){
            Console.scriviStringa(" SQL exception " +se.getMessage());
       }
            
    }
    
    public void modificaDislivello(int idg){
       try{           
            Console.scriviStringa("Scrivi il nuovo dislivello:");
            String nuovoDislivello = Console.leggiStringa();
            Statement st1 = con.createStatement();
            String s = new String("update gara set dislivello = '" + nuovoDislivello + "'"
            + " where idg=" + idg );
            //Console.scriviStringa(s);            
            st1.executeUpdate(s);
       }
            
       catch (SQLException se){
            Console.scriviStringa(" SQL exception " +se.getMessage());
       }
            
    }
    
    public void modificaLunghezza(int idg){
       try{           
            Console.scriviStringa("Scrivi la nuova lunghezza:");
            String nuovaLunghezza = Console.leggiStringa();
            Statement st1 = con.createStatement();
            String s = new String("update gara set lunghezza = '" + nuovaLunghezza + "'"
            + " where idg=" + idg );
            //Console.scriviStringa(s);            
            st1.executeUpdate(s);
       }
            
       catch (SQLException se){
            Console.scriviStringa(" SQL exception " +se.getMessage());
       }
            
    }
    
    public void modificaNumeroPorte(int idg){
       try{           
            Console.scriviStringa("Scrivi il nuovo numero delle porte:");
            String nuovoNumeroPorte = Console.leggiStringa();
            Statement st1 = con.createStatement();
            String s = new String("update gara set numeroporte = '" + nuovoNumeroPorte + "'"
            + " where idg=" + idg );
            //Console.scriviStringa(s);            
            st1.executeUpdate(s);
       }
            
       catch (SQLException se){
            Console.scriviStringa(" SQL exception " +se.getMessage());
       }
            
    }
    
    public void modificaGiorno(int idg){
       try{           
            Console.scriviStringa("Scrivi il nuovo giorno:");
            String nuovoGiorno = Console.leggiStringa();
            Statement st1 = con.createStatement();
            String s = new String("update gara set giorno = '" + nuovoGiorno + "'"
            + " where idg=" + idg );
            //Console.scriviStringa(s);            
            st1.executeUpdate(s);
       }
            
       catch (SQLException se){
            Console.scriviStringa(" SQL exception " +se.getMessage());
       }
            
    }
    
    public void modificaMese(int idg){
       try{           
            Console.scriviStringa("Scrivi il nuovo mese:");
            String nuovoMese = Console.leggiStringa();
            Statement st1 = con.createStatement();
            String s = new String("update gara set mese = '" + nuovoMese + "'"
            + " where idg=" + idg );
            //Console.scriviStringa(s);            
            st1.executeUpdate(s);
       }
            
       catch (SQLException se){
            Console.scriviStringa(" SQL exception " +se.getMessage());
       }
            
    }
    
    public void modificaAnno(int idg){
       try{           
            Console.scriviStringa("Scrivi il nuovo anno:");
            String nuovoAnno = Console.leggiStringa();
            Statement st1 = con.createStatement();
            String s = new String("update gara set anno = '" + nuovoAnno + "'"
            + " where idg=" + idg );
            //Console.scriviStringa(s);            
            st1.executeUpdate(s);
       }
            
       catch (SQLException se){
            Console.scriviStringa(" SQL exception " +se.getMessage());
       }
            
    }
    
    
    public void nuoveSpecialitaGara(Gara a,int idg,char sesso){
        int ids=1;
        try{  
            
            String s = new String("select specialita.tipospecialita,specialita.sesso,specialita.ids  from"
                    + " specialita where specialita.sesso='" + sesso + "' and specialita.ids not in "
                    + " (select specialita.ids from gara,gaspe,"
                    + "specialita where gara.idg=" + idg + " and gara.idg=gaspe.idgar and "
                    + "gaspe.idspec=specialita.ids)");
            //Console.scriviStringa(s); 
            Statement st1 = con.createStatement();
            ResultSet rs2 = st1.executeQuery(s);
            //Console.scriviStringa("prima del while di rs2");
            while(rs2.next()) {
                //Console.scriviStringa("dopo il while di rs2");
                ids = rs2.getInt("ids");
               
                //Console.scriviStringa(rs2.getString("tipospecialita"));
            
                Console.scriviStringa("Nella gara fatta in località " + a.getLocalita()
                        + " esiste la specialità " + rs2.getString("tipospecialita")
                        + "?");
                Console.scriviStringa("s o n");
                char c1 = 'p';
                while(c1 != 's' && c1 != 'n'){
                    c1 = Console.leggiCarattere();
                }                    
                if (c1 == 's'){
                    String datoDaInserire1 = new String("insert into gaspe"
                        + " (idgar,idspec) values ("  
                        + idg + "," + ids + ")");
                    Console.scriviStringa(datoDaInserire1);
                    Statement st2 = con.createStatement();
                    st2.executeUpdate(datoDaInserire1);
                    Console.scriviStringa("");
                    Console.scriviStringa("");
                }
            }
        }
                
                
        catch (SQLException se){
            Console.scriviStringa(" SQL exception " +se.getMessage());
       }
        
    }
    
}
    


