/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autobuskestanice.controller;

import autobuskestanice.model.Zaposlenici;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author marij
 */
public class ProfilAdminController implements Initializable {
  
    /**
     * Initializes the controller class.
     */
    @FXML
    Label ImeLab;
    
    @FXML
    Label PrezimeLab;
    
    @FXML
    Label TelLab;
    
    @FXML
    Label kImeLab;
    
    @FXML
    Label LozinkaLab;
     
    @FXML
    Label PlacaLab;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int id=LoginController.id_logirani;
        Zaposlenici z=Zaposlenici.daj_zaposlenika(id);
        
        ImeLab.setText(z.getIme());
        PrezimeLab.setText(z.getPrezime());
        TelLab.setText(z.getBroj_telefona());
        kImeLab.setText(z.getKorisnicko_ime());
        LozinkaLab.setText(z.getLozinka());
        PlacaLab.setText(String.valueOf(z.getPlaca())+ " KM");
    }  
    
    
}
