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
    private int max = 24;

    public Hashing() {
        tablaHash = new ArrayList<>();
        for (int i = 0; i < max; i++) {
            this.tablaHash.add(null);
        }
    }

    public void agregarValor(int indice, HashNode ptr) {

        String contenido = "";
        if (tablaHash.get(indice) == null) {
            tablaHash.add(indice, ptr);

        } else {
            HashNode aux = tablaHash.get(indice);
            while (aux.getSig() != null) {
                aux = aux.getSig();
                tablaHash.add(indice, aux);
            }
            aux.setSig(ptr);

        }

    }

    public HashNode getNumero(long num) {
        HashNode aux = null;
        for (int i = 0; i < this.tablaHash.size(); i++) {
            if (this.tablaHash.get(i) != null) {
                if (this.tablaHash.get(i).getVertice().getNumeroVertice() == num) {
                    aux = this.tablaHash.get(i);
                    aux.posicion = i;
                    break;
                }
            }
        }
        while (aux != null && aux.getVertice().getNumeroVertice() != num) {
            aux = aux.getSig();
        }
        
        return aux;
    }

    public int hashing(int num) {
        return (num % (this.max - 1));
    }
}
