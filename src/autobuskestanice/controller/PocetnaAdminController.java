/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autobuskestanice.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author marij
 */
public class PocetnaAdminController implements Initializable {

    @FXML
    Button linijeBtn;
    
    @FXML
    Button voznjeBtn;
    
    @FXML
    Button profilBtn;
    
    @FXML
    Button karteBtn;
    
    @FXML
    Button zaposleniciBtn;
    
    @FXML
    Button odjavaBtn;
    
    public void btnprofil(ActionEvent e) throws IOException{
        Stage pozornica=new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource
        ("/autobuskestanice/view/ProfilAdmin.fxml"));
        AnchorPane prozor = loader.load();
        pozornica.setTitle("Profil");
        Scene scene = new Scene(prozor);
        pozornica.setScene(scene);
        pozornica.setResizable(false);
        pozornica.initModality(Modality.NONE);
        pozornica.initOwner(profilBtn.getScene().getWindow());
        pozornica.showAndWait();
        
        
    }
     
    public void btnzaposlenici(ActionEvent e) throws IOException{
        Stage pozornica=new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource
        ("/autobuskestanice/view/PregledZaposlenika.fxml"));
        AnchorPane prozor = loader.load();
        pozornica.setTitle("Pregled zaposlenika");
        Scene scene = new Scene(prozor);
        pozornica.setScene(scene);
        pozornica.setResizable(false);
        pozornica.initModality(Modality.NONE);
        pozornica.initOwner(zaposleniciBtn.getScene().getWindow());
        pozornica.showAndWait();
        
      
    }
    
    public void btnlinije(ActionEvent e) throws IOException{
        Stage pozornica=new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource
        ("/autobuskestanice/view/PregledLinija.fxml"));
        AnchorPane prozor = loader.load();
        pozornica.setTitle("Pregled linija");
        Scene scene = new Scene(prozor);
        pozornica.setScene(scene);
        pozornica.setResizable(false);
        pozornica.initModality(Modality.NONE);
        pozornica.initOwner(linijeBtn.getScene().getWindow());
        pozornica.showAndWait();
        
       
    }
    
    public void btnvoznje(ActionEvent e) throws IOException{
        Stage pozornica=new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource
        ("/autobuskestanice/view/PregledVoznji.fxml"));
        AnchorPane prozor = loader.load();
        pozornica.setTitle("Pregled vožnji");
        Scene scene = new Scene(prozor);
        pozornica.setScene(scene);
        pozornica.setResizable(false);
        pozornica.initModality(Modality.NONE);
        pozornica.initOwner(voznjeBtn.getScene().getWindow());
        pozornica.showAndWait();
        
       
    }
        
    public void btnkarte(ActionEvent e) throws IOException{
        Stage pozornica=new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource
        ("/autobuskestanice/view/PregledKarti.fxml"));
        AnchorPane prozor = loader.load();
        pozornica.setTitle("Pregled karti");
        Scene scene = new Scene(prozor);
        pozornica.setScene(scene);
        pozornica.setResizable(false);
        pozornica.initModality(Modality.NONE);
        pozornica.initOwner(karteBtn.getScene().getWindow());
        pozornica.showAndWait();
        
        
    }
    
    public void btnodjava(ActionEvent event) throws IOException{
        Parent root;
        root=FXMLLoader.load(getClass().getClassLoader().getResource("autobuskestanice/view/Login.fxml"));
        Stage stage=new Stage();
        stage.setTitle("Prijavite se na sustav!");
        stage.setScene(new Scene (root, 550, 320));
        stage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    } 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    
    
}
