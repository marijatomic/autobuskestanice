/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autobuskestanice.controller;

import autobuskestanice.model.Zaposlenici;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author marij
 */
public class ProfilController implements Initializable {

    @FXML
    Label imeLab;
    
    @FXML
    Label prezimeLab;
    
    @FXML
    Label brojtelLab;
    
    @FXML
    Label kImeLab;
    
    @FXML
    Label lozinkaLab;
    
    @FXML
    Label placaLab;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int id=LoginController.id_logirani;
        Zaposlenici z=Zaposlenici.daj_zaposlenika(id);
        
        imeLab.setText(z.getIme());
        prezimeLab.setText(z.getPrezime());
        brojtelLab.setText(z.getBroj_telefona());
        kImeLab.setText(z.getKorisnicko_ime());
        lozinkaLab.setText(z.getLozinka());
        placaLab.setText(String.valueOf(z.getPlaca()) + " KM");
    }    
    
}
