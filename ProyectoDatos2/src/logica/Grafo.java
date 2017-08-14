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
    public ArrayList<Integer> p = new ArrayList(); //obtiene los caminos desde el origen hasta el destino para enlistarlos.

    public Grafo() {

        vertices = new Vertice[25];
        nombresUbicaciones = new String[25];
        verticesVisitados = new Vertice[25];
        caminos = new int[25][25];
        costos = new int[25][25]; //Es la matriz de adyacencia
        costosMinimos = new int[25];

        this.cargarUbicaciones();
        this.insertarUbicaciones();
        this.cargarCostos();
        this.cargarArcos();

    }

    public void cargarCostos() {
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
    private void cargarArcos() {

        //Lameda Island a Scunir Island
        Vertice Vsucesor = this.vertices[2];
        Arco Asucesor = new Arco(200, Vsucesor);
        this.vertices[1].setSucesor(Asucesor);
        this.costos[1][2] = Asucesor.getPeso();

        //Lameda Island a Modulig Island
        Vsucesor = this.vertices[3];
        Asucesor = new Arco(500, Vsucesor);
        this.vertices[1].setSucesor(Asucesor);
        this.costos[1][3] = Asucesor.getPeso();

        //Modulig Island a Jinborei Island
        Vsucesor = this.vertices[4];
        Asucesor = new Arco(280, Vsucesor);
        this.vertices[3].setSucesor(Asucesor);
        this.costos[3][4] = Asucesor.getPeso();

        //Scunir Island a Jinborei Island
        Vsucesor = this.vertices[4];
        Asucesor = new Arco(550, Vsucesor);
        this.vertices[2].setSucesor(Asucesor);
        this.costos[2][4] = Asucesor.getPeso();

        //Jinborei Island a Barheja Island
        Vsucesor = this.vertices[5];
        Asucesor = new Arco(780, Vsucesor);
        this.vertices[4].setSucesor(Asucesor);
        this.costos[4][5] = Asucesor.getPeso();

        //Jinborei Island a Dayton Island
        Vsucesor = this.vertices[6];
        Asucesor = new Arco(240, Vsucesor);
        this.vertices[4].setSucesor(Asucesor);
        this.costos[4][6] = Asucesor.getPeso();

        //Dayton Island a Serka Island
        Vsucesor = this.vertices[10];
        Asucesor = new Arco(400, Vsucesor);
        this.vertices[6].setSucesor(Asucesor);
        this.costos[6][10] = Asucesor.getPeso();

        //Serka Island a Epheria
        Vsucesor = this.vertices[11];
        Asucesor = new Arco(120, Vsucesor);
        this.vertices[10].setSucesor(Asucesor);
        this.costos[10][11] = Asucesor.getPeso();

        //Epheria a  Epheria Pass
        Vsucesor = this.vertices[12];
        Asucesor = new Arco(350, Vsucesor);
        this.vertices[11].setSucesor(Asucesor);
        this.costos[11][12] = Asucesor.getPeso();

        //Epheria Pass a Elder Bridge
        Vsucesor = this.vertices[13];
        Asucesor = new Arco(600, Vsucesor);
        this.vertices[12].setSucesor(Asucesor);
        this.costos[12][13] = Asucesor.getPeso();

        //Epheria Pass a Oldster's Bridge Post
        Vsucesor = this.vertices[14];
        Asucesor = new Arco(1500, Vsucesor);
        this.vertices[12].setSucesor(Asucesor);
        this.costos[12][14] = Asucesor.getPeso();

        //Barheja Island a Oben Island
        Vsucesor = this.vertices[7];
        Asucesor = new Arco(200, Vsucesor);
        this.vertices[5].setSucesor(Asucesor);
        this.costos[5][7] = Asucesor.getPeso();

        //Oben Island a Aberdeen Island
        Vsucesor = this.vertices[8];
        Asucesor = new Arco(300, Vsucesor);
        this.vertices[7].setSucesor(Asucesor);
        this.costos[7][8] = Asucesor.getPeso();

        //Oben Island a Landise Island
        Vsucesor = this.vertices[9];
        Asucesor = new Arco(450, Vsucesor);
        this.vertices[8].setSucesor(Asucesor);
        this.costos[8][9] = Asucesor.getPeso();

        //Oben Island a Oldster's Bridge Post
        Vsucesor = this.vertices[14];
        Asucesor = new Arco(1000, Vsucesor);
        this.vertices[8].setSucesor(Asucesor);
        this.costos[8][14] = Asucesor.getPeso();

        //Oldster's Bridge Post a Troll Defense Camp
        Vsucesor = this.vertices[15];
        Asucesor = new Arco(900, Vsucesor);
        this.vertices[14].setSucesor(Asucesor);
        this.costos[14][15] = Asucesor.getPeso();

        // Troll Defense Camp a Forsaken Land
        Vsucesor = this.vertices[17];
        Asucesor = new Arco(740, Vsucesor);
        this.vertices[15].setSucesor(Asucesor);
        this.costos[15][17] = Asucesor.getPeso();

        // Troll Defense Camp a Isolated Sentry Post
        Vsucesor = this.vertices[16];
        Asucesor = new Arco(400, Vsucesor);
        this.vertices[15].setSucesor(Asucesor);
        this.costos[15][16] = Asucesor.getPeso();

        //Isolated Sentry Post a Cohen Farm
        Vsucesor = this.vertices[18];
        Asucesor = new Arco(400, Vsucesor);
        this.vertices[16].setSucesor(Asucesor);
        this.costos[16][18] = Asucesor.getPeso();

        // Forsaken Land a Cohen Farm
        Vsucesor = this.vertices[18];
        Asucesor = new Arco(502, Vsucesor);
        this.vertices[17].setSucesor(Asucesor);
        this.costos[17][18] = Asucesor.getPeso();

        //Cohen Farm Post a Tainted Farm
        Vsucesor = this.vertices[19];
        Asucesor = new Arco(1000, Vsucesor);
        this.vertices[18].setSucesor(Asucesor);
        this.costos[18][19] = Asucesor.getPeso();

        // Tainted Farm a  Calpheon
        Vsucesor = this.vertices[20];
        Asucesor = new Arco(600, Vsucesor);
        this.vertices[19].setSucesor(Asucesor);
        this.costos[19][20] = Asucesor.getPeso();

        // Tainted Farm a Garvino Farm
        Vsucesor = this.vertices[21];
        Asucesor = new Arco(200, Vsucesor);
        this.vertices[19].setSucesor(Asucesor);
        this.costos[19][21] = Asucesor.getPeso();

        //Calpheon a Calpheon Castle Ruins
        Vsucesor = this.vertices[22];
        Asucesor = new Arco(750, Vsucesor);
        this.vertices[20].setSucesor(Asucesor);
        this.costos[20][22] = Asucesor.getPeso();

        //Calpheon Castle Ruins a Northern Kaia Mountain Summit
        Vsucesor = this.vertices[24];
        Asucesor = new Arco(900, Vsucesor);
        this.vertices[22].setSucesor(Asucesor);
        this.costos[22][24] = Asucesor.getPeso();

        //Catfishman Camp a Northern Kaia Mountain Summit
        Vsucesor = this.vertices[24];
        Asucesor = new Arco(360, Vsucesor);
        this.vertices[23].setSucesor(Asucesor);
        this.costos[23][24] = Asucesor.getPeso();

        //Catfishman Camp a Northern Kaia Mountain Summit
        Vsucesor = this.vertices[23];
        Asucesor = new Arco(800, Vsucesor);
        this.vertices[21].setSucesor(Asucesor);
        this.costos[21][23] = Asucesor.getPeso();

    }

    //Obtiene el vertice se busca por medio del numero de vertice
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
        
        int posconjunto = 1;
        int[] verticesNoVisitados = new int[25];// aqui se van guardando los vertices no visitados
        this.costosMinimos[1] = 0;
        //this.vertices[posconjunto] = null;
        for (int i = 1; i <= 24; i++) {
            this.costosMinimos[i] = costos[origen.getNumeroVertice()][i];
        }

        for (int i = 1; i < 24; i++) {

            posconjunto++;
            this.verticeVisitado = buscaMinimo();//deja en w el vertice no visitado con el menor costo en ese momento
            verticesNoVisitados[posconjunto] = this.verticeVisitado;//inserta al vertice en los visitados

            //Se valida para al tratar de sacar de los visitados no trate de acceder a una posicion que no exista
            if (verticeVisitado != 10000) {
                //Saca los vertices que se van visitando
                this.verticesVisitados[verticeVisitado] = null;
                for (int v = 1; v < 25; v++) {
                    //analiza si es menor por este camino(verticeVisitado) o por el que esta en este momento
                    this.costosMinimos[v] = min(this.costosMinimos[v], this.costosMinimos[this.verticeVisitado] + costos[this.verticeVisitado][v]);
                }
            }
        }
        this.insertarUbicaciones();
    }

    //retorna el valor con menor costo
    private int min(int x, int y) {
        if (x < y) {
            return x;
        } else {
            return y;
        }
    }

    //Busca el vertice con costo minimo de los no visitados
    private int buscaMinimo() {
        int minimo = 10000, pos = 10000;
        for (int i = 1; i < 25; i++) {
            if (this.verticesVisitados[i] != null) {
                if (this.costosMinimos[i] < minimo) {
                    minimo = this.costosMinimos[i];
                    pos = i;
                }
            }
        }
        return pos;
    }

    //Obtiene los caminos cortos del grafo
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
    public void camino(int origen, int destino) {

        int k = 0;

        k = caminos[origen][destino];
        
        if (k == 0) {
            return;
        }
        camino(origen, k);
        camino(k, destino);
        p.add(k); //Va guardando las posiciones de las ubicaciones para poder ser mostrada la secuencia del camino
    }

    //Imprime la matriz de adyacencia pero solo del origen que se pidio
    public void imprimirMatrizAdyacencia(int origen) {
        String posiciones = "";
        for (int i = 1; i < 25; i++) {
            for (int j = 1; j < 25; j++) {
                //Valida que solo salga la fila y columna del origen que se esta enviando
                if (i == origen) {
                    if (this.costos[i][j] != 10000) {
                        //Color magenta
                        System.out.print("\033[35m" + this.costos[i][j] + "\t");
                        posiciones += "\n" + "[" + i + "," + j + "]";//Obtiene las posiciones donde sea diferente a 10000,para poder mostrarlas
                    } else {
                        System.out.print("\033[30m" + this.costos[i][j] + "\t");
                    }
                }
            }
        }
        System.out.println("\n" + "--------------------------------");
        System.out.print("Posiciones dentro de la matriz");
        System.out.println(posiciones); 
        System.out.println();

    }

    //Imprime la matriz completa de adyacencia
    public void imprimirMatrizAdyacenciaCompleta() {

        String fila = ""; //Obtiene valores de 1 a 24 para ser mostrados 
        for (int i = 1; i < this.costos.length; i++) {
            if (i == 1) {
                //La cantidad de puntos varia segun el tamaño de la lista
                for (int u = 1; u < 25; u++) {
                    if (u < 10) {
                        fila += u + ".......";
                    }
                    if ((u >= 10) && (u < 25)) {
                        fila += u + "......";
                    }
                }
                System.out.println("\t" + fila + "\n" + "\t");

            }
            for (int j = 1; j < 25; j++) {
                if (j == 1) {
                    if (i < 10) {
                        System.out.print(i + "   " + "|");
                    }
                    if (i >= 10) {
                        System.out.print(i + "  " + "|");
                    }
                }
                if (costos[i][j] != 10000) {
                    //color magenta
                    System.out.print("\t" + "\033[35m" + this.costos[i][j]);
                } else {
                    //color negro
                    System.out.print("\t" + "\033[30m" + this.costos[i][j]);
                }
                if (j == 25 - 1) {
                    System.out.println("\n" + "    " + "|");
                }
            }
        }
    }

    //Obtiene los vertices
    public Vertice[] getVertices() {
        return vertices;
    }

}
