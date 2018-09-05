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
public class AutobuskaLinija extends Baza {
    public static final int ID=1;
    public static final int MJESTO_POLASKA=2;
    public static final int MJESTO_DOLASKA=3;
    public static final int CIJENA=4;
    public static final int NAZIV_LINIJE=5;
   
    
    private int id_linije;
    private String mjesto_polaska;
    private String mjesto_dolaska;
    private float cijena;
    private String naziv_linije;

    public AutobuskaLinija() {
        this.id_linije=0;
        this.mjesto_polaska="";
        this.mjesto_dolaska="";
        this.cijena=0.0f;
        this.naziv_linije="";
    }

    public AutobuskaLinija(int id_linije, String mjesto_polaska, String mjesto_dolaska, float cijena, String naziv_linije) {
        this.id_linije = id_linije;
        this.mjesto_polaska = mjesto_polaska;
        this.mjesto_dolaska = mjesto_dolaska; 
        this.cijena = cijena;
        this.naziv_linije=naziv_linije;
    }

    public AutobuskaLinija(String mjesto_polaska, String mjesto_dolaska, float cijena, String naziv_linije) {
        this.mjesto_polaska = mjesto_polaska;
        this.mjesto_dolaska = mjesto_dolaska;
        this.cijena = cijena;
        this.naziv_linije = naziv_linije;
    }
    
    

    public int getId_linije() {
        return id_linije;
    }

    public void setId_linije(int id_linije) {
        this.id_linije = id_linije;
    }

    public String getMjesto_polaska() {
        return mjesto_polaska;
    }

    public void setMjesto_polaska(String mjesto_polaska) {
        this.mjesto_polaska = mjesto_polaska;
    }

    public String getMjesto_dolaska() {
        return mjesto_dolaska;
    }

    public void setMjesto_dolaska(String mjesto_dolaska) {
        this.mjesto_dolaska = mjesto_dolaska;
    }

    public float getCijena() {
        return cijena;
    }

    public void setCijena(float cijena) {
        this.cijena = cijena;
    }

    public String getNaziv_linije() {
        return naziv_linije;
    }

    public void setNaziv_linije(String naziv_linije) {
        this.naziv_linije = naziv_linije;
    }

    
    @Override
    public void spasi() {
        Baza.spoji();
        
        try {
            PreparedStatement statement=connection.prepareStatement("INSERT INTO autobuska_linija VALUES(NULL,?,?,?,?)");
            statement.setString(1,this.mjesto_polaska);
            statement.setString(2,this.mjesto_dolaska);
            statement.setFloat(3,this.cijena);
            statement.setString(4,this.naziv_linije);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Nastala je greska prilikom ubacivanja u bazu: " + ex.getMessage());
        }
        
        Baza.odspoji();
    }

    @Override
    public void uredi() {
        try {
            Baza.spoji();
            PreparedStatement st=connection.prepareStatement("UPDATE autobuska_linija SET mjesto_polaska=?, mjesto_dolaska=?, "
                    + "cijena=?, naziv_linije=? WHERE id_linije=?");
            st.setString(1, this.mjesto_polaska);
            st.setString(2, this.mjesto_dolaska);
            st.setFloat(3,this.cijena);
            st.setString(4,this.naziv_linije);
            st.setInt(5, this.id_linije);
            
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
            
            PreparedStatement ps=connection.prepareStatement("DELETE FROM autobuska_linija WHERE id_linije = ?");
            ps.setInt(1, this.id_linije);
            ps.executeUpdate();        
            
            Baza.odspoji();
        } catch (SQLException ex) {
            System.out.println("Nastala je greska prilikom izvrsavanja upita: "+ex.getMessage());
        }
    }
    
    public static ObservableList<AutobuskaLinija> uzmiSveLinije () {
        ObservableList<AutobuskaLinija> linije = FXCollections.observableArrayList();
        try {
            Baza.spoji();
            PreparedStatement st= connection.prepareStatement("SELECT * FROM autobuska_linija");
            
            
            ResultSet rs= st.executeQuery();
            
            
            while (rs.next()) {
                AutobuskaLinija l;
                l = new AutobuskaLinija(rs.getInt(ID),rs.getString(MJESTO_POLASKA),
                        rs.getString(MJESTO_DOLASKA),rs.getFloat(CIJENA),rs.getString(NAZIV_LINIJE));
                
                linije.add(l);
            }
            Baza.odspoji();
            
        } catch (SQLException ex) {
            Logger.getLogger(Zaposlenici.class.getName()).log(Level.SEVERE, null, ex);
        }
        return linije;
    } 
    
    @Override
    public String toString(){
        return this.naziv_linije;
    }
   
}
