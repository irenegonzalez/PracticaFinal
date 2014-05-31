/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.medicamento;

import java.text.SimpleDateFormat;
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
    private enum tiposLotes {conReceta, sinReceta};
    private tiposLotes tipo;  
    private ArrayList PpoAct = new ArrayList<>();
    private int udFcdas;
    private GregorianCalendar fecFab;
    private GregorianCalendar fecCad;
//    private Calendar fecFab;
//    private Calendar fecCad;

    public Lote(String nombre, int udFcdas, ArrayList ppoAct, tiposLotes tipo, double p) {
        this.nombre = nombre;
        this.udFcdas = udFcdas;
        this.PpoAct=ppoAct;    
        this.tipo=tipo;
// ¿Con esto basta para que luego coja "conReceta" o "sinReceta"?   
        this.p=p;

        this.fecFab = new GregorianCalendar();
        this.fecCad = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("'día' dd 'de' MMMM 'de' yyyy 'a las' hh:mm:ss");
        sdf.format(fecFab.getTime());
        sdf.format(fecCad.getTime());
        fecCad.add(GregorianCalendar.YEAR, +1);
 //¿Es correcto que vaya el objeto sdf en el constructor?
 //Preguntarle a Antonio cuál es la diferencia y cuál está mejor.
//        this.fecFab = Calendar.getInstance(); 
//        this.fecCad = Calendar.getInstance();
//        fecCad.add(Calendar.YEAR, +1);
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

    public tiposLotes getTipo() {
        return tipo;
    }

    public GregorianCalendar getFecFab() {
        return fecFab;
    }

    public GregorianCalendar getFecCad() {
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
        return nombre.contains(l.nombre);
    }  
      
         @Override
    public int compareTo(Lote o) {
        return o.fecCad.compareTo(fecCad);
    }  
    
}
