/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autobuskestanice.controller;

import autobuskestanice.model.AutobuskaLinija;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author marij
 */
public class PregledLinijaController implements Initializable {
    
    @FXML 
    TextField idTF;
    
    @FXML 
    TextField nazivTF;
    
    @FXML 
    TextField mpolazakTF;
    
    @FXML 
    TextField mdolazakTF;
    
    @FXML 
    TextField cijenaTF;
    
    
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
    
    @FXML
    private TableColumn<AutobuskaLinija, Integer> idTblCol;

    
    public void spremi_liniju(ActionEvent e) throws ParseException{
        try{   
            String nazivTxt=nazivTF.getText();
            String mpolazakTxt=mpolazakTF.getText();
            String mdolazakTxt=mdolazakTF.getText();
            Float cijenaTxt=Float.parseFloat(cijenaTF.getText());
            
            AutobuskaLinija a=new AutobuskaLinija(mpolazakTxt,mdolazakTxt,cijenaTxt,nazivTxt);
            a.spasi();
            this.initialize(null, null);
        
        }catch(RuntimeException exc) {
            JOptionPane.showMessageDialog(null, "Nastala je greška. Unesite sve podatke!");
        } 

    }
    
    public void obrisi_liniju(ActionEvent e){
        try{
        
            AutobuskaLinija a=new AutobuskaLinija();
            a.setId_linije(Integer.parseInt(idTF.getText()));
            a.brisi();
            this.initialize(null, null);
            
        }catch(RuntimeException exc) {
            JOptionPane.showMessageDialog(null, "Nastala je greška. Odaberite liniju!");
        }    
 
    }
    
    public void uredi_liniju(ActionEvent e) throws ParseException{
        try {
            
            AutobuskaLinija a=new AutobuskaLinija();
            a.setId_linije(Integer.parseInt(idTF.getText()));
            a.setNaziv_linije(nazivTF.getText());
            a.setMjesto_polaska(mpolazakTF.getText());
            a.setMjesto_dolaska(mdolazakTF.getText());
            a.setCijena(Float.parseFloat(cijenaTF.getText()));
          
            a.uredi();
            this.initialize(null, null);
            
         }catch(RuntimeException exc) {
                    JOptionPane.showMessageDialog(null, "Nastala je greška. Unesite sve podatke!");
            }
        
    }
    
    
    private void setCellValueFromTable() {
        linijeTbl.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                AutobuskaLinija a=linijeTbl.getItems().get(linijeTbl.getSelectionModel().getSelectedIndex());
                idTF.setText(String.valueOf(a.getId_linije()));
                nazivTF.setText(a.getNaziv_linije());
                mpolazakTF.setText(a.getMjesto_polaska());
                mdolazakTF.setText(a.getMjesto_dolaska());
                cijenaTF.setText(String.valueOf(a.getCijena()));
                
        
           }
        
        });
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ObservableList<AutobuskaLinija> data=AutobuskaLinija.uzmiSveLinije();
    
        
         nazivTblCol.setCellValueFactory(new PropertyValueFactory<AutobuskaLinija, String>("Naziv_linije"));
         polazakTblCol.setCellValueFactory(new PropertyValueFactory<AutobuskaLinija, String>("Mjesto_polaska"));
         dolazakTblCol.setCellValueFactory(new PropertyValueFactory<AutobuskaLinija, String>("Mjesto_dolaska"));
         cijenaTblCol.setCellValueFactory(new PropertyValueFactory<AutobuskaLinija, Float>("Cijena"));
         idTblCol.setCellValueFactory(new PropertyValueFactory<AutobuskaLinija, Integer>("Id_linije"));
         linijeTbl.setItems(data);   
         
         setCellValueFromTable();
    }    
    
}
