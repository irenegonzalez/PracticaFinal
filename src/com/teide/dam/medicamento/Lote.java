/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.medicamento;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Irene
 */
public class Lote implements Comparable<Lote>{
    private String nombre;
    private double p;

    private enum tipo {conReceta, sinReceta};
    private ArrayList PpoAct = new ArrayList<>();
    private int udFcdas;
    private int fecFab;
    private int fecCad;

    public Lote(String nombre, int udFcdas) {
        this.nombre = nombre;
        this.udFcdas = udFcdas;
        Calendar fecFab = Calendar.getInstance(); //No estoy segura de que las fechas estén bien, las he puesto hetInstance para que me digan la fecha de hoy y a la de caduzidad le he sumado un año, y las he metido dentro del constructor porque dijo antonio que se podía, si se te ocurreo otra forma me avisas
        Calendar fecCad = Calendar.getInstance();
        fecCad.add(Calendar.YEAR, +1);
    }

    public String getNombre() {
        return nombre;
    }

    public double getP() {
        return p;
    }

    public ArrayList getPpoAct() {
        return PpoAct;
    }

    public int getUdFcdas() {
        return udFcdas;
    }

    public int getFecFab() {
        return fecFab;
    }

    public int getFecCad() {
        return fecCad;
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
        return nombre.equals(l.nombre);
    }  
      
         @Override
    public int compareTo(Lote o) {
        if (fecCad>o.fecCad) return 1;
        else if (fecCad < o.fecCad) return -1;
        else return 0;
    }  
    
}
