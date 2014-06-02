/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.medicamento;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Irene
 */
public class Ejecuta {
    public static void main(String[] args) {
     Scanner s=new Scanner (System.in);
        Produccion p = new Produccion();
         int opcion = 0;
          do{
            System.out.println("1. Arrancar");
            System.out.println("2. Alta lote");
            System.out.println("3. Buscar por nombre");
            System.out.println("4. Buscar por principio activo");
            System.out.println("5. Vender por nombre");
            System.out.println("6. Vender por principio activo");
            System.out.println("7. Borrar medicamento");
            System.out.println("8. Salir");
            opcion = s.nextInt();
            s.nextLine();
            switch (opcion) {
                case 1: {
                    p.limpiarStock();
                    break;
                }
                case 2: {
                    System.out.println("Introduce el nombre del lote");
                    String nombre = s.nextLine();
                    System.out.println("Introduce el precio del lote");
                    int precio = s.nextInt();
                    System.out.println("Introduce el tipo de lote");
                    String tipo = s.nextLine();
                    System.out.println("Introduce las unidades del medicamento");
                    int unidades = s.nextInt();
                    System.out.println("Introduce el nombre del principio activo");
                    String nombreP = s.nextLine();
                    System.out.println("Introduce los mg del principio activo");
                    double mg = s.nextDouble();
                   
                    ArrayList<PpoAct> ppo = new ArrayList<>();
                    crearPpo(ppo);
                    ppo.add(nombreP);
                    ppo.add(mg);
                    
                    Lote l = new Lote(nombre, unidades, ppo, tipo, precio);
                    p.alta(l);
                    System.out.println("El medicamento se a dado de alta correctamente");
                    break;
                }
                case 3: {
                    System.out.println("Introduce el nombre del lote");
                    String nombre = s.nextLine();
                    boolean aux = p.buscarXNombreLote(nombre);
                    if (aux==false) System.out.println("El lote no existe");
                    else System.out.println(aux);
                    break;
                }
                case 4: {
                    System.out.println("Introduce el nombre del principio activo");
                    String nombre = s.nextLine();
                    boolean aux = p.buscarXNombrePpo(nombre);
                    if (aux==false) System.out.println("El principio activo no existe");
                    else System.out.println(aux);
                    break;
                }
                case 5: {
                    System.out.println("Introduce el nombre del lote");
                    String nombre = s.nextLine();
                    break;
                }
                case 6: {
                   
                }
                case 7: {
                  System.out.println("Introduce el nombre del medicamento");
                  String nombre = s.nextLine(); 
                  boolean aux = p.borrar(nombre);
                  if (aux==false) System.out.println("El medicamento no existe");
                  else System.out.println("Se ha borrado correctamente");
                  break;
                }
            }
        }
        while (opcion != 8);
         
    }
}

