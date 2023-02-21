/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.discover.services;

import edu.discover.entities.Reservationevent;
import edu.discover.utils.Datasource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author ramzi
 */
public class Crudreservation implements IService<Reservationevent> {
    Connection cnx = Datasource.getInstance().getCnx();

    @Override
    public void ajout(Reservationevent t) {
 try {
        if (t.getNomclient().isEmpty()) {
            System.err.println("Remplir votre nom !! ");
            return;
        }
        
        // Check if the client name already exists in the database
        String checkQuery = "SELECT COUNT(*) FROM `reservationvehiculee` WHERE NomClient=?";
        PreparedStatement checkStatement = cnx.prepareStatement(checkQuery);
        checkStatement.setString(1, t.getNomclient());
        ResultSet checkResult = checkStatement.executeQuery();
        checkResult.next();
        int count = checkResult.getInt(1);
        if (count > 0) {
            System.err.println("Vous avez deja reservé !! ");
            return;
        }

        String req = "INSERT INTO `reservationvehiculee`(`NomClient`, `NbrClient`) VALUES (?, ?)";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setString(1, t.getNomclient());
        st.setInt(2, t.getNbrclient());
        st.executeUpdate();
        System.out.println("Reservation ajouter!");
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }  
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `reservationvehiculee` WHERE IdReservationv = ? ";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, id);
            st.executeUpdate();
            System.out.println("_______________________");
            System.out.println("Colab deleted!!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Reservationevent t) {
        try {

            String req = "UPDATE `reservationvehiculee` SET `NomClient`= ?,`NbrClient`= ? WHERE IdReservationv = 3 ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getNomclient());
            ps.setInt(2, t.getNbrclient());
            ps.executeUpdate();
            System.out.println("_______________________");
            System.out.println("row modified");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public Reservationevent getOneById(int id) {
Reservationevent c1 = null;
        try {
            String req = "SELECT * FROM `reservationvehiculee` WHERE IdReservationv=" + id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                c1 = new Reservationevent(
                        rs.getString(2),
                        rs.getInt(3));
                System.out.println("_______________________");
                System.out.println("Reservation affichée :");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return c1;    }

    @Override
    public List<Reservationevent> getAll() {
List<Reservationevent> resercation = new ArrayList<>();
        try {
            String req = "SELECT * FROM `reservationvehiculee`";
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                Reservationevent rec = new Reservationevent(
                        result.getString("NomClient"),
                        result.getInt("NbrClient")
                );
                resercation.add(rec);
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return resercation;    }
    
    
    
     public static List<Reservationevent> trier(List<Reservationevent> listc) {
        return listc.stream()
                .sorted(Comparator.comparing(Reservationevent::getNbrclient))
                .collect(Collectors.toList());
    }
    
    
     
     public static List<Reservationevent> rechercher(List<Reservationevent> listc,String nomclient)
   {
       return (List<Reservationevent>) listc.stream()
        .filter(a -> a.getNomclient().equalsIgnoreCase(nomclient)).collect(Collectors.toList());
       
       
   }
     
     
     
     
     
    
    
    
    
}
