/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor;

import logica.Grafo;
import logica.Vertice;
import static ui.Main.br;

/**
 *
 * @author jgm16
 */
public class Gestor {

    Grafo grafo = new Grafo();

    public void caminoCorto(int porigen, int pdestino) {

        Vertice origen = grafo.getVertice(porigen);

        Vertice destino = grafo.getVertice(pdestino);

        grafo.dijkstra(origen);
        grafo.masCorto();

        int x = grafo.camino(origen.getNumeroVertice(), destino.getNumeroVertice());
        System.out.println("Camino corto :" + origen.getEtiqueta() + " ->" + x);

        System.out.println("Distancia :" + grafo.costosMinimos[destino.getNumeroVertice()] + " metros");
    }

    public void imprimirMatrizAdyacenciaCompleta() {
        grafo.imprimirMatrizAdyacenciaCompleta();
    }

    public void imprimirMatrizAdyacenciaBusqueda(int origen) {
        grafo.imprimirMatrizAdyacencia(origen);
    }

}
