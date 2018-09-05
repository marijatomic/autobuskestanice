/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autobuskestanice.controller;

import autobuskestanice.model.Voznje;
import autobuskestanice.model.VrsteKarti;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author marij
 */
public class VrsteKartiController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML 
    TextField idTF;
    
    @FXML 
    TextField nazivTF;
    
    @FXML 
    TextField popustTF;
    
    @FXML
    TableView<VrsteKarti> vrsteTbl;
    
    @FXML
    private TableColumn<VrsteKarti, Integer> idTblCol;
    
    @FXML
    private TableColumn<VrsteKarti,String> nazivTblCol;
    
    @FXML
    private TableColumn<VrsteKarti, Float> popustTblCol;
    
    public void spremi_vrstu(ActionEvent e) throws ParseException{
        try{   
            String nazivTxt=nazivTF.getText();
            
            float popustTxt=Float.parseFloat(popustTF.getText());
            
            VrsteKarti v=new VrsteKarti(nazivTxt,popustTxt);
            v.spasi();
            this.initialize(null, null);
        
        }catch(RuntimeException exc) {
            JOptionPane.showMessageDialog(null, "Nastala je greška. Unesite sve podatke!");
        } 

    }
    
    public void obrisi_vrstu(ActionEvent e){
        
        try{
        
            VrsteKarti v=new VrsteKarti();
            v.setId_vrste(Integer.parseInt(idTF.getText()));
            v.brisi();
            this.initialize(null, null);
        
        }catch(RuntimeException exc) {
                    JOptionPane.showMessageDialog(null, "Nastala je greška. Odaberite vrstu!");
        }
 
    }
    
    public void uredi_vrstu(ActionEvent e) throws ParseException{
        try {
            
            VrsteKarti v=new VrsteKarti();
            v.setId_vrste(Integer.parseInt(idTF.getText()));
            v.setNaziv_vrste(nazivTF.getText());
            v.setPopust(Float.parseFloat(popustTF.getText()));
          
            v.uredi();
            this.initialize(null, null);
            
         }catch(RuntimeException exc) {
                    JOptionPane.showMessageDialog(null, "Nastala je greška. Unesite sve podatke!");
         }
        
    }
    
    private void setCellValueFromTable() {
        vrsteTbl.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                VrsteKarti v=vrsteTbl.getItems().get(vrsteTbl.getSelectionModel().getSelectedIndex());
                idTF.setText(String.valueOf(v.getId_vrste()));
                nazivTF.setText(v.getNaziv_vrste());
                popustTF.setText(String.valueOf(v.getPopust()));

           }
        
        });
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<VrsteKarti> data=VrsteKarti.uzmiSveVrste();
        
        nazivTblCol.setCellValueFactory(new PropertyValueFactory<VrsteKarti, String>("naziv_vrste"));
        popustTblCol.setCellValueFactory(new PropertyValueFactory<VrsteKarti, Float>("popust"));
        idTblCol.setCellValueFactory(new PropertyValueFactory<VrsteKarti, Integer>("id_vrste"));
        vrsteTbl.setItems(data); 
        
        setCellValueFromTable();
    }   
}
