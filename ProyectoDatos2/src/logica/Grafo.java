/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;

/**
 *
 * @author jgm16
 */
public class Grafo {
  
   
    public int D[];// aqui van quedando los costos minimos
    private int[] vertices; //se guardan los vertices
    private int[][] p; //se utiliza para sacar los caminos
    private int[][] costos; //matriz donde estan los costos de ir de un vertice a otro
    private int[] vertices2; //aqui van quedando los vertices visitados
    private int w; //contiene el vertice visitado actualmente
    
    private ArrayList<Vertice> lista;//lista de vertices
    
    public Grafo(){
        lista = new ArrayList<Vertice>();
    }
    /*public Grafo(){
        
        vertices = new int[8];
        vertices2 = new int[8];
        p = new int[8][8];
        costos = new int[8][8];
        D = new int[8];
        
        for (int i = 1; i <= 7; i++){
            for (int j =1; j <=7; j++){
                
                costos[i][j] = 10000;
            }
        }
        
        costos[1][2] = 200;
        costos[1][4] = 150;
        costos[2][3] = 100;
        costos[3][1] = 50;
        costos[3][7] = 25;
        costos[4][3] = 500;
        costos[4][5] = 200;
        costos[5][6] = 175;
        costos[6][3] = 250;
        costos[7][5] = 300;
        
        for (int i = 1; i < 7; i++) {
            vertices[i] = i;
            vertices2[i] = i;
        }
    }*/

    public void dijkstra(){
        
        int[] conjunto = new int[8];
        int posconjunto = 1;
        vertices[posconjunto] = 0;
        D[1] = 0;
        
        for (int i = 2; i <= 7; i++) {
            D[i] = costos[1][i];
        }
        
        for (int i = 1; i <= 7-1; i++) {
            w = buscaMinimo();
            posconjunto++;
            conjunto[posconjunto] = w;
            vertices2[w] = 0;
            
            for (int v = 7 - posconjunto; v <= 7; v++) {
                D[v] = min(D[v], D[w] + costos[w][v]);
            }
        }
    }
    
    public void masCorto(){
        
        int i, j, k;
        int[][] a = new int [8][8];
        
        for (i = 1; i <= 7; i++) {
            for (j = 1; j <= 7; j++) {
                a[i][j] = costos[i][j];
                p[i][j] = 0;
            }
        }
        
        for (i = 1; i <= 7; i++) {
           a[i][i] = 0; 
        }
        
        for (k = 1; k <= 7; k++) {
            for (i = 1; i <= 7; i++) {
                for (j = 1; j <= 7; j++) {
                    if ((a[i][k] + a[k][j]) < (a[i][j])) {
                        a[i][j] = a[i][k] + a[k][j];
                        p[i][j] = k;
                    }
                }
            }
        }
    }
    
    public void caminos(int pi, int pj){
        
        int k;
        char c = ' ';
        k = p[pi][pj];
        
        if (k == 0) {
            return;
        }
        
        caminos(pi, k);
        
        System.out.println(k);
        System.out.println(c);
        
        caminos(k, pj);
    }
    
    private int buscaMinimo(){
        
        int minimo = 10000, pos = 10000;
        
        for (int i = 1; i <= 7; i++) {
            if (vertices2[i] != 0) {
                if (D[i] < minimo) {
                    minimo = D[i];
                    pos = 1;
                }
            }
        }
        
        return pos;
    }
    
    private int min(int px, int py){
        
        if (px < py) {
            return px;
        }else{
            return py;
        }
    }
    
    //FUncionalidades del grafo
    public ArrayList<Vertice> getLista() {
        return lista;
    }
    
    public String agregarVertice(Vertice pvertice){
        if(this.estaVertice(pvertice)==true){
            return "El nodo ya existe";
        }else{
            lista.add(pvertice);
            return this.imprimirVertice(pvertice);
        }
    }
    
    public void agregarArcoSucesor(Vertice origen, Arco arco){
        origen.setSucesor(arco);
    }
    
    public void agregarArcoPredecesor(Vertice origen, Arco arco){
        origen.setPredecesor(arco);
    }
    
    public boolean estaVertice(Vertice pnodo){
        for (int i = 0; i < lista.size(); i++) {
            if(lista.get(i).getNumeroVertice() == pnodo.getNumeroVertice() && lista.get(i).getEtiqueta().equals(pnodo)){
            return true;
            }
        }
        return false;
    }
    
    public String mostrarGrafo(){
        String msj = "";        
        for (int i = 0; i <= lista.size(); i++) {
            msj = msj + imprimirVertice(lista.get(i));
            
        }
        return msj;
    }
    
    public String imprimirVertice(Vertice nodo){
        String mensaje;
        
        mensaje = "********************"+"\n"
                + "*Numero: "+nodo.getNumeroVertice()+"         *"+"\n"
                + "*Etiqueta: "+nodo.getEtiqueta()+"       *"+"\n"
                + "********************"+"\n"
                + "          |"+"\n"
                + "          v"+"\n"+"\n";
        
        
        return mensaje;
    }  
    
    public String imprimirArco(Vertice nodo, Arco arco){
        String mensaje;
        
        mensaje = "*********************"+"                   " + "*********************"+"\n"
                + "*    Nodo Origen    *"+"   *************   " + "*    Nodo Destino   *"+"\n"
                + "*Numero: "+nodo.getNumeroVertice()+"          *"+"   * Peso: "+arco.getPeso()+" ->*   " + "*Numero: "+arco.getPunteroVertice().getNumeroVertice()+"          *"+"\n"
                + "*Etiqueta: "+nodo.getEtiqueta()+"        *"+"   *************   " + "*Etiqueta: "+arco.getPunteroVertice().getEtiqueta()+"        *"+"\n"
                + "*********************"+"                   " + "*********************"+"\n"+"\n";

        return mensaje;
    }
    
    public String ImprimirArcosNodo(Vertice nodo){
        String msj = "";
        
        if(nodo.getPredecesor() == null){
            msj = msj + "No hay vertices predecesores";
        }else{
            Arco aux ;
            msj = msj + "-Predecesores"+ "\n";
            
            for (int i = 0; i < nodo.getPredecesor().size(); i++) {
                 msj = msj + this.imprimirArco(nodo, nodo.getPredecesor().get(i));
            }
               
            
        }
        
        if(nodo.getSucesor() == null){
            msj = msj + "No hay vertices sucesores";
        }else{
            Arco aux ;
            msj = msj + "-Sucesores"+ "\n";
            for (int i = 0; i < nodo.getSucesor().size(); i++) {
                 msj = msj + this.imprimirArco(nodo, nodo.getSucesor().get(i));
            }
        }
        
        return msj;
    }
    
    public Vertice getVertice(int numero, String etiqueta){
        Vertice result = null;
        
        for (int i = 0; i < lista.size(); i++) {
            if(lista.get(i).getNumeroVertice() == numero && lista.get(i).getEtiqueta().equals(etiqueta)){
                return lista.get(i);
            }
        }
        
        return result;
    }
}

