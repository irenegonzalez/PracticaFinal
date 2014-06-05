/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.medicamento;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.GregorianCalendar;

/**
 *
 * @author Irene
 */
public class Lote implements Comparable<Lote>, Serializable{
    private String nombre;
    private double p;
    public enum TipoLote {conReceta, sinReceta};
    private TipoLote tipo;  
    private ArrayList<PpoAct> ppoAct = new ArrayList<>();
    private int udFcdas;
    private GregorianCalendar fecFab;
    private GregorianCalendar fecCad;
    
    public Lote() {
    }

    public Lote(String nombre) {
        this.nombre = nombre;
    }
    
    

    public Lote(String nombre, int udFcdas, ArrayList ppoAct, TipoLote tipo, double p) {
        this.nombre = nombre;
        this.udFcdas = udFcdas;
        this.ppoAct=ppoAct;    
        this.tipo=tipo;  
        this.p=p;
        this.fecFab = new GregorianCalendar();
        this.fecCad = new GregorianCalendar();
        fecCad.add(GregorianCalendar.YEAR, +1);
    }

    public String getNombre() {
        return nombre;
    }

    public double getP() {
        return p;
    }

    public ArrayList<PpoAct> getPpoAct() {
        return ppoAct;
    }

    public int getUdFcdas() {
        return udFcdas;
    }

    public TipoLote getTipo() {
        return tipo;
    }

    public GregorianCalendar getFecFab() {
        return fecFab;
    }

    public GregorianCalendar getFecCad() {
        return fecCad;
    }

    public void setPpoAct(ArrayList ppoAct) {
        this.ppoAct = ppoAct;
    }

    public void setP(double p) {
        this.p = p;
    }

    public void setUdFcdas(int udFcdas) {
        this.udFcdas = udFcdas;
    }
        
      @Override 
    public boolean equals(Object obj) {
        Lote l = (Lote) obj;
        return nombre.contains(l.nombre);
    }  
      
         @Override
    public int compareTo(Lote o) {
        return o.fecCad.compareTo(fecCad);
    }  

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("'d�a' dd 'de' MMMM 'de' yyyy 'a las' hh:mm:ss");
        return "Lote: "+nombre+" Precio: "+p+ " Unidades fabricadas: "+udFcdas+" Principio activo: "+ppoAct.toString()+" Tipo: "+tipo+" Fecha de fabricación: "+sdf.format(fecFab.getTime())+" Fecha de caducidad: "+sdf.format(fecCad.getTime());
    }

    
        
    
         
    
    
}
