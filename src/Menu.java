import java.sql.*;
import java.util.*;

public class Menu {
    Connection con;
    int indice;
    
    public Menu (Connection con){
        this.con = con;
        boolean b=false;
        do{
          try{
            while(true){
                Console.scriviStringa("\n MENU PRINCIPALE");
                Console.scriviStringa("1) Leggi dati \n2) Inserimento dati"
                    + "\n3) Modifica dati\n4) Cancella dati\n5)"
                        + " Effettua richieste \n6) Esci\n");
                indice = Console.leggiIntero();
                switch (indice){
                    case 1:
                        MenuLeggiDati menuLeggiDati = new MenuLeggiDati(con);                        
                        break;
                    case 2:
                        MenuInsDati menuInsDati = new MenuInsDati(con);
                        break;
                    case 3:
                        MenuModificaDati menuModificaDati=new MenuModificaDati(con);
                        break;
                    case 4:
                        MenuCancellaDati menuCancellaDati=new MenuCancellaDati(con);
                        break;
                    case 5:
                        MenuInterrogazioni menuInterrogazioni=new MenuInterrogazioni(con);
                        break;                         
                       
                    case 6:
                        uscita();
                        break;
                    default:
                        continue;
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
      }while(b);
    }
    
    public void uscita(){
        System.exit(0);
    }
}



