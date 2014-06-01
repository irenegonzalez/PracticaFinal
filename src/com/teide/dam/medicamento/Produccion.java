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
   public boolean buscarXNombreLote(String nombreLote){
       
       Lote aux = new Lote();
       nombreLote=aux.getNombre();
       if (listado.equals(aux)) return true;
       else return false;    
   }  
   
   public boolean buscarXNombrePpo(String nombrePpo){
       
       PpoAct aux = new PpoAct();
       nombrePpo=aux.getNomPpoAct();
       if (listado.equals(aux)) return true;
       else return false;    
   }
   
   public void crearPpo(ArrayList ppo){
      Lote l = new Lote();
      l.setPpoAct(ppo);
      
   }
   
   
   /**
    * Busca si existen lotes con el mismo nombre y cambia el precio de todos 
    * ellos para igualarlo con el precio de este nuevo lote. Si no existen lotes 
    * con este nombre, crea un nuevo ppo activo.
    * Creará un nuevo lote en cualquier caso.
    * @param l El lote que se da de alta.
    */
   public void alta (Lote l){
       
       if(buscarXNombreLote(l.getNombre())){
           for (Lote lote : listado) {
                   if (l.getP()!=lote.getP()) lote.setP(l.getP());
           }
           listado.add(l);
       }
       else {
           crearPpo(l.getPpoAct());
           listado.add(l); 
           }        
       }     
   
   /**
    * Busca si hay stock del lote, en su caso escoge el lote que caduca antes, resta esas unidades 
    * @param nombreLote El nombre del lote a vender proporcionadas por el usuario.
    * @param udes Las unidades a vender proporcionadas por el ususario.
    */
   public boolean venderXNombreLote(String nombreLote, int udes){//En el Ejecuta calcular el coste y comprobar si es con receta y si sí, preguntar al user si la tiene.
         
           if (buscarXNombreLote(nombreLote)) {
               int udTotalesXNombre=0;
               Lote aux = new Lote();
               for (Lote lote : listado) {
                   if (listado.equals(aux)) udTotalesXNombre+=aux.getUdFcdas();
               }
               if (udTotalesXNombre>=udes){
                   aux.setUdFcdas(aux.getUdFcdas()-udes);
                   return true;
               }
               else return false;                 
           }       
           else return false;
   }
    public boolean venderXNombrePpo(String nombrePpo, int udes){//En el Ejecuta calcular el coste y comprobar si es con receta y si sí, preguntar al user si la tiene.
         
           if (buscarXNombrePpo(nombrePpo)) {
               int udTotalesXNombre=0;
               Lote aux = new Lote();
               for (Lote lote : listado) {
                   if (listado.equals(aux)) udTotalesXNombre+=aux.getUdFcdas();
               }
               if (udTotalesXNombre>=udes){
                   aux.setUdFcdas(aux.getUdFcdas()-udes);
                   return true;
               }
               else return false;                 
           }       
           else return false;
   }
   public boolean borrar (String nombre){
       Lote l = new Lote();
       for (Lote lote : listado) {
                  if(buscarXNombreLote(nombre)){
                  listado.remove(l);
                  return true;
                  }                                 
       }
       return false;
      
   }       
   
         
       
   }
   
   
   
  
    

