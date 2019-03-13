import java.sql.*;
import java.util.*;

public class MenuInsDati {
    private int indice;
    private Connection con;
    
    public MenuInsDati (Connection con){
        this.con=con;
        while (true){
            Console.scriviStringa("\n  MENU SELEZIONE DATI");
            Console.scriviStringa("1) Inserisci nuovo atleta \n2) "
                + "Inserisci nuova gara\n3) Inserisci nuova specialita'\n"
                + "4) Inserisci a quale gara ha partecipato un atleta\n"
                    + "   e con quale risultato\n"
                    + "5) Torna al menù principale\n");
            indice = Console.leggiIntero();
            switch (indice){
                case 1:
                    inserisciDatiAtleta();
                    break;
                case 2:
                    inserisciDatiGara();
                    break;    
                case 3:
                    inserisciDatiSpecialita();
                    break;
                case 4:
                    inserisciPartecipazione();
                    break;    
                case 5:
                    Menu menu = new Menu(con);
                    break;
                default:
                    continue;
            }
        }
    }
        
    public void inserisciDatiAtleta(){
        boolean b=false;
        String s;
        int i;
        char c = 'p';
        char c1 = 'p';
        char[] vet; 
        do{
            Atleta a = new Atleta();
            Specialita sp = new Specialita ();        
            try {
                // inserimento dati nella tabella Atleta
                Console.scriviStringa("Nome");
                s = Console.leggiStringa();                
                a.setNome(s);
                Console.scriviStringa("Cognome");
                s = Console.leggiStringa();
                a.setCognome(s);
                Console.scriviStringa("Nazionalità");
                s = Console.leggiStringa();
                a.setNazionalita(s);
                Console.scriviStringa("ida");
                i = Console.leggiIntero();
                a.setIDa(i);
                int res = 0;            
                String datoDaInserire = new String("insert into atleta "
                    + "(nome,cognome,nazionalita,ida ) values ( " +  "'"
                    + a.getNome() + "','" +  a.getCognome() + "','" + 
                    a.getNazionalita() + "'," + a.getIDa() + ")");
                Console.scriviStringa(datoDaInserire);
                Statement st = con.createStatement();
                res = st.executeUpdate(datoDaInserire);
                Console.scriviStringa("Inserisci ora sesso e specialità praticate"
                    + " dall'atleta");
                res = 0;  
                //inserimento dati nella tabella atlespe
                // 1)  scrivi su console se sesso è M o F
                while(c != 'M' && c != 'F'){
                    Console.scriviStringa("sesso M o F");
                    c = Console.leggiCarattere();
                }
                // 2) scansiona riga per riga la tabella specialità
                Statement st1 = con.createStatement();
                ResultSet rs = st1.executeQuery("select sesso,tipospecialita,ids from specialita");
                while(rs.next()){
                    //leggi l'attributo sesso e tipospecialità dalla tabella specialità e scrivilo
                    //nella classe specialità
                    vet=rs.getString("sesso").toCharArray();
                    sp.setSesso(vet[0]);
                    sp.setTipoSpecialita(rs.getString("tipospecialita"));
                    sp.setIDs(rs.getInt("ids"));
                    //confronta sesso della tabella specialità con il valore che ho
                    //scritto sulla console
                    if (vet[0] == c){
                        Console.scriviStringa("Questo atleta partecipa a questa "
                            + "specialità?   " + sp.getTipoSpecialita());
                        Console.scriviStringa("s o n");
                        c1 = 'p';
                        while(c1 != 's' && c1 != 'n'){
                            c1 = Console.leggiCarattere();
                        }
                        
                        if (c1 == 's'){
                            String datoDaInserire1 = new String("insert into atlespe"
                                + " (idatleta,idspecialita) values (" + 
                                + a.getIDa() + "," +  sp.getIDs() + ")");
                            Console.scriviStringa(datoDaInserire1);
                            Statement st2 = con.createStatement();
                            res = st2.executeUpdate(datoDaInserire1);
                        }
                        b=false;
                    }
                }
            }
            catch (InputMismatchException ime){
                Console.scriviStringa("java exception " +ime.getMessage());
                //la stringa che genera l'eccezione rimane in memoria
                //l'istruzione successiva serve a svuotare la memoria
                Console.leggiStringa();
                b=true;
            }
            catch (SQLException se){
                Console.scriviStringa("SQL exception " +se.getMessage());
                b=true;
            }
            
        }while(b);        
    } 
    
    public void inserisciDatiGara(){
        boolean b=false;
        String s;
        int i;
        char c = 'p';
        char c1 = 'p';
        char[] vet; 
        Gara a = new Gara();
        Specialita sp = new Specialita ();
        do{
            try {
                // inserimento dati nella tabella Gara
                Console.scriviStringa("Località");
                s = Console.leggiStringa();
                a.setLocalita(s);
                Console.scriviStringa("Dislivello");
                i = Console.leggiIntero();
                a.setDislivello(i);
                Console.scriviStringa("lunghezza");
                i = Console.leggiIntero();
                a.setLunghezza(i);
                Console.scriviStringa("Numero di porte");
                i = Console.leggiIntero();
                a.setNumeroPorte(i);
                Console.scriviStringa("Data della gara:");
                Console.scriviStringa("giorno");
                i = Console.leggiIntero();
                a.setGiorno(i);
                Console.scriviStringa("mese");
                i = Console.leggiIntero();
                a.setMese(i);
                Console.scriviStringa("anno");
                i = Console.leggiIntero();
                a.setAnno(i);
                Console.scriviStringa("idg");
                i = Console.leggiIntero();
                a.setIdg(i);
                int res = 0;            
                String datoDaInserire = new String("insert into gara "
                    + "(localita,dislivello,lunghezza,numeroporte,giorno,mese,"
                    + "anno,idg ) values ( " +  "'" + a.getLocalita() + "'," + 
                    a.getDislivello() + "," + a.getLunghezza() + "," 
                    + a.getNumeroPorte() +  "," + a.getGiorno() + "," +
                    a.getMese() + "," + a.getAnno() + "," + a.getIdg() + ")"); 
                //Console.scriviStringa("------------------------------");
                Console.scriviStringa(datoDaInserire);
                Statement st = con.createStatement();
                res = st.executeUpdate(datoDaInserire);
                res = 0;  
                //inserimento dati nella tabella gaspe
                // 1)  scrivi su console se sesso è M o F
                while(c != 'M' && c != 'F'){
                    Console.scriviStringa("sesso M o F");
                    c = Console.leggiCarattere();
                }
                // 2) scansiona riga per riga la tabella specialità
                Statement st1 = con.createStatement();
                ResultSet rs = st1.executeQuery("select sesso,tipospecialita,ids from specialita");
                while(rs.next()){
                    //leggi l'attributo sesso e tipospecialità dalla tabella specialità e scrivilo
                    //nella classe specialità
                    vet=rs.getString("sesso").toCharArray(); 
                    sp.setSesso(vet[0]);
                    sp.setTipoSpecialita(rs.getString("tipospecialita"));
                    sp.setIDs(rs.getInt("ids"));
                    //confronta sesso della tabella specialità con il valore che ho
                    //scritto sulla console
                    if (vet[0] == c){
                        Console.scriviStringa(" Per questa gara è prevista la "
                          + "seguente specialità:  " + sp.getTipoSpecialita());
                        Console.scriviStringa("s o n");
                        c1 = 'p';
                        while(c1 != 's' && c1 != 'n'){
                            c1 = Console.leggiCarattere();
                        }
                        if (c1 == 's'){
                            String datoDaInserire1 = new String("insert into gaspe (idgar,idspec) values (" + 
                                + a.getIdg() + "," +  sp.getIDs() + ")");
                            Console.scriviStringa(datoDaInserire1);
                            Statement st2 = con.createStatement();
                            res = st2.executeUpdate(datoDaInserire1);
                        }
                    }
                }
            } 
            catch (InputMismatchException ime){
                Console.scriviStringa("java exception " +ime.getMessage());
                //la stringa che genera l'eccezione rimane in memoria
                //l'istruzione successiva serve a svuotare la memoria
                Console.leggiStringa();
                b=true;
            }        
            catch (SQLException se){
                Console.scriviStringa(" SQL exception " +se.getMessage());
                
            }
            
        }while(b);   
    }
    
    public void inserisciDatiSpecialita(){
        boolean b=false;
        String s;
        char c;
        int i;
        Specialita a = new Specialita();
        do{
            c='p';
            try {                
                while(c != 'M' && c != 'F'){
                    Console.scriviStringa("sesso M o F");
                    c = Console.leggiCarattere();                    
                }
                a.setSesso(c);
                Console.scriviStringa("tipo della specialità");
                s = Console.leggiStringa();
                a.setTipoSpecialita(s); 
                Console.scriviStringa("ids");
                i = Console.leggiIntero();
                a.setIDs(i);   
                int res = 0;
                //inserisci nuova specialita nel database
                String datoDaInserire = new String("insert into specialita "
                    + "(sesso,tipospecialita,ids ) values ( " +  "'"
                    + a.getSesso() + "','" +  a.getTipoSpecialita() + "'," + 
                    a.getIDs() + ")");
                Console.scriviStringa(datoDaInserire);
                Statement st = con.createStatement();
                res = st.executeUpdate(datoDaInserire);
                b=false;
                //chiedi se vuol fare la scansione di tutti gli atleti
                //per inserire la nuova specialità
                
                
            }
       
            catch (SQLException se){
                Console.scriviStringa(" SQL exception " +se.getMessage());
                b=true;
            }
            catch (InputMismatchException ime){
                Console.scriviStringa("java exception " +ime.getMessage());
                //la stringa che genera l'eccezione rimane in memoria
                //l'istruzione successiva serve a svuotare la memoria
                Console.leggiStringa();
                b=true;
            }
        }while(b);
    }
    
    public boolean controllo(Atleta a,Specialita s,Gara g){
        /*questa funzione controlla che nella tabella partecipazione
         non esistano tuple con valori di idgar,idatl,idspl uguali,
         se tali valori non esistono restituisce true*/
        boolean b;
        int idgar;
        int idatl;
        int idsp;
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select idgar,idatl,idsp"
                    + " from partecipazione");
            while(rs.next()) {
                idgar=rs.getInt("idgar");
                idatl=rs.getInt("idatl");
                idsp=rs.getInt("idsp");
                if(idgar==g.getIdg()&&idatl==a.getIDa()&&idsp==s.getIDs()){
                    return b=false;                    
                }else{
                    b=true;
                }
                
            }
            
            
        }
        catch (SQLException se){
            Console.scriviStringa(" SQL exception " +se.getMessage());
        }
        return b=true;
        
    }
    
    
    
    
    
    public void inserisciPartecipazione(){        
        int punteggio;
        int ordinepartenza;
        int ordinearrivo;
        char[] vett;
        Atleta a = new Atleta();
        Specialita spec = new Specialita();
        Gara g = new Gara ();
        
        /****************************************/
        //Seleziona un atleta dalla tabella atleta
        
        try{
            
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select nome,cognome,"
                    + "ida from atleta");
            while(rs.next()) {
                a.setNome(rs.getString("nome")); 
                a.setCognome(rs.getString("cognome"));               
                a.setIDa(rs.getInt("ida"));
                //Console.scriviStringa("IDa " + a.getIDa());
                Console.scriviStringa("");
                Console.scriviStringa("------------------------------");
                Console.scriviStringa("ATLETA");
                Console.scriviStringa(a.getNome() + " " + a.getCognome());
                
                /***********************************************/
                //seleziona una specialita
                
                Statement st1 = con.createStatement();
                String s1 = new String("select distinct specialita.sesso, specialita.tipospecialita, "
                        + " specialita.ids from specialita,"
                    + " atleta, atlespe  where " + a.getIDa() + 
                        " =atlespe.idatleta and "
                    + "atlespe.idspecialita = specialita.ids" );
                //Console.scriviStringa(s);
                ResultSet rs1 = st1.executeQuery(s1);
                boolean b = true;
                while(rs1.next()) {
                    //scrivi il sesso dell'atleta una sola volta
                    if (b){
                        //Console.scriviStringa("IDs " + spec.getIDs());
                        vett = rs1.getString("sesso").toCharArray();
                        String str = new String ("Sesso: " + vett [0] );
                                
                        Console.scriviStringa(str);
                        b = false;
                    }         
                    
                    //scrivi una specialità praticata dall'atleta
                    spec.setIDs(rs1.getInt("ids"));
                    //Console.scriviStringa("IDs1 " + spec.getIDs());
                    spec.setTipoSpecialita(rs1.getString("tipospecialita"));
                    //Console.scriviStringa(spec.getTipoSpecialita());
                    //Console.scriviStringa("");
                    
                    /*********************************************/
                    //seleziono una gara                  
                    
                    Statement st2 = con.createStatement();
                    String s2 = new String("select gara.localita, gara.idg from gaspe, gara"
                            + " where gaspe.idspec=" + spec.getIDs() + " and"
                            + " gara.idg=gaspe.idgar");
                    //Console.scriviStringa(s2);
                    ResultSet rs2 = st2.executeQuery(s2);
                    while(rs2.next()) {
                        g.setLocalita(rs2.getString("localita"));
                        g.setIdg(rs2.getInt("idg"));
                        
                        /*Chiamo la funzione controllo per verificare che i 
                         valori di IDatleta,IDgara,IDspecialita non compaiono 
                          già  nella tabella PARTECIPAZIONE*/
                        
                        //Console.scriviStringa(a.getIDa() + " " + spec.getIDs() + " " + g.getIdg());
                        //Console.scriviBooleano(controllo(a,spec,g)); 
                        if(controllo(a,spec,g)){                       
                                    
                        
                            Console.scriviStringa("L'atleta " + a.getNome() +
                                " " + a.getCognome() + " ha partecipato alla gara "
                                + "in località " + g.getLocalita()
                                 + " specialità " +  spec.getTipoSpecialita() + "? " );
                        
                            /********************************************/
                            // chiedi se l'atleta selezionato sopra partecipa
                            //oppure no alla gara selezionata 
                        
                            Console.scriviStringa("s o n");
                            char c1 = 'p';
                            while(c1 != 's' && c1 != 'n'){
                                c1 = Console.leggiCarattere();
                            }
                            
                            /*****************************************/
                            // Sì allora inserisci atleta (il suo ID) nella 
                            // tabella partecipazione
                            // qui inserisco i dati della associazione partecipazione 
                            if (c1 == 's'){
                                    Console.scriviStringa("punteggio");
                                    punteggio = Console.leggiIntero();
                                    Console.scriviStringa("atleta:posizione alla partenza");
                                    ordinepartenza = Console.leggiIntero();
                                    Console.scriviStringa("atleta:posizione all'arrivo");
                                    ordinearrivo = Console.leggiIntero();
                                    String datoDaInserire = new String ("insert into "
                                        + "partecipazione (punteggio,ordinepartenza,"
                                        + "ordinearrivo,idgar,idatl,idsp)"
                                        + " values (" + punteggio + "," + ordinepartenza +
                                        "," + ordinearrivo + "," + g.getIdg() + "," + a.getIDa() +
                                        "," + spec.getIDs() +   ")");
                                    Console.scriviStringa(datoDaInserire);
                                    Statement st3 = con.createStatement();
                                    st3.executeUpdate(datoDaInserire);
                                    Console.scriviStringa("");
                              }
                                
                            
                        }else{
                            break;
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
