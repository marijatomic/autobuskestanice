/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autobuskestanice.model;

import static autobuskestanice.model.Baza.connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author marij
 */
public class VrsteKarti extends Baza {
    public static final int ID=1;
    public static final int NAZIV_VRSTE=2;
    public static final int POPUST=3;
    
    private int id_vrste;
    private String naziv_vrste;
    private float popust;

    public VrsteKarti() {
        this.id_vrste=0;
        this.naziv_vrste="";
        this.popust=0.0f;
    }

    public VrsteKarti(int id_vrste, String naziv_vrste, float popust) {
        this.id_vrste = id_vrste;
        this.naziv_vrste = naziv_vrste;
        this.popust = popust;
    }

    public VrsteKarti(String naziv_vrste, float popust) {
        this.naziv_vrste = naziv_vrste;
        this.popust = popust;
    }
    
    

    public int getId_vrste() {
        return id_vrste;
    }

    public void setId_vrste(int id_vrste) {
        this.id_vrste = id_vrste;
    }

    public String getNaziv_vrste() {
        return naziv_vrste;
    }

    public void setNaziv_vrste(String naziv_vrste) {
        this.naziv_vrste = naziv_vrste;
    }

    public float getPopust() {
        return popust;
    }

    public void setPopust(float popust) {
        this.popust = popust;
    }

    @Override
    public void spasi() {
        try {
            Baza.spoji();
            
            PreparedStatement statement=connection.prepareStatement("INSERT INTO vrste_karti VALUES(NULL,?,?)");
            statement.setString(1,this.naziv_vrste);
            statement.setFloat(2,this.popust);
            
            
            statement.executeUpdate();
            
            Baza.odspoji();
        } catch (SQLException ex) {
            System.out.println("Nastala je greska prilikom ubacivanja u bazu: " + ex.getMessage());
        }
    }

    @Override
    public void uredi() {
        try {
            Baza.spoji();
            PreparedStatement st=connection.prepareStatement("UPDATE vrste_karti SET naziv_vrste=?, popust=? WHERE id_vrste=?");
            st.setString(1, this.naziv_vrste);
            st.setFloat(2, this.popust);
            st.setInt(3, this.id_vrste);
            
            st.executeUpdate();
            Baza.odspoji();
        } catch (SQLException ex) {
            System.out.println("Nastala je greska prilikom izvrsavanja upita: "+ex.getMessage());
        }
    }

    @Override
    public void brisi() {
        try {
            Baza.spoji();
            
            PreparedStatement ps=connection.prepareStatement("DELETE FROM vrste_karti WHERE id_vrste = ?");
            ps.setInt(1, this.id_vrste);
            ps.executeUpdate();        
            
            Baza.odspoji();
        } catch (SQLException ex) {
            System.out.println("Nastala je greska prilikom izvrsavanja upita: "+ex.getMessage());
        }
    }
    
  public static ObservableList<VrsteKarti> uzmiSveVrste () {
        ObservableList<VrsteKarti> vrste = FXCollections.observableArrayList();
        try {
            Baza.spoji();
            PreparedStatement st= connection.prepareStatement("SELECT * FROM vrste_karti");
            
            
            ResultSet rs= st.executeQuery();
            
            
            while (rs.next()) {
               VrsteKarti v;
                v = new VrsteKarti(rs.getInt(ID),rs.getString(NAZIV_VRSTE),rs.getFloat(POPUST));
                
                vrste.add(v);
            }
            Baza.odspoji();
            
        } catch (SQLException ex) {
            Logger.getLogger(Zaposlenici.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vrste;
    } 
  
    public static float uzmiPopust(int id){
        Float popust=0.0f;
        try {
            Baza.spoji();
            PreparedStatement st= connection.prepareStatement("SELECT * FROM vrste_karti WHERE id_vrste=?;");
            st.setInt(1,id);
            
            ResultSet rs= st.executeQuery();
            while (rs.next()) {
                popust=rs.getFloat(POPUST);
            }
            Baza.odspoji();
        } catch (SQLException ex) {
            System.out.println("Nastala je greska prilikom izvrsavanja upita: "+ex.getMessage());
        }
        return popust;
    }
    
    @Override
    public String toString(){
        return this.naziv_vrste;
    }
    
    
    
}
