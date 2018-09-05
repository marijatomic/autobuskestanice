/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autobuskestanice.model;

import static autobuskestanice.model.AutobuskaLinija.uzmiSveLinije;
import static autobuskestanice.model.Baza.connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
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
public class Voznje extends Baza {
    public static final int ID=1;
    public static final int VRIJEME_POLASKA=2;
    public static final int VRIJEME_DOLASKA=3;
    public static final int BROJ_SJEDISTA=4;
    public static final int ID_LINIJE=5;
    public static final int NAZIV_LINIJE=6;
    public static final int CIJENA=7;
    
    private int id_voznje;
    private String vrijeme_polaska;
    private String vrijeme_dolaska;
    private int broj_sjedista;
    private int id_linije;
    private String naziv_linije;
    private float cijena;

    public Voznje() {
        this.id_voznje=0;
        this.vrijeme_polaska="";
        this.vrijeme_dolaska="";
        this.broj_sjedista=0;
        this.id_linije=0;
    }

    public Voznje(int id_voznje, String vrijeme_polaska, String vrijeme_dolaska, int broj_sjedista, int id_linije) {
        this.id_voznje = id_voznje;
        this.vrijeme_polaska = vrijeme_polaska;
        this.vrijeme_dolaska = vrijeme_dolaska;
        this.broj_sjedista = broj_sjedista;
        this.id_linije = id_linije;
    }

    public Voznje(int id_voznje, String vrijeme_polaska, String vrijeme_dolaska, int broj_sjedista, int id_linije, String naziv_linije, float cijena) {
        this.id_voznje = id_voznje;
        this.vrijeme_polaska = vrijeme_polaska;
        this.vrijeme_dolaska = vrijeme_dolaska;
        this.broj_sjedista = broj_sjedista;
        this.id_linije = id_linije;
        this.naziv_linije = naziv_linije;
        this.cijena = cijena;
    }

    public Voznje(String vrijeme_polaska, String vrijeme_dolaska, int broj_sjedista, int id_linije) {
        this.vrijeme_polaska = vrijeme_polaska;
        this.vrijeme_dolaska = vrijeme_dolaska;
        this.broj_sjedista = broj_sjedista;
        this.id_linije = id_linije;
    }
    
    
    
    

    public int getId_voznje() {
        return id_voznje;
    }

    public void setId_voznje(int id_voznje) {
        this.id_voznje = id_voznje;
    }

    public String getVrijeme_polaska() {
        return vrijeme_polaska;
    }

    public void setVrijeme_polaska(String vrijeme_polaska) {
        this.vrijeme_polaska = vrijeme_polaska;
    }

    public String getVrijeme_dolaska() {
        return vrijeme_dolaska;
    }

    public void setVrijeme_dolaska(String vrijeme_dolaska) {
        this.vrijeme_dolaska = vrijeme_dolaska;
    }

    public int getBroj_sjedista() {
        return broj_sjedista;
    }

    public void setBroj_sjedista(int broj_sjedista) {
        this.broj_sjedista = broj_sjedista;
    }

    public int getId_linije() {
        return id_linije;
    }

    public void setId_linije(int id_linije) {
        this.id_linije = id_linije;
    }

    public String getNaziv_linije() {
        return naziv_linije;
    }

    public void setNaziv_linije(String naziv_linije) {
        this.naziv_linije = naziv_linije;
    }

    

    public float getCijena() {
        return cijena;
    }

    public void setCijena(float cijena) {
        this.cijena = cijena;
    }
    
    

    @Override
    public void spasi() {
        try {
            Baza.spoji();
            
            PreparedStatement statement=connection.prepareStatement("INSERT INTO voznje VALUES(NULL,?,?,?,?)");
            statement.setString(1,this.vrijeme_polaska);
            statement.setString(2,this.vrijeme_dolaska);
            statement.setInt(3,this.broj_sjedista);
            statement.setInt(4,this.id_linije);
            
            
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
            PreparedStatement st=connection.prepareStatement("UPDATE voznje SET vrijeme_polaska=?, vrijeme_dolaska=?, "
                    + "broj_sjedista=?, id_linije=? WHERE id_voznje=?");
            st.setString(1, this.vrijeme_polaska);
            st.setString(2, this.vrijeme_dolaska);
            st.setInt(3, this.broj_sjedista);
            st.setInt(4, this.id_linije);
            st.setInt(5, this.id_voznje);
            
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
            
            PreparedStatement ps=connection.prepareStatement("DELETE FROM voznje WHERE id_voznje = ?");
            ps.setInt(1, this.id_voznje);
            ps.executeUpdate();        
            
            Baza.odspoji();
        } catch (SQLException ex) {
            System.out.println("Nastala je greska prilikom izvrsavanja upita: "+ex.getMessage());
        }
    }
    
    /*public String dajVoznja_linija () {
        ObservableList <AutobuskaLinija> linija = uzmiSveLinije();
        for (AutobuskaLinija a : linija) {
            if (a.getId_linije() == this.getId_linije()) return a.getNaziv_linije();
        }
        return null;
    }*/
    
    public static ObservableList<Voznje> uzmiSveVoznje () {
        ObservableList<Voznje> voznje = FXCollections.observableArrayList();
      
        try {
            Baza.spoji();
            PreparedStatement st= connection.prepareStatement("SELECT voznje.id_voznje,"
                    + "voznje.vrijeme_polaska, voznje.vrijeme_dolaska, voznje.broj_sjedista,voznje.id_linije, "
                    + "autobuska_linija.naziv_linije,autobuska_linija.cijena\n" 
                    +"FROM voznje\n" 
                    +"LEFT JOIN autobuska_linija ON "
                    + "voznje.id_linije = autobuska_linija.id_linije;");
            
            
            ResultSet rs= st.executeQuery();
            
            
            while (rs.next()) {
                Voznje v;
                
                v = new Voznje(rs.getInt(ID),rs.getString(VRIJEME_POLASKA),
                        rs.getString(VRIJEME_DOLASKA),rs.getInt(BROJ_SJEDISTA),
                        rs.getInt(ID_LINIJE), rs.getString(NAZIV_LINIJE),rs.getFloat(CIJENA));
                
                voznje.add(v);
               
            }
            Baza.odspoji();
            
        } catch (SQLException ex) {
            Logger.getLogger(Zaposlenici.class.getName()).log(Level.SEVERE, null, ex);
        }
        return voznje;
    }
    
    @Override
    public String toString(){
        
        String result=this.naziv_linije + ": " + this.vrijeme_polaska + " - " + this.vrijeme_dolaska;
        return result;  
    }
   
}
