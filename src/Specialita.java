
public class Specialita {
    private char sesso;
    private String tipoSpecialita;
    private int ids;
    
    public char getSesso(){
        return sesso;
    }
    
    public String getTipoSpecialita () {
        return tipoSpecialita;
    } 
    
    public int getIDs(){
        return ids;
    }
    
    public void setSesso (char sesso){
        this.sesso=sesso;
    }
    
    public void setTipoSpecialita (String tipoSpecialita){
        this.tipoSpecialita=tipoSpecialita;
    }
    
    public void setIDs(int ids){
        this.ids = ids;
    }
    
    public String toString(){
        return ids + "  " + sesso + "  " +  tipoSpecialita;
    }
}
