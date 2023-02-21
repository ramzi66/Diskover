/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.discover.gui;

import edu.discover.entities.Colaborationevent;
import edu.discover.services.Crudcolaboration;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ramzi
 */
public class AjouteventController implements Initializable {

    @FXML
    private TextField nomi;
    @FXML
    private TextField adressei;
    @FXML
    private TextField nbri;
    @FXML
    private TextField prixi;
    @FXML
    private Button add;
    @FXML
    private DatePicker datei;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void saveEvent(ActionEvent event) {
        
     String nomevent = nomi.getText();
     Date dateevent = Date.valueOf(datei.getValue());
     String adresseevent = adressei.getText();
     int nbrplacevehicule = Integer.parseInt(nbri.getText()) ;//Integer.parseInt pour forcer la conversion en int
     int prixvehiculeevent = Integer.parseInt(prixi.getText());
     
     Colaborationevent c = new Colaborationevent(nomevent, dateevent, adresseevent, nbrplacevehicule, prixvehiculeevent);
     Crudcolaboration cc = new Crudcolaboration();
     cc.ajout(c);
    }
    
}
