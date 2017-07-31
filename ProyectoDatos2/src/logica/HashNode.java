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
    
    int elementId;
    Vertice data;
    HashNode sig;
    
    public HashNode(int x) {
        this.setElementId(x);
    }
    public void setElementId(int elementId) {
        this.elementId = elementId;
    }
    public void setData(Vertice data) {
        this.data = data;
    }
    public void setSig(HashNode sig) {
        this.sig = sig;
    }
    public int getElementId() {
        return elementId;
    }
    public Vertice getData() {
        return data;
    }
    public HashNode getSig() {
        return sig;
    }
}
