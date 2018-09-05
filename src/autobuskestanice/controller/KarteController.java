/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autobuskestanice.controller;

import autobuskestanice.model.Karte;
import autobuskestanice.model.Voznje;
import autobuskestanice.model.VrsteKarti;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author marij
 */
public class KarteController implements Initializable {
    @FXML
    TextField idTF;
    
    @FXML
    TextField vizdavanjaTF; 
    
    @FXML
    TextField sjedisteTF;
    
    @FXML
    Label kcijenaLab;

    @FXML
    ComboBox<Voznje> voznjaCB;
    
    @FXML
    ComboBox<VrsteKarti> vrstaCB;
    
    @FXML
    Button prodaj;
    
    @FXML
    Button rezerviraj;
    
    @FXML
    Button otkazi;
    
    @FXML
    Button cijenaKarte;

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
    private TableColumn<Karte,String> tipTblCol;
    
    @FXML
    DatePicker odbDatum;

    private final DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    
    private final ObservableList<Voznje> voznja=FXCollections.observableArrayList(
        Voznje.uzmiSveVoznje()
       
     );
    
    private final ObservableList<VrsteKarti> vrste=FXCollections.observableArrayList(
        VrsteKarti.uzmiSveVrste()
       
    );
    
    public void prodaj_kartu(ActionEvent e) throws ParseException{
        try{   
            String dizdavanjaTxt=formater.format(odbDatum.getValue());
            String vizdavanjaTxt=vizdavanjaTF.getText();
            int sjedistaTxt=Integer.parseInt(sjedisteTF.getText());
            int id_zaposlenika=LoginController.id_logirani;
            int id_voznje=voznjaCB.getValue().getId_voznje();
            int id_vrste=vrstaCB.getValue().getId_vrste();
            String tipTxt="Prodana";
            
            Karte k=new Karte(dizdavanjaTxt,vizdavanjaTxt,sjedistaTxt,id_zaposlenika,id_voznje,id_vrste,tipTxt);
            k.spasi();
            this.initialize(null, null);
        
        }catch(RuntimeException exc) {
            JOptionPane.showMessageDialog(null, "Nastala je greška. Unesite sve podatke!");
        } 

    }
    
    public void rezerviraj_kartu(ActionEvent e) throws ParseException{
        try{   
            String dizdavanjaTxt=formater.format(odbDatum.getValue());
            String vizdavanjaTxt=vizdavanjaTF.getText();
            int sjedistaTxt=Integer.parseInt(sjedisteTF.getText());
            int id_zaposlenika=LoginController.id_logirani;
            int id_voznje=voznjaCB.getValue().getId_voznje();
            int id_vrste=vrstaCB.getValue().getId_vrste();
            String tipTxt="Rezervirana";
            
            Karte k=new Karte(dizdavanjaTxt,vizdavanjaTxt,sjedistaTxt,id_zaposlenika,id_voznje,id_vrste,tipTxt);
            k.spasi();
            this.initialize(null, null);
        
        }catch(RuntimeException exc) {
            JOptionPane.showMessageDialog(null, "Nastala je greška. Unesite sve podatke!");
        } 

    }
    
    public void otkazi_rezervaciju(ActionEvent e) {
        try {   
            Karte k1=new Karte();
            k1.setId_karte(Integer.parseInt(idTF.getText()));
            k1.otkazi_rezervaciju();
            this.initialize(null, null);
            
        }catch(RuntimeException exc) {
            JOptionPane.showMessageDialog(null, "Odaberite rezerviranu kartu!");
        } 
    }
    
    public void izracunajCijenuKarte(){
        try{
            int id_voznje=voznjaCB.getValue().getId_voznje();
            int id_vrste=vrstaCB.getValue().getId_vrste();
            Float cijena=Karte.uzmiCijenu(id_voznje);
            Float popust=VrsteKarti.uzmiPopust(id_vrste);
            kcijenaLab.setText(String.valueOf(cijena-(cijena*popust)) + " KM");
        
         }catch(RuntimeException exc) {
            JOptionPane.showMessageDialog(null, "Odaberite vožnju i vrstu karte.");
        } 
    }
    
    private void setCellValueFromTable() {
        karteTbl.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                Karte k=karteTbl.getItems().get(karteTbl.getSelectionModel().getSelectedIndex());
                idTF.setText(String.valueOf(k.getId_karte()));
                odbDatum.setPromptText(k.getDatum_izdavanja());
                vizdavanjaTF.setText(k.getVrijeme_izdavanja());
                voznjaCB.setPromptText(k.getMjesto_polaska()+"-"+k.getMjesto_dolaska()+": " + k.getVrijeme_polaska() +"-"+ k.getVrijeme_dolaska());
                sjedisteTF.setText(String.valueOf(k.getSjediste()));
                vrstaCB.setPromptText(k.getNaziv_vrste());
           
            }
        
        });
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int id=LoginController.id_logirani;
       
        ObservableList<Karte> data=Karte.uzmiSveKarteZaposlenika(id);
            
      
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
        tipTblCol.setCellValueFactory(new PropertyValueFactory<Karte, String>("tip"));
        
        voznjaCB.setItems(voznja);
        vrstaCB.setItems(vrste);
        karteTbl.setItems(data); 
     
        setCellValueFromTable();
        
        odbDatum.setConverter(new StringConverter<LocalDate>(){
        
            @Override
            public String toString(LocalDate t){
                
                if(t!=null){
                    return formater.format(t);
                }
                return null;
            }
            
            @Override
            public LocalDate fromString(String string){
                
                if(string != null && !string.trim().isEmpty()){
                
                    return LocalDate.parse(string,formater);
                }
                return null;
            }
        
        });
        
        
        

    }    
    
}
