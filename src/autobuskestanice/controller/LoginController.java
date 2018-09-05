/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autobuskestanice.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import autobuskestanice.model.Zaposlenici;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;


public class LoginController implements Initializable {
    public static int id_logirani;
    

    @FXML
    Label statusLbl;
    
    @FXML
    TextField kimeTxt;
    
    @FXML
    PasswordField lozinkaTxt;
    
    @FXML
    Button prijaviseBtn;
    
    public void prijavise (ActionEvent event) {
        String kime = kimeTxt.getText();
        String lozinka = lozinkaTxt.getText();
        
        if (kime.equals("") || lozinka.equals("")) {
            statusLbl.setText("Morate unijeti sve vrijednosti!");
        } else {
            if (Zaposlenici.logiraj(kime, lozinka)) {
                Zaposlenici z1=new Zaposlenici(kime,lozinka);
                id_logirani=z1.dajID_prijavljenog();
                
                if (id_logirani==1){
                    try {
                        statusLbl.setTextFill(Color.DARKCYAN);
                        statusLbl.setText("Uspješno ste se prijavili kao administrator.");
                        
                        Parent root;
                        root =FXMLLoader.load(getClass().getClassLoader().getResource("autobuskestanice/view/PocetnaAdmin.fxml"));
                        
                        
                        Stage stage = new Stage();
                        stage.setTitle("Administracija");
                        stage.setScene(new Scene(root, 1049, 450));
                        stage.show();
                        
                        ((Node)(event.getSource())).getScene().getWindow().hide();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }else {
                    try {
                        statusLbl.setTextFill(Color.DARKCYAN);
                        statusLbl.setText("Uspješno ste se prijavili kao "+kime+".");
                        
                        Parent root;
                        root =FXMLLoader.load(getClass().getClassLoader().getResource("autobuskestanice/view/Pocetna.fxml"));
                        
                        
                        Stage stage = new Stage();
                        stage.setTitle("Početna");
                        stage.setScene(new Scene(root, 871, 450));
                        stage.show();
                        
                        ((Node)(event.getSource())).getScene().getWindow().hide();
                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
               

            } else {
                statusLbl.setTextFill(Color.FIREBRICK);
                statusLbl.setText("Korisnički podatci nisu ispravni, pokušajte ponovo.");
            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
