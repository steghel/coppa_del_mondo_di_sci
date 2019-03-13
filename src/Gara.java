import java.sql.*;
import java.util.*;

public class Gara {
    private int idg;
    private String localita;
    private int dislivello;
    private int lunghezza;
    private int numeroPorte;
    private int giorno;
    private int mese;
    private int anno;
    private java.sql.Date data;
    
    
    public int getIdg(){
        return idg;
    }
    
    public String getLocalita(){
        return localita;
    }
    
    public int getDislivello(){
        return dislivello;
    }
    
    public int getLunghezza(){
        return lunghezza;
    }
    
    public int getNumeroPorte(){
        return numeroPorte;
    }
    
    public int getGiorno(){
        return giorno;
    }
    
    public int getMese(){
        return mese;
    }
    
    public int getAnno(){
        return anno;
    }
       
    public void setIdg(int idg){
        this.idg = idg;
    }
    
    public void setLocalita(String localita){
        this.localita = localita;
    }
    
    public void setDislivello(int dislivello){
        this.dislivello = dislivello;
    }
    
    public void setLunghezza(int lunghezza){
        this.lunghezza = lunghezza;
    }
    
    public void setNumeroPorte(int numeroPorte){
        this.numeroPorte = numeroPorte;
    }
    
    public void setGiorno(int giorno){
        this.giorno = giorno;
    }
    
    public void setMese(int mese){
        this.mese = mese;
    }
    
    public void setAnno(int anno){
        this.anno = anno;
    }
    
    public String toString(){
        return "codice: " + idg + "    Località: "  +  localita + 
               "    dislivello: " + dislivello + "   lunghezza: " + lunghezza + 
               "   numero di porte: " + numeroPorte + "    Data: " +  giorno +  
               "/" + mese + "/" + anno;
    }
}
