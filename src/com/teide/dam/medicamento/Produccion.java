/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.medicamento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;

/**
 *
 * @author Irene
 */
public class Produccion {
    
   public ArrayList<Lote> listado = new ArrayList<>();
   
    /**
    * Ordena el listado, que quedará ya ordenado por refencia, ¿de menor a mayor? 
    * fecha de caducidad y borra los lotes caducados. 
    * @return true si algún lote ha sido borrado. Si no ha encontrado lotes caducados, false.
    */
   public boolean limpiarStock (){
       
       Collections.sort(listado);
       GregorianCalendar gc = new GregorianCalendar ();
       for (Lote lote : listado) {
           int devuelve = lote.getFecCad().compareTo(gc);
           if (devuelve ==-1) listado.remove(lote);    
        }
       return true;
   }
   /**
    * Busca por palabras similares del nombre del Lote la primera ocurrencia. 
    * @param nombreLote Cadena con texto similar al nombre del Lote a buscar.
    * @return El Lote que antes caduca con un nombre que contenga palabras similares la cadena buscada.
    */
   public Lote buscarXNombreLote(String nombreLote){
       
       Lote aux = new Lote();
       nombreLote=aux.getNombre();
       if (listado.contains(aux)) return listado.get(listado.indexOf(aux));
       else return null;    
   }  
   /**
    * Busca un principio activo, en cada uno de los Lotes existentes, por palabras similares del nombre de principio activo. 
    * @param nombrePpo La cadena con un texto similar al nombre del principio activo a buscar.
    * @return ArrayList de Lotes que contienen el principio activo buscado.
    */
   public ArrayList<Lote> buscarXNombrePpo(String nombrePpo){
       
       PpoAct aux = new PpoAct();
       nombrePpo=aux.getNomPpoAct();
       ArrayList<Lote>contenidos=new ArrayList<>();
       for (Lote lote : listado) {
         if (lote.getPpoAct().contains(aux)) contenidos.add(lote);          
       }
       return contenidos;    
   }
   
   public void crearPpo(ArrayList ppo){
      Lote l = new Lote();
      l.setPpoAct(ppo);      
   }
    
   /**
    * Busca si existen lotes con el mismo nombre y cambia el precio de todos 
    * ellos para igualarlo con el precio de este nuevo lote.Creará un nuevo lote en cualquier caso.
    * @param l El lote que se da de alta.
    */
   public boolean alta (Lote l){
       
       if(buscarXNombreLote(l.getNombre())!=null){
           for (Lote lote : listado) {
               if (l.getNombre().equals(lote.getNombre())){
                   if (l.getP()!=lote.getP()) lote.setP(l.getP());
               }              
           }
           listado.add(l);
       }
       else listado.add(l);
       return true;
   }     
   
   /**
    * Busca si hay stock del lote, en su caso escoge los lotes que caducan antes, y va restando unidades 
    * @param nombreLote El nombre del lote proporcionadas por el usuario.
    * @param udes Las unidades a vender proporcionadas por el ususario.
    */
   public boolean venderXNombreLote(String nombreLote, int udes){//En el Ejecuta calcular el coste y comprobar si es con receta y si sí, preguntar al user si la tiene.
         
           if (buscarXNombreLote(nombreLote)!=null) {
               int udTotalesXNombre=0;
               Lote aux=buscarXNombreLote(nombreLote);
               for (Lote lote : listado) {
                    if (listado.equals(aux)) udTotalesXNombre+=aux.getUdFcdas();
                    if (udTotalesXNombre>=udes){
                        do{
                            lote.setUdFcdas(lote.getUdFcdas()-udes);
                            udTotalesXNombre-=udes;
                        }
                        while(udTotalesXNombre>0);
                    }
                    else return false;
                }
                return true;
            }
            else return false;                 
   }       
    /**
    * Busca si hay stock en los lotes que contengan ese principio activo, en su caso escoge los lotes que caducan antes, y va restando unidades 
    * @param nombrePrincipio El nombre de un principio activo proporcionadas por el usuario.
    * @param udes Las unidades a vender proporcionadas por el ususario.
    */      
    public boolean venderXNombrePpo(String nombrePpo, int udes){//En el Ejecuta calcular el coste y comprobar si es con receta y si sí, preguntar al user si la tiene.
         
           if (buscarXNombrePpo(nombrePpo).size()!=0) {
               int udTotalesXNombre=0;
               ArrayList<Lote>contenidos = buscarXNombrePpo(nombrePpo);
               for (int i = 0; i < contenidos.size(); i++) {
                   if (contenidos.get(i).getPpoAct().get(0).equals(nombrePpo)){
                       if (udTotalesXNombre>=udes){
                            do{
                                contenidos.get(i).setUdFcdas(contenidos.get(i).getUdFcdas()-udes);
                                udTotalesXNombre-=udes;
                            }
                            while(udTotalesXNombre>0);
                        }
                        else return false;
                   }                   
               }
               return true;
           } 
           else return false;
    }
   public boolean borrar (String nombre){
       Lote l = new Lote();
       for (Lote lote : listado) {
                  if(buscarXNombreLote(nombre)!=null){
                  listado.remove(l);
                  return true;
                  }                                 
       }
       return false;
      
   }       
}