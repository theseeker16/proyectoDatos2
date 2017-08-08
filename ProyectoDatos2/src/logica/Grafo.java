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
            for (int i = 1; i <= 24; i++) {
                for (int j = 1; j < this.nombresUbicaciones[i].length(); j++) {
                    //Se crea objetos vertice con el nombre de la etiqueta ya establecida 
                    Vertice vertice = new Vertice(i, this.nombresUbicaciones[i]);

                    this.vertices[i] = vertice;
                    this.verticesVisitados[i] = vertice;
                }
            }
        }
    }

    //Se enlaza un vertice con otro por medio de arcos
    //Tambien se pone el peso en la matriz de adyacencia
    //Los datos son estaticos no dinamicos
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

        //Jinborei Island a Barheja Island
        Vsucesor = this.vertices[5];
        Asucesor = new Arco(800, Vsucesor);
        this.costos[4][5] = Asucesor.getPeso();
        this.vertices[4].setSucesor(Asucesor);

        //Jinborei Island a Dayton Island
        Vsucesor = this.vertices[6];
        Asucesor = new Arco(1200, Vsucesor);
        this.costos[4][6] = Asucesor.getPeso();
        this.vertices[4].setSucesor(Asucesor);

    }

    public void mostrarVertices() {
        for (int i = 1; i < this.vertices.length; i++) {
            System.out.println("Numero vertice:" + vertices[i].getNumeroVertice() + " Etiqueta: " + vertices[i].getEtiqueta());
        }
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

    
    public void dijkstra(Vertice origen) {

        int[] verticesNoVisitados = new int[25];// aqui se van guardando los vertices no visitados
        int posconjunto = 1;
        this.costosMinimos[1] = 0;
        this.vertices[posconjunto] = null;
        for (int i = 1; i <= 24; i++) {
            this.costosMinimos[i] = costos[origen.getNumeroVertice()][i];
        }

        for (int i = 1; i < 24-1; i++) {
            this.verticeVisitado = buscaMinimo();//deja en w el vertice no visitado con el menor costo en ese momento
            posconjunto++;
            verticesNoVisitados[posconjunto] = this.verticeVisitado;//inserta al vertice en los visitados

            for (int v = 24 - posconjunto; v <= 24; v++) {
                this.costosMinimos[v] = min(this.costosMinimos[v], this.costosMinimos[this.verticeVisitado] + costos[this.verticeVisitado][v]);
            }
        }

    }

    //Busca el vertice con costo minimo de los no visitados
    private int buscaMinimo() {
        int minimo = 10000, pos = 10000;
        for (int i = 1; i <= 24; i++) {
            if (this.verticesVisitados[i] != null) {
                if (this.costosMinimos[i] < minimo) {
                    minimo = this.costosMinimos[i];
                    pos = i;
                }
            }
        }

        return pos;
    }

    public void masCorto() {
        int i, j, k;
        int[][] a = new int[25][25];

        for (i = 1; i <= 24; i++) {
            for (j = 1; j <= 24; j++) {
                a[i][j] = costos[i][j];
                this.caminos[i][j] = 0;
            }
        }
        for (i = 1; i <= 24; i++) {
            a[i][i] = 0;
        }
        for (k = 1; k <= 24; k++) {
            for (i = 1; i <= 24; i++) {
                for (j = 1; j <= 24; j++) {
                    if ((a[i][k] + a[k][j]) < (a[i][j])) {
                        a[i][j] = a[i][k] + a[k][j];
                        this.caminos[i][j] = k;
                    }
                }
            }
        }
    }

    //imprime la ruta mas corta desde el vertice i hasta el j
    public int camino(int origen, int destino) {
        int k = 0;
        for (int i = 0; i < destino; i++) {
            k = caminos[origen][destino];
            if (k == 0) {
                return 0;
            }
            camino(origen, k);
            camino(k, destino);
        }

        return k;
    }

    //retorna el valor con menor costo
    private int min(int x, int y) {

        if (x < y) {
            return x;
        } else {
            return y;
        }
    }
}
