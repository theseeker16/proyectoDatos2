/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import gestor.Gestor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import logica.Grafo;
import logica.Vertice;

/**
 *
 * @author jgm16
 */
public class Main {
  public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    /**
     * @param args the command line arguments
     */
  Gestor gestor = new Gestor();
    public static void main(String[] args) throws IOException {
      Grafo grafo = new Grafo();
      
      System.out.println("Ingrese el origen");
      int posicion = Integer.parseInt(br.readLine());
      Vertice origen = grafo.getVertice(posicion);
      
      System.out.println("Ingrese el destino");
      posicion = Integer.parseInt(br.readLine());
      Vertice destino = grafo.getVertice(posicion);
      
      grafo.dijkstra(origen);
      grafo.masCorto();
  
      int x = grafo.camino(origen.getNumeroVertice(),destino.getNumeroVertice());
      System.out.println("Camino corto :" + origen.getEtiqueta() + " ->" + x);
    
      System.out.println("Distancia :" + grafo.costosMinimos[destino.getNumeroVertice()]);
          
     
     
   
      
    
      
//      grafo.caminos(origen.getNumeroVertice(), destino.getNumeroVertice());
      
//        int opcion = 0;
//        do {
//            System.out.println("1.Buscar una determinada ubicación");
//            System.out.println("2.Mostrar una determinada ubicación,");
//            System.out.println("3.Mostrar sus ubicaciones adyacentes");
//            System.out.println("4.Mostrar el camino mínimo");
//            System.out.println("5.Mostrar grafo");
//            System.out.println("6.Salir");
//            System.out.println("Digite una opcion");
//            opcion = Integer.parseInt(br.readLine());
//           
//     
//        } while (opcion != 4 );
        
       
    }
    
    public void menu(int opcion){
        switch(opcion){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            default:
        }
    }
    
}
