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
public class HashNode {
    
    Vertice vertice;
    HashNode sig;
    
    public HashNode(Vertice vertice) {
        this.setVertice(vertice);
    }

    public Vertice getVertice() {
        return vertice;
    }

    public void setVertice(Vertice pvertice) {
        this.vertice = pvertice;
    }
   
    public void setSig(HashNode sig) {
        this.sig = sig;
    }
   
    public HashNode getSig() {
        return sig;
    }
}
