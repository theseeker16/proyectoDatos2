/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author jgm16
 */
public class Arco {
    private int peso;
    private Arco siguiente;
    private Vertice punteroVertice;

    public Arco(int ppeso,Vertice ppunteroVertice){
        this.setPeso(ppeso);
        this.setPunteroVertice(ppunteroVertice);
        siguiente = null;
        
    }
    public Arco(){
        
    }
    public int getPeso() {
        return peso;
    }

    public Arco getSiguiente() {
        return siguiente;
    }

    public Vertice getPunteroVertice() {
        return punteroVertice;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public void setSiguiente(Arco siguiente) {
        this.siguiente = siguiente;
    }

    public void setPunteroVertice(Vertice punteroVertice) {
        this.punteroVertice = punteroVertice;
    }
    
    
    
    
}
