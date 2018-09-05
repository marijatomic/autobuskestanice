/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autobuskestanice.controller;

import autobuskestanice.model.Karte;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author marij
 */
public class PregledKartiController implements Initializable {

    
    @FXML
    TableView<Karte> karteTbl;
    
    @FXML
    private TableColumn<Karte, Integer> idTblCol;
    
    @FXML
    private TableColumn<Karte, String> dizdavanjaTblCol;
    
    @FXML
    private TableColumn<Karte, String> vizdavanjaTblCol;
    
    @FXML
    private TableColumn<Karte, String> mpolazakTblCol;
    
    @FXML
    private TableColumn<Karte, String> mdolazakTblCol;
    
    @FXML
    private TableColumn<Karte, String> vpolazakTblCol;
    
    @FXML
    private TableColumn<Karte, String> vdolazakTblCol;
    
    @FXML
    private TableColumn<Karte, Integer> sjedisteTblCol;
    
    @FXML
    private TableColumn<Karte, Float> cijenaTblCol;
    
    @FXML
    private TableColumn<Karte, String> vrstaTblCol;
    
    @FXML
    private TableColumn<Karte,Float> popustTblCol;
    
    @FXML
    private TableColumn<Karte, String> imezTblCol;
    
    @FXML
    private TableColumn<Karte, String> prezimezTblCol;
    
    @FXML
    private TableColumn<Karte, String> tipTblCol;
    
    @FXML
    Button vrsteBtn;
    
    
    public void btnvrste(ActionEvent e) throws IOException{
        Stage pozornica=new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource
        ("/autobuskestanice/view/VrsteKarti.fxml"));
        AnchorPane prozor = loader.load();
        pozornica.setTitle("Pregled vrsta karti");
        Scene scene = new Scene(prozor);
        pozornica.setScene(scene);
        pozornica.setResizable(false);
        pozornica.initModality(Modality.NONE);
        pozornica.initOwner(vrsteBtn.getScene().getWindow());
        pozornica.showAndWait();  
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Karte> data=Karte.uzmiSveKarte();
            
      
        idTblCol.setCellValueFactory(new PropertyValueFactory<Karte, Integer>("id_karte"));
        dizdavanjaTblCol.setCellValueFactory(new PropertyValueFactory<Karte, String>("datum_izdavanja"));
        vizdavanjaTblCol.setCellValueFactory(new PropertyValueFactory<Karte, String>("vrijeme_izdavanja"));
        mpolazakTblCol.setCellValueFactory(new PropertyValueFactory<Karte, String>("mjesto_polaska"));
        mdolazakTblCol.setCellValueFactory(new PropertyValueFactory<Karte, String>("mjesto_dolaska"));
        vpolazakTblCol.setCellValueFactory(new PropertyValueFactory<Karte, String>("vrijeme_polaska"));
        vdolazakTblCol.setCellValueFactory(new PropertyValueFactory<Karte, String>("vrijeme_dolaska"));
        sjedisteTblCol.setCellValueFactory(new PropertyValueFactory<Karte, Integer>("sjediste"));
        cijenaTblCol.setCellValueFactory(new PropertyValueFactory<Karte, Float>("cijena"));
        vrstaTblCol.setCellValueFactory(new PropertyValueFactory<Karte, String>("naziv_vrste"));
        popustTblCol.setCellValueFactory(new PropertyValueFactory<Karte, Float>("popust"));
        imezTblCol.setCellValueFactory(new PropertyValueFactory<Karte, String>("ime"));
        prezimezTblCol.setCellValueFactory(new PropertyValueFactory<Karte, String>("prezime"));
        tipTblCol.setCellValueFactory(new PropertyValueFactory<Karte, String>("tip"));
       
        karteTbl.setItems(data); 
    }    
    
}
