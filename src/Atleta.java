/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sgh
 */
public class Atleta {
    private String nome = null;
    private String cognome = null;
    private String nazionalita = null;
    private int ida ;
    
    
    public String getNome(){
        return nome;
    }
    
    public String getCognome(){
        return cognome;
    }
    
    public int getIDa(){
        return ida;
    }
    
    public String getNazionalita(){
        return nazionalita;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public void setCognome(String cognome){
        this.cognome = cognome;
    }
    
    public void setNazionalita(String nazionalita){
        this.nazionalita= nazionalita;
    }
    
    public void setIDa(int ida){
        this.ida = ida;
    }
    
    public String toString(){
        return "codice: " + ida + "    nome: " +  nome +  "    cognome: " + cognome + "    nazionalità: " + nazionalita ;
    }
    
}
