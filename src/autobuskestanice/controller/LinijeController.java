/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autobuskestanice.controller;

import autobuskestanice.model.AutobuskaLinija;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author marij
 */
public class LinijeController implements Initializable {

    @FXML
    TableView<AutobuskaLinija> linijeTbl;
    
    @FXML
    private TableColumn<AutobuskaLinija, String> nazivTblCol;
    
    @FXML
    private TableColumn<AutobuskaLinija, String> polazakTblCol;
    
    @FXML
    private TableColumn<AutobuskaLinija, String>  dolazakTblCol;
    
    @FXML
    private TableColumn<AutobuskaLinija, Float> cijenaTblCol;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<AutobuskaLinija> data=AutobuskaLinija.uzmiSveLinije();
    
        
        nazivTblCol.setCellValueFactory(new PropertyValueFactory<AutobuskaLinija, String>("Naziv_linije"));
        polazakTblCol.setCellValueFactory(new PropertyValueFactory<AutobuskaLinija, String>("Mjesto_polaska"));
        dolazakTblCol.setCellValueFactory(new PropertyValueFactory<AutobuskaLinija, String>("Mjesto_dolaska"));
        cijenaTblCol.setCellValueFactory(new PropertyValueFactory<AutobuskaLinija, Float>("Cijena"));
        linijeTbl.setItems(data); 
    }    
    
}
