/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autobuskestanice.controller;

import autobuskestanice.model.Karte;
import autobuskestanice.model.Zaposlenici;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;
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
public class PregledZaposlenikaController implements Initializable {

    /**
     * Initializes the controller class.
     * 
     */
    @FXML 
    TextField idTF;
    
    @FXML 
    TextField imeTF;
    
    @FXML 
    TextField prezimeTF;
    
    @FXML 
    TextField brojtelTF;
    
    @FXML 
    TextField placaTF;
    
    @FXML 
    TextField kimeTF;
    
    @FXML 
    TextField lozinkaTF;
    
    @FXML 
    Label dnevniprometLabel;
    
    @FXML 
    Label prosjecnaplacaLabel;
    
    @FXML
    Button dodajZaposlenika;
    
    @FXML
    Button urediZaposlenika;
    
    @FXML
    Button obrisiZaposlenika;
    
    
    @FXML
    TableView<Zaposlenici> zaposleniciTbl;
    
    @FXML
    private TableColumn<Zaposlenici, String> imeTblCol;
    
    @FXML
    private TableColumn<Zaposlenici, String>  prezimeTblCol;
    
    @FXML
    private TableColumn<Zaposlenici, String> brojtelTblCol;
    
    @FXML
    private TableColumn<Zaposlenici, Float> placaTblCol;
    
    @FXML
    private TableColumn<Zaposlenici, String> kimeTblCol;
    
    @FXML
    private TableColumn<Zaposlenici, String> lozinkaTblCol;
    
    @FXML
    private TableColumn<Zaposlenici, Integer> idTblCol;
    
    
 
    public void spremi_zaposlenika(ActionEvent e) throws ParseException{
        try{   
            String imeTxt=imeTF.getText();
            String prezimeTxt=prezimeTF.getText();
            String telefonTxt=brojtelTF.getText();
            Float placaTxt=Float.parseFloat(placaTF.getText());
            String kimeTxt=kimeTF.getText();
            String lozinkaTxt=lozinkaTF.getText();
            
            
            Zaposlenici z=new Zaposlenici(imeTxt, prezimeTxt,telefonTxt,placaTxt,kimeTxt,lozinkaTxt);
            z.spasi();
            this.initialize(null, null);
        
        }catch(RuntimeException exc) {
            JOptionPane.showMessageDialog(null, "Nastala je greška. Unesite sve podatke!");
        } 

    }
    
    public void obrisi_zaposlenika(ActionEvent e){
        try{
            Zaposlenici z1=new Zaposlenici();
            z1.setId_zaposlenika(Integer.parseInt(idTF.getText()));
            z1.brisi();
            this.initialize(null, null);
           
        }catch(RuntimeException exc) {
                    JOptionPane.showMessageDialog(null, "Nastala je greška. Odaberite zaposlenika!");
        }
    }
    
    public void uredi_zaposlenika(ActionEvent e) throws ParseException{
        try {
            
            Zaposlenici z1 =new Zaposlenici();
            z1.setId_zaposlenika(Integer.parseInt(idTF.getText()));
            z1.setIme(imeTF.getText());
            z1.setPrezime(prezimeTF.getText());
            z1.setBroj_telefona(brojtelTF.getText());
            z1.setPlaca(Float.parseFloat(placaTF.getText()));
            z1.setKorisnicko_ime(kimeTF.getText());
            z1.setLozinka(lozinkaTF.getText());
            
            z1.uredi();
            this.initialize(null, null);
            
         }catch(RuntimeException exc) {
                    JOptionPane.showMessageDialog(null, "Nastala je greška. Unesite sve podatke!");
         }
        
    }
    
    public void izracunajDnevniPromet(ActionEvent e){
        try{
            int id=Integer.parseInt(idTF.getText());
            ObservableList<Karte> karte;
            karte = Karte.uzmiSveProdaneKarteZaposlenika(id);
            float cijena=0.00f;
            float popust=0.00f;
            float promet=0.00f;
            float ukupno=0.00f;
            for(Karte k:karte){
                 cijena =k.getCijena();
                 popust =k.getPopust();
                 promet=cijena-(cijena*popust);
                 ukupno +=promet;
            }
            dnevniprometLabel.setText(String.valueOf(ukupno) + " KM");
        
        }catch(RuntimeException exc) {
                    JOptionPane.showMessageDialog(null, "Nastala je greška. Odaberite zaposlenika!");
        }
        
    }
    
    public void izracunajProsjecnuPlacu(){
        ObservableList<Zaposlenici> zaposlenici=Zaposlenici.uzmiSveZaposlenike();
        float placa=0.0f;
        int brojac=0;
        float prosjek=0.0f;
        for(Zaposlenici z:zaposlenici){
            placa+=z.getPlaca();
            brojac++;
            prosjek=placa/brojac;
        }
        prosjecnaplacaLabel.setText(String.valueOf(prosjek) + " KM");
    }
    
    private void setCellValueFromTable() {
        zaposleniciTbl.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                Zaposlenici z=zaposleniciTbl.getItems().get(zaposleniciTbl.getSelectionModel().getSelectedIndex());
                idTF.setText(String.valueOf(z.getId_zaposlenika()));
                imeTF.setText(z.getIme());
                prezimeTF.setText(z.getPrezime());
                brojtelTF.setText(z.getBroj_telefona());
                placaTF.setText(String.valueOf(z.getPlaca()));
                kimeTF.setText(z.getKorisnicko_ime());
                lozinkaTF.setText(z.getLozinka());
                
            }
        
        });
    }
    
 
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            
         ObservableList<Zaposlenici> data=Zaposlenici.uzmiSveZaposlenike();
   
        
         imeTblCol.setCellValueFactory(new PropertyValueFactory<Zaposlenici, String>("Ime"));
         prezimeTblCol.setCellValueFactory(new PropertyValueFactory<Zaposlenici, String>("Prezime"));
         brojtelTblCol.setCellValueFactory(new PropertyValueFactory<Zaposlenici, String>("Broj_telefona"));
         placaTblCol.setCellValueFactory(new PropertyValueFactory<Zaposlenici, Float>("Placa"));
         kimeTblCol.setCellValueFactory(new PropertyValueFactory<Zaposlenici, String>("Korisnicko_ime"));
         lozinkaTblCol.setCellValueFactory(new PropertyValueFactory<Zaposlenici, String>("Lozinka"));
         idTblCol.setCellValueFactory(new PropertyValueFactory<Zaposlenici, Integer>("Id_zaposlenika"));
         zaposleniciTbl.setItems(data);
         setCellValueFromTable();
         
        
    }    
    
}
