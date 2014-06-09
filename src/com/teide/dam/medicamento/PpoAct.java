/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.medicamento;

import java.io.Serializable;

/**
 *
 * @author Irene
 */
public class PpoAct implements Serializable{
    private  String nomPpoAct;
    private  double mg;

    public PpoAct() {
    }

    public PpoAct(String nomPpoAct, double mg) {
        this.nomPpoAct = nomPpoAct;
        this.mg = mg;
    }

    public PpoAct(String nomPpoAct) {
        this.nomPpoAct = nomPpoAct;
    }

    public String getNomPpoAct() {
        return nomPpoAct;
    }

    public double getMg() {
        return mg;
    }

    public void setNomPpoAct(String nomPpoAct) {
        this.nomPpoAct = nomPpoAct;
    }

    public void setMg(double mg) {
        this.mg = mg;
    }
    
    
          @Override 
    public boolean equals(Object obj) {
        PpoAct p = (PpoAct) obj;
        return p.nomPpoAct.contains(nomPpoAct);
    } 

    @Override
    public String toString() {
        return "Nombre del principio: "+nomPpoAct+" Cantidad de mg: "+mg;
    }        
}
