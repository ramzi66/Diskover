/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.discover.services;

import edu.discover.entities.Colaborationevent;
import edu.discover.utils.Datasource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 *
 * @author ramzi
 */
public class Crudcolaboration implements IService<Colaborationevent> {

    Connection cnx = Datasource.getInstance().getCnx();

    @Override
    public void ajout(Colaborationevent t) {
    try {
        if (t.getNomevent().isEmpty() ||  t.getAdresseevent().isEmpty()) {
            System.err.println("Remplir tout les champs ");
            return;
        }
        // Vérifier si le nom de l'événement existe déjà dans la base de données
        String checkReq = "SELECT COUNT(*) FROM `colaborationevent` WHERE NomEvent = ?";
        PreparedStatement checkPs = cnx.prepareStatement(checkReq);
        checkPs.setString(1, t.getNomevent());
        ResultSet rs = checkPs.executeQuery();
        if (rs.next() && rs.getInt(1) > 0) {
            System.err.println("Un événement avec ce nom existe déjà!");
            return;
        }
        // Insérer la nouvelle collaboration si le nom de l'événement est unique
        String req = "INSERT INTO `colaborationevent`(`NomEvent`, `DateEvent`, `AdresseEvent`, `NbrPlaceVehicule`, `PrixVehiculeEvent`) VALUES ('" + t.getNomevent() + "','" + t.getDateevent() + "','" + t.getAdresseevent() + "','" + t.getNbrplacevehicule() + "','" + t.getPrixvehiculeevent() + "')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("_______________________");
        System.out.println("Collaboration add!");
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}


    
    
    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `colaborationevent` WHERE IdEvent = ? ";
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
    public void modifier(Colaborationevent t) {
        try {

            String req = "UPDATE `colaborationevent` SET `NomEvent`= ?,`DateEvent`= ?,`AdresseEvent`= ?,`NbrPlaceVehicule`= ?,`PrixVehiculeEvent`= ? WHERE IdEvent = 3 ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getNomevent());
            ps.setDate(2, t.getDateevent());
            ps.setString(3, t.getAdresseevent());
            ps.setInt(4, t.getNbrplacevehicule());
            ps.setInt(5, t.getPrixvehiculeevent());
            ps.executeUpdate();
            System.out.println("_______________________");
            System.out.println("row modified");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    
    
    
    @Override
    public Colaborationevent getOneById(int id) {
        Colaborationevent c1 = null;
        try {
            String req = "SELECT * FROM `colaborationevent` WHERE IdEvent=" + id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                c1 = new Colaborationevent(
                        rs.getString(2),
                        rs.getDate(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getInt(6));
                System.out.println("_______________________");
                System.out.println("Event affichée :");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return c1;
    }

    
    
    
    @Override
    public List<Colaborationevent> getAll() {
        List<Colaborationevent> colaboration = new ArrayList<>();
        try {
            String req = "SELECT * FROM `colaborationevent`";
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                Colaborationevent rec = new Colaborationevent(
                        result.getString("NomEvent"),
                        result.getDate("DateEvent"),
                        result.getString("AdresseEvent"),
                        result.getInt("NbrPlaceVehicule"),
                        result.getInt("PrixVehiculeEvent")
                );
                colaboration.add(rec);
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return colaboration;
    }
    
    
    
    
    
    //tri
    public static List<Colaborationevent> trier(List<Colaborationevent> listc) {
        return listc.stream()
                .sorted(Comparator.comparing(Colaborationevent::getPrixvehiculeevent))
                .collect(Collectors.toList());
    }
    
    
    
    
    
    //recherche
    public static List<Colaborationevent> rechercher(List<Colaborationevent> listc,String nomevent, String adresseevent)
   {
       return (List<Colaborationevent>) listc.stream()
        .filter(a -> a.getNomevent() .equalsIgnoreCase(nomevent) || a.getAdresseevent().equalsIgnoreCase(adresseevent)).collect(Collectors.toList());
       
       
   }

}
