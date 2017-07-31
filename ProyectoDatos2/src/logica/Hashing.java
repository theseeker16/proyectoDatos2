/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jgm16
 */
public class Hashing {
       private List<HashNode> tablaHash;
    private int max = 30;

    public Hashing() {
        tablaHash = new ArrayList<>();
        for (int i = 0; i < max; i++) {
            this.tablaHash.add(null);
        }
    }

    public String agregarValor(int indice, HashNode ptr) {

        String contenido = "";
        if (tablaHash.get(indice) == null) {
            tablaHash.add(indice, ptr);
            
            contenido = "Indice: " + indice + " Numero " + tablaHash.get(indice).getElementId() + "\n";
        } else {
            HashNode aux = tablaHash.get(indice);
            while (aux.getSig() != null) {
                aux = aux.getSig();
                tablaHash.add(indice,aux);
                contenido = "Indice: " + indice + " Numero " + aux.getSig().getElementId();
            }
            aux.setSig(ptr);
          
        }
        return contenido;
    }

    public HashNode getNumero(int indice, long num) {
        HashNode aux = this.tablaHash.get(indice);
        while (aux != null && aux.getElementId() != num) {
            aux = aux.getSig();
        }
        return aux;
    }

    public int hashing(int num) {
        return (num % 13);
    }
}
