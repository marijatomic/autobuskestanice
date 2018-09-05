/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autobuskestanice.controller;

import autobuskestanice.model.AutobuskaLinija;
import autobuskestanice.model.Voznje;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
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
public class PregledVoznjiController implements Initializable {

    /**
     * Initializes the controller class.
     * 
     */
    @FXML
    ComboBox<AutobuskaLinija> linijaCB;
    
    @FXML 
    TextField idTF;
    
    @FXML 
    TextField vpolazakTF;
    
    @FXML 
    TextField vdolazakTF;
    
    @FXML 
    TextField sjedistaTF;
    
    
    @FXML
    TableView<Voznje> voznjeTbl;
    
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
    private TableColumn<Voznje, Integer> idTblCol;
    
   
    public void spremi_voznju(ActionEvent e) throws ParseException{
        try{   
            String vpolazakTxt=vpolazakTF.getText();
            String vdolazakTxt=vdolazakTF.getText();
            int sjedistaTxt=Integer.parseInt(sjedistaTF.getText());
            int id_linije=linijaCB.getValue().getId_linije();
            
            Voznje v=new Voznje(vpolazakTxt,vdolazakTxt,sjedistaTxt,id_linije);
            v.spasi();
            this.initialize(null, null);
        
        }catch(RuntimeException exc) {
            JOptionPane.showMessageDialog(null, "Nastala je greška. Unesite sve podatke!");
        } 

    }
    
    public void obrisi_voznju(ActionEvent e){
        
        try{
        
            Voznje v=new Voznje();
            v.setId_voznje(Integer.parseInt(idTF.getText()));
            v.brisi();
            this.initialize(null, null);
        
        }catch(RuntimeException exc) {
                    JOptionPane.showMessageDialog(null, "Nastala je greška. Odaberite vožnju!");
        }
 
    }
    
    public void uredi_voznju(ActionEvent e) throws ParseException{
        try {
            
            Voznje v=new Voznje();
            v.setId_voznje(Integer.parseInt(idTF.getText()));
            v.setVrijeme_polaska(vpolazakTF.getText());
            v.setVrijeme_dolaska(vdolazakTF.getText());
            v.setBroj_sjedista(Integer.parseInt(sjedistaTF.getText()));
            v.setId_linije(linijaCB.getValue().getId_linije());
          
            v.uredi();
            this.initialize(null, null);
            
         }catch(RuntimeException exc) {
                    JOptionPane.showMessageDialog(null, "Nastala je greška. Unesite sve podatke!");
         }
        
    }

    private void setCellValueFromTable() {
        voznjeTbl.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                Voznje v=voznjeTbl.getItems().get(voznjeTbl.getSelectionModel().getSelectedIndex());
                idTF.setText(String.valueOf(v.getId_voznje()));
                vpolazakTF.setText(v.getVrijeme_polaska());
                vdolazakTF.setText(v.getVrijeme_dolaska());
                sjedistaTF.setText(String.valueOf(v.getBroj_sjedista()));
                linijaCB.setPromptText(v.getNaziv_linije());
                

           }
        
        });
    }
    
     private final ObservableList<AutobuskaLinija> linija=FXCollections.observableArrayList(
            AutobuskaLinija.uzmiSveLinije()
       
     );
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Voznje> data=Voznje.uzmiSveVoznje();
            
      
 
        vpolazakTblCol.setCellValueFactory(new PropertyValueFactory<Voznje, String>("vrijeme_polaska"));
        vdolazakTblCol.setCellValueFactory(new PropertyValueFactory<Voznje, String>("vrijeme_dolaska"));
        brsjedistaTblCol.setCellValueFactory(new PropertyValueFactory<Voznje, Integer>("broj_sjedista"));
        linijaTblCol.setCellValueFactory(new PropertyValueFactory<Voznje, String>("naziv_linije"));
        cijenaTblCol.setCellValueFactory(new PropertyValueFactory<Voznje, Float>("cijena"));
        idTblCol.setCellValueFactory(new PropertyValueFactory<Voznje, Integer>("id_voznje"));
        voznjeTbl.setItems(data); 
        linijaCB.setItems(linija);
        
    
        
        setCellValueFromTable();
    }    
    
}
