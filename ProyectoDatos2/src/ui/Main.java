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
    static Gestor gestor = new Gestor();

    public static void main(String[] args) throws IOException {

        int opcion = 0;
        do {
            System.out.println("1.Mostrar nombres de ubicaciones del mapa");
            System.out.println("2.Buscar una determinada ubicación por hash");
            System.out.println("3.Mostrar una determinada ubicación,en la matriz de adyacencia");
            System.out.println("4.Mostrar matriz de adyacencia completa");
            System.out.println("5.Mostrar el camino minimo");
            System.out.println("6.Salir");
            System.out.println("Digite una opcion");
            opcion = Integer.parseInt(br.readLine());
            menu(opcion);
        } while (opcion != 6);

    }

    public static void menu(int opcion) throws IOException {
        int origen;
        switch (opcion) {
            case 1:
                for (int i = 1; i < gestor.obtenerVertices().length; i++) {

                    System.out.println("Numero de vertice:" + gestor.obtenerVertices()[i].getNumeroVertice() + " Etiqueta: " + gestor.obtenerVertices()[i].getEtiqueta() + "\t");
                    System.out.println("-------------------------------------------------------");
                }

                break;
            case 2:
                int indice;

                System.out.println("Inserte un el indice del vertice a  buscar");
                indice = Integer.parseInt(br.readLine());

                System.out.println("------------------------------------------------------------");
                System.out.println(gestor.obtenerVerticeEnHash(indice));
                System.out.println("------------------------------------------------------------");
                break;
            case 3:
               
                System.out.println("Ingrese el origen");
                origen = Integer.parseInt(br.readLine());
                 System.out.println();
                for (int i = 1; i < 25; i++) {
                     System.out.print("\t"+i);
                }
                System.out.println();
                System.out.print(origen + " | ");gestor.imprimirMatrizAdyacenciaBusqueda(origen);
                break;
            case 4:
                gestor.imprimirMatrizAdyacenciaCompleta();
                break;
            case 5:
                System.out.println("Ingrese el origen");
                origen = Integer.parseInt(br.readLine());

                System.out.println("Ingrese el destino");
                int destino = Integer.parseInt(br.readLine());
                
                System.out.println("------------------------------------------------------------");
                System.out.println(gestor.caminoCorto(origen, destino));
                System.out.println("------------------------------------------------------------");
                break;
            case 6:
                break;
            default:
        }
    }

}
