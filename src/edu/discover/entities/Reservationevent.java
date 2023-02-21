/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.discover.entities;

import java.util.Objects;

/**
 *
 * @author ramzi
 */
public class Reservationevent {
    private int idresrvation;
    private String nomclient;
    private int nbrclient;   

    
    //constructeur 
    public Reservationevent() {
    }

    public Reservationevent(String nomclient, int nbrclient) {
        this.nomclient = nomclient;

        this.nbrclient = nbrclient;
    }

    public Reservationevent(int idresrvation, String nomclient, int nbrclient) {
        this.idresrvation = idresrvation;
        this.nomclient = nomclient;
        this.nbrclient = nbrclient;
    }

    
    
    //getter setter
    public int getIdresrvation() {
        return idresrvation;
    }


    public String getNomclient() {
        return nomclient;
    }

    public void setNomclient(String nomclient) {
        this.nomclient = nomclient;
    }

   

    public int getNbrclient() {
        return nbrclient;
    }

    public void setNbrclient(int nbrclient) {
        this.nbrclient = nbrclient;
    }

    @Override
    public String toString() {
        return "Reservationevent{" + "nomclient=" + nomclient + ", nbrclient=" + nbrclient + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.idresrvation;
        hash = 67 * hash + Objects.hashCode(this.nomclient);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reservationevent other = (Reservationevent) obj;
        if (this.idresrvation != other.idresrvation) {
            return false;
        }
        if (!Objects.equals(this.nomclient, other.nomclient)) {
            return false;
        }
        if (!Objects.equals(this.nbrclient, other.nbrclient)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
    
    
}
