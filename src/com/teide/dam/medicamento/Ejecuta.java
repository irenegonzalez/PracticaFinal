/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.medicamento;

import com.teide.dam.aortiz.ioutil.OperationsIO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Irene
 */
public class Ejecuta {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Produccion p = new Produccion();
        int opcion;
        int tip = 0;

        OperationsIO util = new OperationsIO("datos");
        ArrayList<Lote> listado;
        try {
            listado = (ArrayList<Lote>) util.read();
        } catch (Exception e) {
            listado = new ArrayList<>();
        }

        if (p.limpiarStock()) System.out.println("Se han eliminado lotes caducados");
        else System.out.println("No había lotes caducados");

        do {
            System.out.println("1. Alta lote");
            System.out.println("2. Buscar por nombre");
            System.out.println("3. Buscar por principio activo");
            System.out.println("4. Vender por nombre");
            System.out.println("5. Vender por principio activo");
            System.out.println("6. Borrar medicamento");
            System.out.println("7. Salir");
            opcion = s.nextInt();
            s.nextLine();
            switch (opcion) {

                case 1: {
                    System.out.println("Introduce el nombre del lote");
                    String nombre = s.nextLine();
                    System.out.println("Introduce el precio del lote");
                    int precio = s.nextInt();
                    System.out.println("Introduce las unidades del medicamento");
                    int unidades = s.nextInt();
                    ArrayList<PpoAct> ppo = new ArrayList<>();

                    System.out.println("¿cuantos principios tiene?");
                    int cantidad = s.nextInt();
                    do {
                        s.nextLine();
                        cantidad--;
                        System.out.println("Introduce el nombre del principio activo");
                        String nombreP = s.nextLine();
                        System.out.println("Introduce los mg del principio activo");
                        double mg = s.nextDouble();
                        PpoAct principio = new PpoAct(nombreP, mg);
                        ppo.add(principio);
                    } while (cantidad > 0);

                    System.out.println("Indica si el lote es con receta 1 o sin receta 2");
                    tip = s.nextInt();
                    //Pedir con o sin receta
                    if (tip == 1) {
                        Lote l = new Lote(nombre, unidades, ppo, Lote.TipoLote.conReceta, precio);
                        if (p.alta(l) == true) {
                            System.out.println("El medicamento se a dado de alta correctamente");
                        } else {
                            System.out.println("No se a podido dar de alta");
                        }
                    } else {
                        Lote l = new Lote(nombre, unidades, ppo, Lote.TipoLote.sinReceta, precio);
                        if (p.alta(l) == true) {
                            System.out.println("El medicamento se a dado de alta correctamente");
                        } else {
                            System.out.println("No se a podido dar de alta");
                        }
                    }
                    break;
                }
                case 2: {
                    System.out.println("Introduce el nombre del lote");
                    String nombre = s.nextLine();
                    Lote aux = new Lote();
                    if (p.buscarXNombreLote(nombre) == null) {
                        System.out.println("El lote no existe");
                    } else {
                        aux = p.buscarXNombreLote(nombre);
                        System.out.println(aux);
                    }
                    break;
                }
                case 3: {
                    System.out.println("Introduce el nombre del principio activo");
                    String nombre = s.nextLine();
                    ArrayList<Lote> auxLotes = new ArrayList<>();
                    if (p.buscarXNombrePpo(nombre) == null) {
                        System.out.println("El lote no existe");
                    } else {
                        auxLotes = p.buscarXNombrePpo(nombre);
                        System.out.println(auxLotes);
                    }
                    break;
                }
                case 4: {
                    System.out.println("Introduce el nombre del lote");
                    String nombre = s.nextLine();
                    System.out.println("Introduce las unidades");
                    int unidades = s.nextInt();
                    p.venderXNombreLote(nombre, unidades);
                    if (p.venderXNombreLote(nombre, unidades) == false) {
                        System.out.println("No hay stock"); //Error, salta siempre
                    } else {
                        if (tip == 1) {
                            System.out.println("¿Tienes la receta? 1 Si/2 No");
                            int receta = s.nextInt();
                            if (receta == 1) {
                                p.venderXNombreLote(nombre, unidades);
                                System.out.println("Lote vendido");
                            } else {
                                System.out.println("No se vende sin receta");
                            }
                        } else {
                            p.venderXNombreLote(nombre, unidades);
                            System.out.println("Lote vendido");
                        }
                    }
                    break;
                }
                case 5: {
                    System.out.println("Introduce el nombre del principio activo");
                    String nombre = s.nextLine();
                    System.out.println("Introduce las unidades");
                    int unidades = s.nextInt();
                    p.venderXNombrePpo(nombre, unidades);
                    if (p.venderXNombrePpo(nombre, unidades) == false) {
                        System.out.println("No hay stock");
                    } else {
                        if (tip == 1) {
                            System.out.println("¿Tienes la receta? 1 Si/2 No");
                            int receta = s.nextInt();
                            if (receta == 1) {
                                p.venderXNombrePpo(nombre, unidades);
                                System.out.println("Unidades vendidas");
                            } else {
                                System.out.println("No se vende sin receta");
                            }
                        } else {
                            p.venderXNombrePpo(nombre, unidades);
                            System.out.println("Unidades vendidas");
                        }

                    }
                    break;
                }
                case 6: {
                    System.out.println("Introduce el nombre del medicamento");
                    String nombre = s.nextLine();
                    if (p.borrar(nombre) == true) {
                        System.out.println("Se ha borrado correctamente");
                    } else {
                        System.out.println("El medicamento no existe");
                    }
                    break;
                }
            }



        } while (opcion != 7);
        try {
            util.write(listado);
            System.out.println("Datos guardados correctamente");
        } catch (IOException e) {
            System.out.println("Error de escritura " + e.getMessage());
        }

    }
}
