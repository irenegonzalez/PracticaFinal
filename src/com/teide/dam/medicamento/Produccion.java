/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.medicamento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;

/**
 *
 * @author Irene
 */
public class Produccion implements Serializable{
    
   public ArrayList<Lote> listado = new ArrayList<>();
   
    /**
    * Ordena el listado, que quedará ya ordenado por refencia, ¿de menor a mayor? 
    * fecha de caducidad y borra los lotes caducados. 
    * @return true si algún lote ha sido borrado. Si no ha encontrado lotes caducados, false.
    */
   public boolean limpiarStock (){
       boolean eliminados = false;
       Collections.sort(listado);
       GregorianCalendar gc = new GregorianCalendar ();
       for (Lote lote : listado) {
           int devuelve = lote.getFecCad().compareTo(gc);
           if (devuelve ==-1) {
               listado.remove(lote);
               eliminados = true;
           }    
        }
       return eliminados;
   }
   /**
    * Busca por palabras similares del nombre del Lote la primera ocurrencia. 
    * @param nombreLote Cadena con texto similar al nombre del Lote a buscar.
    * @return El Lote que antes caduca con un nombre que contenga palabras similares la cadena buscada.
    */
   public Lote buscarXNombreLote(String nombreLote){
       
       Lote aux = new Lote(nombreLote);
       for (Lote lote : listado) {
           if(lote.getNombre().equals(aux.getNombre())) return lote;
       }
       if (listado.contains(aux)) return listado.get(listado.indexOf(aux));
       else return null;
  }  
   /**
    * Busca un principio activo, en cada uno de los Lotes existentes, por palabras similares del nombre de principio activo. 
    * @param nombrePpo La cadena con un texto similar al nombre del principio activo a buscar.
    * @return ArrayList de Lotes que contienen el principio activo buscado.
    */
   public ArrayList<Lote> buscarXNombrePpo(String nombrePpo){
       
       PpoAct aux = new PpoAct(nombrePpo);
       
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
       }
       listado.add(l);
       return true;
   }     
   
   /**
    * Busca si hay stock del lote, en su caso escoge los lotes que caducan antes, y va restando unidades 
    * @param nombreLote El nombre del lote proporcionadas por el usuario.
    * @param udes Las unidades a vender proporcionadas por el ususario.
    */
   public boolean venderXNombreLote(String nombreLote, int udes){//En el Ejecuta calcular el coste y comprobar si es con receta y si sí, preguntar al user si la tiene.
         
           Lote aux=buscarXNombreLote(nombreLote);
           if (aux!=null) {
               int udTotalesXNombre=0;
               
               for (Lote lote : listado) {
                    if (lote.getNombre().equals(aux.getNombre())) udTotalesXNombre+=lote.getUdFcdas();
               }
               if (udTotalesXNombre>=udes){
                   int i =0;
                   do{
                       Lote lote = listado.get(i);
                       if (lote.getNombre().equals(aux.getNombre())) {
                           if (lote.getUdFcdas() < udes) {
                               listado.remove(i);
                               i--;
                           }
                           else lote.setUdFcdas(lote.getUdFcdas()-udes);
                           udes-=lote.getUdFcdas();
                        }
                        i++;
                    }
                    while(udes>0);
                    return true;
                }
               else return false;
            }
            else return false;
    }
             
    /**
    * Busca si hay stock en los lotes que contengan ese principio activo, en su caso escoge los lotes que caducan antes, y va restando unidades 
    * @param nombrePrincipio El nombre de un principio activo proporcionadas por el usuario.
    * @param udes Las unidades a vender proporcionadas por el ususario.
    */      

   public boolean borrar (String nombre){
       Lote l = new Lote(nombre);
       for (Lote lote : listado) {
                  if(buscarXNombreLote(nombre)!=null){
                  listado.remove(l);
                  return true;
                  }                                 
       }
       return false;
      
   }
   
   
   @Override
    public String toString() {
        String resultado = "";
        for (Lote lote : listado) {
            resultado+=lote.toString()+"\n";
        }
            return resultado;
        }
   
}