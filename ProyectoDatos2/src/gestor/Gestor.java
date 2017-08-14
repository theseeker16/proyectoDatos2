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
        grafo  = new Grafo();
        this.cargarValoresEnHash();
    }

    //busca el camino corto desde un origen hasta un destino
    public String caminoCorto(int porigen, int pdestino) {

        Vertice origen = grafo.getVertice(porigen);
        Vertice destino = grafo.getVertice(pdestino);

        grafo.dijkstra(origen);
        grafo.masCorto();
        grafo.camino(porigen, pdestino);
        
        Vertice caminoCorto;//variable tipo Vertice se le asigna el valor del vertice guardado en el arreglo P
        String texto;
       
            texto = "Camino corto:" + "\n" + origen.getNumeroVertice() + "." + origen.getEtiqueta() + "\n";
            
            //Se recorre la lista de vertices para obtener el camino completo desde el origen hasta el destino
            for (int i = 0; i < grafo.p.size(); i++) {
                caminoCorto = grafo.getVertice(grafo.p.get(i));
                texto += caminoCorto.getNumeroVertice() + "." + caminoCorto.getEtiqueta() + "\n";
            }

            texto += destino.getNumeroVertice() + "." + destino.getEtiqueta() + "\n";
            texto += "Distancia :" + grafo.costosMinimos[destino.getNumeroVertice()] + " metros";

            grafo.p.clear();//Limpia todos los valores del arreglo P, para que no se acumulen ubicaciones siguientes.
           
        return texto;
    }
   
    //Imprime la matriz de adyacencia completa
    public void imprimirMatrizAdyacenciaCompleta() {
        grafo.imprimirMatrizAdyacenciaCompleta();
    }
    //Imprime solo la matriz del origen que se solicita
    public void imprimirMatrizAdyacenciaBusqueda(int origen) {
        grafo.imprimirMatrizAdyacencia(origen);
    }

    //Busca el vertice dentro de la tabla hash
    //recibe el numero de vertice para ser encontrado.
    public String obtenerVerticeEnHash(long n) {
        String contenido;
        HashNode v = hash.getNumero(n);
        if (v != null) {

            contenido = " El numero de vertice:" + hash.getNumero(n).getVertice().getNumeroVertice() + ",Etiqueta:" + hash.getNumero(n).getVertice().getEtiqueta() + ",Posicion en el hash:" + v.getPosicion();

        } else {
            contenido = "No existe ningun registro que corresponda";
        }
        return contenido;
    }

    //Carga los vertices en el hash.
    private void cargarValoresEnHash() {

        for (int i = 1; i <= 24; i++) {
            Vertice vertice = new Vertice();
            vertice.setEtiqueta(grafo.getVertices()[i].getEtiqueta());
            vertice.setNumeroVertice(grafo.getVertices()[i].getNumeroVertice());

            hash.agregarValor(hash.hashing(i), new HashNode(vertice));
        }

    }

    //Obtiene el arreglo de vertices
    public Vertice[] obtenerVertices() {
        return grafo.getVertices();
    }

}
