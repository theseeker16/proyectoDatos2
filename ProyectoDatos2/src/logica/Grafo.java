/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.awt.Color;
import java.awt.Graphics;
import static java.lang.Math.random;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author jgm16
 */
public class Grafo {

    public int costosMinimos[];// aqui van quedando los costos minimos
    private Vertice[] vertices; //se guardan los vertices
    private int[][] caminos; //se utiliza para sacar los caminos
    private int[][] costos; //matriz donde estan los costos de ir de un vertice a otro
    private Vertice[] verticesVisitados; //aqui van quedando los vertices visitados
    private int verticeVisitado; //contiene el vertice visitado actualmente
    private String[] nombresUbicaciones;//Contiene el nombre de todas las ubicaciones del mapa

    public Grafo() {

        vertices = new Vertice[25];
        nombresUbicaciones = new String[25];
        verticesVisitados = new Vertice[25];
        caminos = new int[25][25];
        costos = new int[25][25]; //Es la matriz de adyacencia
        costosMinimos = new int[25];

        this.cargarCostos();
        this.cargarUbicaciones();
        this.insertarUbicaciones();
        this.cargarArcos();
//        this.mostrarVertices();

    }

    private void cargarCostos() {
        for (int i = 1; i <= 24; i++) {
            for (int j = 1; j <= 24; j++) {
                costos[i][j] = 10000;
            }
        }
    }

    //Carga el nombre de las ubicaciones
    private void cargarUbicaciones() {
       
        nombresUbicaciones[1] = "Lameda Island";
        nombresUbicaciones[2] = "Scunir Island";
        nombresUbicaciones[3] = "Modulig Island";
        nombresUbicaciones[4] = "Jinborei Island";
        nombresUbicaciones[5] = "Barheja Island";
        nombresUbicaciones[6] = "Dayton Island";
        nombresUbicaciones[7] = "Oben Island";
        nombresUbicaciones[8] = "Aberdeen Island";
        nombresUbicaciones[9] = "Landise Island";
        nombresUbicaciones[10] = "Serka Island";
        nombresUbicaciones[11] = "Epheria";
        nombresUbicaciones[12] = "Epheria Pass";
        nombresUbicaciones[13] = "Elder Bridge";
        nombresUbicaciones[14] = "Oldster's Bridge Post";
        nombresUbicaciones[15] = "Troll Defense Camp";
        nombresUbicaciones[16] = "Isolated Sentry Post";
        nombresUbicaciones[17] = "Forsaken Land";
        nombresUbicaciones[18] = "Cohen Farm";
        nombresUbicaciones[19] = "Tainted Farm";
        nombresUbicaciones[20] = "Calpheon";
        nombresUbicaciones[21] = "Garvino Farm";
        nombresUbicaciones[22] = "Calpheon Castle Ruins";
        nombresUbicaciones[23] = "Catfishman Camp";
        nombresUbicaciones[24] = "Northern Kaia Mountain Summit";

    }

    //insertar el nombre de las ubicaciones dentro del arreglo de vertices
    private void insertarUbicaciones() {
        int peso = 0;
        Graphics g;
        if (this.vertices != null) {
            for (int i = 1; i < 25; i++) {
                for (int j = 1; j < this.nombresUbicaciones[i].length(); j++) {
                    //Se crea objetos vertice con el nombre de la etiqueta ya establecida 
                    Vertice vertice = new Vertice(i, this.nombresUbicaciones[i]);

                    this.vertices[i] = vertice;
                    this.verticesVisitados[i] = vertice;
                }
            }
        }
    }

    public void cargarArcos() {

        //Lameda Island a Scunir Island
        Vertice Vsucesor = this.vertices[2];
        Arco Asucesor = new Arco(200, Vsucesor);
        this.vertices[1].setSucesor(Asucesor);
        this.costos[1][2] = Asucesor.getPeso();

        //Lameda Island a Modulig Island
        Vsucesor = this.vertices[3];
        Asucesor = new Arco(500, Vsucesor);
        this.costos[1][3] = Asucesor.getPeso();
        this.vertices[1].setSucesor(Asucesor);

        //Modulig Island a Jinborei Island
        Vsucesor = this.vertices[4];
        Asucesor = new Arco(1000, Vsucesor);
        this.costos[3][4] = Asucesor.getPeso();
        this.vertices[3].setSucesor(Asucesor);

        //Scunir Island a Jinborei Island
        Vsucesor = this.vertices[4];
        Asucesor = new Arco(700, Vsucesor);
        this.costos[2][4] = Asucesor.getPeso();
        this.vertices[2].setSucesor(Asucesor);

    }

    public void mostrarVertices() {
        for (int i = 1; i < this.vertices.length; i++) {
            System.out.println("Numero vertice:" + vertices[i].getNumeroVertice() + " Etiqueta: " + vertices[i].getEtiqueta());
        }
    }
    //FUncionalidades del grafo************************************************

    public String imprimirArco(Vertice nodo, Arco arco) {
        String mensaje;

        mensaje = "*********************" + "                   " + "*********************" + "\n"
                + "*    Nodo Origen    *" + "   *************   " + "*    Nodo Destino   *" + "\n"
                + "*Numero: " + nodo.getNumeroVertice() + "          *" + "   * Peso: " + arco.getPeso() + " ->*   " + "*Numero: " + arco.getPunteroVertice().getNumeroVertice() + "          *" + "\n"
                + "*Etiqueta: " + nodo.getEtiqueta() + "        *" + "   *************   " + "*Etiqueta: " + arco.getPunteroVertice().getEtiqueta() + "        *" + "\n"
                + "*********************" + "                   " + "*********************" + "\n" + "\n";

        return mensaje;
    }

    public String ImprimirArcosNodo(Vertice nodo) {
        String msj = "";

        if (nodo.getPredecesor() == null) {
            msj = msj + "No hay vertices predecesores";
        } else {
            Arco aux;
            msj = msj + "-Predecesores" + "\n";

            for (int i = 0; i < nodo.getPredecesor().size(); i++) {
                msj = msj + this.imprimirArco(nodo, nodo.getPredecesor().get(i));
            }

        }

        if (nodo.getSucesor() == null) {
            msj = msj + "No hay vertices sucesores";
        } else {
            Arco aux;
            msj = msj + "-Sucesores" + "\n";
            for (int i = 0; i < nodo.getSucesor().size(); i++) {
                msj = msj + this.imprimirArco(nodo, nodo.getSucesor().get(i));
            }
        }

        return msj;
    }

    public Vertice getVertice(int numero) {
        Vertice result = null;
        for (int i = 1; i < this.vertices.length; i++) {
            if (vertices[i].getNumeroVertice() == numero) {
                return vertices[i];
            }
        }

        return result;
    }
    
    //quitar el void
    public void dijkstra(Vertice origen) {

        int[] verticesNoVisitados = new int[25];// aqui se van guardando los vertices no visitados
        int posconjunto = 1;

        this.costosMinimos[origen.getNumeroVertice()] = 0;

        for (int i = 1; i <= 24; i++) {
            this.costosMinimos[i] = costos[origen.getNumeroVertice()][i];
        }

        for (int i = 1; i <= 24-1; i++) {
            this.verticeVisitado = buscaMinimo();//deja en w el vertice no visitado con el menor costo en ese momento
            posconjunto++;
            verticesNoVisitados[posconjunto] = this.verticeVisitado;//inserta al vertice en los visitados
            this.verticesVisitados[this.verticeVisitado] = null; //saca al vertice los no visitados
            //this.verticesVisitados[this.verticeVisitado] = 0;

            for (int v = 24 - posconjunto; v <= 24; v++) {
                this.costosMinimos[v] = min(this.costosMinimos[v], this.costosMinimos[this.verticeVisitado] + costos[this.verticeVisitado][v]);
            }
        }

    }
    
    //Busca el vertice con costo minimo de los no visitados
    private int buscaMinimo() {

        int minimo = 10000, pos = 10000;

        for (int i = 1; i < 25; i++) {
            if (this.verticesVisitados[i] != null) {
                if (this.costosMinimos[i] < minimo) {
                    minimo = this.costosMinimos[i];
                    pos = 1;
                }
            }
        }

        return pos;
    }

    public void masCorto() {

        int[][] a = new int[25][25];

        for (int i = 1; i < 25; i++) {
            for (int j = 1; j < 25; j++) {
                a[i][j] = costos[i][j];
                this.caminos[i][j] = 0;
            }
        }

        for (int i = 1; i < 25; i++) {
            a[i][i] = 0;
        }

        for (int k = 1; k < 25; k++) {
            for (int i = 1; i < 25; i++) {
                for (int j = 1; j < 25; j++) {
                    if ((a[i][k] + a[k][j]) < (a[i][j])) {
                        a[i][j] = a[i][k] + a[k][j];
                        this.caminos[i][j] = k;
                    }
                }
            }
        }
    }
   
    //imprime la ruta mas corta desde el vertice i hasta el j
    public int caminos(int origen,int destino) {
        int k;
        
        k = this.caminos[origen][destino];
        if (k == 0) {
            return 0;
        }
   
             caminos(origen, k);
        
             caminos(destino,k );
        
       
        return k;
    }

    //retorna el valor con menor costo
    private int min(int px, int py) {

        if (px < py) {
            return px;
        } else {
            return py;
        }
    }
}
