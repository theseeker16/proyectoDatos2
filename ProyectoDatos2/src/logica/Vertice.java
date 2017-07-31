/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;

/**
 *
 * @author jgm16
 */
public class Vertice {
    private int numeroVertice;
    private String etiqueta;
    private ArrayList<Arco> predecesor;
    private ArrayList<Arco>  sucesor;
    
    public Vertice(int pnumVertice, String petiqueta){
        this.setEtiqueta(petiqueta);
        this.setNumeroVertice(pnumVertice);
        predecesor=null;
        sucesor=null;
    }
    public int getNumeroVertice() {
        return numeroVertice;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public ArrayList<Arco> getPredecesor() {
        return predecesor;
    }

    public ArrayList<Arco> getSucesor() {
        return sucesor;
    }

    public void setNumeroVertice(int numeroVertice) {
        this.numeroVertice = numeroVertice;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public void setPredecesor(Arco ppredecesor) {
        if(predecesor == null){
            this.predecesor = new ArrayList<Arco>();
            this.predecesor.add(ppredecesor);
        }else{
            this.predecesor.add(ppredecesor);
        }
    }

    public void setSucesor(Arco psucesor) {
        
        if(sucesor == null){
            this.sucesor = new ArrayList<Arco>();
            this.sucesor.add(psucesor);
        }else{
            this.sucesor.add(psucesor);
        }
    }
}
