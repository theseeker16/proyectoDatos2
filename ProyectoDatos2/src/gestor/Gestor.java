/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor;

import logica.Arco;
import logica.Grafo;
import logica.HashNode;
import logica.Hashing;
import logica.Vertice;
import static ui.Main.br;

/**
 *
 * @author jgm16
 */
public class Gestor {

    Hashing hash;
    Grafo grafo;

    public Gestor() {
        hash = new Hashing();
        grafo = new Grafo();
        this.cargarValoresEnHash();
    }
     
    public void caminoCorto(int porigen, int pdestino) {

        Vertice origen = grafo.getVertice(porigen);

        Vertice destino = grafo.getVertice(pdestino);

        grafo.dijkstra(origen);
        grafo.masCorto();
        grafo.camino(porigen, pdestino);
        Vertice caminoCorto;
        String texto;

        System.out.println("------------------------------------------------------------");

        texto = "Camino corto:" + "\n" + origen.getNumeroVertice() + "." + origen.getEtiqueta() + "\n";
        for (int i = 0; i < grafo.p.size(); i++) {
            caminoCorto = grafo.getVertice(grafo.p.get(i));
            texto = texto + caminoCorto.getNumeroVertice() + "." + caminoCorto.getEtiqueta() + "\n";
        }

        System.out.println(texto + destino.getNumeroVertice() + "." + destino.getEtiqueta());
        System.out.println("Distancia :" + grafo.costosMinimos[destino.getNumeroVertice()] + " metros");
        System.out.println("------------------------------------------------------------" + "\n");

    }

    public void imprimirMatrizAdyacenciaCompleta() {
        grafo.imprimirMatrizAdyacenciaCompleta();
    }

    public void imprimirMatrizAdyacenciaBusqueda(int origen) {
        grafo.imprimirMatrizAdyacencia(origen);
    }

    public String obtenerVerticeEnHash(int indice, long n) {
        String contenido;
        HashNode v = hash.getNumero(hash.hashing(indice), n);
        if (v != null) {
            contenido = " El numero: " + hash.getNumero(indice, n).getVertice().getNumeroVertice() + " Etiqueta:" + hash.getNumero(indice, n).getVertice().getEtiqueta() + " Se encuentra en la posicion " + indice;
        } else {
            contenido = "No existe ningun registro que corresponda";
        }
        return contenido;
    }

    private void cargarValoresEnHash() {

        for (int i = 1; i <= 24; i++) {

            Vertice vertice = new Vertice();
            vertice.setEtiqueta(grafo.getVertices()[i].getEtiqueta());
            vertice.setNumeroVertice(grafo.getVertices()[i].getNumeroVertice());

//            Arco arco = new Arco();
//            arco.setPeso(grafo.getVertices()[i].getSucesor().get(i).getPeso());
//            arco.setPunteroVertice(grafo.getVertices()[i].getSucesor().get(i).getPunteroVertice());
//            vertice.setSucesor(arco);
            hash.agregarValor(hash.hashing(i), new HashNode(vertice));
        }

    }
    public Vertice[] obtenerVertices(){
        return grafo.getVertices();
    }

}
