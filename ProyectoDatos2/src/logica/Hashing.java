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

    //Agrega el valor a la tabla hash
    public void agregarValor(int indice, HashNode ptr) {

        //Se valida que si el indice esta null puede guardarse el valor ahi
        //Pero si no se obtiene la siguiente posicion para guardarlo, al final queda como una lista enlazada
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

    //Obtiene el numero dentro de la tabla hash
    //@param num: numero del vertice a obtener
    public HashNode getNumero(long num) {
        HashNode aux = null;
        //Busca dentro de la tablaHash el indice del vertice que se desea obtener
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
