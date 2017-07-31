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
public class Grafo {
  
   
    public int costosMinimos[];// aqui van quedando los costos minimos
    private Vertice[] vertices; //se guardan los vertices
    private int[][] caminos; //se utiliza para sacar los caminos
    private int[][] costos; //matriz donde estan los costos de ir de un vertice a otro
    private int[] verticesVisitados; //aqui van quedando los vertices visitados
    private int verticeVisitado; //contiene el vertice visitado actualmente
    private String [] nombresUbicaciones;
    
    private ArrayList<Vertice> lista;//lista de vertices
    
    public Grafo(){
        lista = new ArrayList<Vertice>();
        vertices = new Vertice[25];
        nombresUbicaciones = new String[25];
        verticesVisitados = new int[25];
        caminos = new int[25][25];
        costos = new int[25][25];
        costosMinimos = new int[25];
        
       this.cargarCostos();
       this.cargarUbicaciones();
       this.insertarUbicaciones();
      
        
       
    }
    
    private void cargarCostos(){
        
         for (int i = 1; i <= 24; i++){
            for (int j =1; j <= 24; j++){
 
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
    }
    
    //Carga el nombre de las ubicaciones
    private void cargarUbicaciones(){
        
        nombresUbicaciones[0] = null;
        nombresUbicaciones[1] = "B";
        nombresUbicaciones[2] = "C";
        nombresUbicaciones[3] ="D";
        nombresUbicaciones[4] ="E";
        nombresUbicaciones[5] ="F";
        nombresUbicaciones[6] ="G";
        nombresUbicaciones[7] ="H";
        nombresUbicaciones[8] ="I";
        nombresUbicaciones[9] ="J";
        nombresUbicaciones[10] ="K";
        nombresUbicaciones[11] ="L";
        nombresUbicaciones[12] ="M";
        nombresUbicaciones[13] ="N";
        nombresUbicaciones[14] ="Ñ";
        nombresUbicaciones[15] ="O";
        nombresUbicaciones[16] ="P";
        nombresUbicaciones[17] ="Q";
        nombresUbicaciones[18] ="R";
        nombresUbicaciones[19] ="S";
        nombresUbicaciones[20] ="T";
        nombresUbicaciones[21] ="U";
        nombresUbicaciones[22] ="V";
        nombresUbicaciones[23] ="W";
        nombresUbicaciones[24] ="X";
    }
    
    //insertar el nombre de las ubicaciones dentro del arreglo de vertices
    private void insertarUbicaciones(){
        
         for (int i = 1; i < 25; i++) {
             for (int j = 0; j < this.nombresUbicaciones[i].length(); j++) {
                 //Se crea objetos vertice con el nombre de la etiqueta ya establecida
                 Vertice vertice = new Vertice(i,this.nombresUbicaciones[i]);
                 this.vertices[i] = vertice;
                 System.out.println("Numero vertice:" + vertices[i].getNumeroVertice() + " Etiqueta: " + vertices[i].getEtiqueta());
                 this.verticesVisitados[i] = i; 
             }
        }
    }
   

    public void dijkstra(){
        
        int[] conjunto = new int[8];
        int posconjunto = 1;
        //vertices[posconjunto] = 0;
        this.costosMinimos[1] = 0;
        
        for (int i = 2; i <= 7; i++) {
            this.costosMinimos[i] = costos[1][i];
        }
        
        for (int i = 1; i <= 7-1; i++) {
            this.verticeVisitado = buscaMinimo();
            posconjunto++;
            conjunto[posconjunto] = this.verticeVisitado;
            this.verticesVisitados[this.verticeVisitado] = 0;
            
            for (int v = 7 - posconjunto; v <= 7; v++) {
                this.costosMinimos[v] = min(this.costosMinimos[v], this.costosMinimos[this.verticeVisitado] + costos[this.verticeVisitado][v]);
            }
        }
    }
    
    public void masCorto(){
        

        int[][] a = new int [8][8];
        
        for (int i = 1; i <= 7; i++) {
            for (int j = 1; j <= 7; j++) {
                a[i][j] = costos[i][j];
                this.caminos[i][j] = 0;
            }
        }
        
        for (int i = 1; i <= 7; i++) {
           a[i][i] = 0; 
        }
        
        for (int k = 1; k <= 7; k++) {
            for (int i = 1; i <= 7; i++) {
                for (int j = 1; j <= 7; j++) {
                    if ((a[i][k] + a[k][j]) < (a[i][j])) {
                        a[i][j] = a[i][k] + a[k][j];
                        this.caminos[i][j] = k;
                    }
                }
            }
        }
    }
    
    public void caminos(int pi, int pj){
        
        int k;
        char c = ' ';
        k = this.caminos[pi][pj];
        
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
            if (this.verticesVisitados[i] != 0) {
                if (this.costosMinimos[i] < minimo) {
                    minimo = this.costosMinimos[i];
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

