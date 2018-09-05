/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autobuskestanice.controller;

import autobuskestanice.model.Karte;
import autobuskestanice.model.Voznje;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author marij
 */
public class VoznjeController implements Initializable {

    @FXML
    TableView<Voznje> voznjeTbl;
    
    @FXML
    private TableColumn<Voznje, Integer> idTblCol;
    
    @FXML
    private TableColumn<Voznje, String> vpolazakTblCol;
    
    @FXML
    private TableColumn<Voznje, String> vdolazakTblCol;
    
    @FXML
    private TableColumn<Voznje, Integer> brsjedistaTblCol;
    
    @FXML
    private TableColumn<Voznje,String> linijaTblCol;
    
    @FXML
    private TableColumn<Voznje, Float> cijenaTblCol;
    
    @FXML
    private Label idLab;
    
    @FXML
    private Label sjedistaLab;
    
    @FXML
    private Label slobodnaLab;
    
    @FXML
    private Button brojSM;
    
    private void setCellValueFromTable() {
        voznjeTbl.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
               Voznje v=voznjeTbl.getItems().get(voznjeTbl.getSelectionModel().getSelectedIndex());
               
               idLab.setText(String.valueOf(v.getId_voznje()));
               sjedistaLab.setText(String.valueOf(v.getBroj_sjedista()));
   
            }
        
        });
    }
    
    public void izracunajBrojSlobodnihMjesta(){
        try{
            int id=Integer.parseInt(idLab.getText());
            int karte=Karte.brojKarti(id);
            int sjedista=Integer.parseInt(sjedistaLab.getText());
            slobodnaLab.setText(String.valueOf(sjedista-karte));
            
         }catch(RuntimeException exc) {
            JOptionPane.showMessageDialog(null, "Odaberite vožnju!");
        } 
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Voznje> data=Voznje.uzmiSveVoznje();
        
        idTblCol.setCellValueFactory(new PropertyValueFactory<Voznje, Integer>("id_voznje"));
        vpolazakTblCol.setCellValueFactory(new PropertyValueFactory<Voznje, String>("vrijeme_polaska"));
        vdolazakTblCol.setCellValueFactory(new PropertyValueFactory<Voznje, String>("vrijeme_dolaska"));
        brsjedistaTblCol.setCellValueFactory(new PropertyValueFactory<Voznje, Integer>("broj_sjedista"));
        linijaTblCol.setCellValueFactory(new PropertyValueFactory<Voznje, String>("naziv_linije"));
        cijenaTblCol.setCellValueFactory(new PropertyValueFactory<Voznje, Float>("cijena"));
       
        voznjeTbl.setItems(data); 
   
        
        setCellValueFromTable();
       
    }    
    
}
