
public class Main {
    public static void main(String[] args){
        Connessione c = new Connessione();
        c.effettuaConnessione();
        Menu menu = new Menu(c.getConnessione());
    }
}

