/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

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
    
    public Grafo(){
        
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
    }

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
}

