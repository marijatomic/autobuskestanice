/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autobuskestanice.model;

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
public class Zaposlenici extends Baza {
    public static final int ID=1;
    public static final int IME=2;
    public static final int PREZIME=3;
    public static final int BROJ_TELEFONA=4;
    public static final int PLACA=5;
    public static final int KORISNICKO_IME=6;
    public static final int LOZINKA=7;
    
    
    private int id_zaposlenika;
    private String ime;
    private String prezime;
    private String broj_telefona;
    private float placa;
    private String korisnicko_ime;
    private String lozinka;
    
    public Zaposlenici(String korisnicko_ime, String lozinka){
        this.korisnicko_ime=korisnicko_ime;
        this.lozinka=lozinka;
    }

    public Zaposlenici(int id_zaposlenika, String ime, String prezime, String broj_telefona, float placa, String korisnicko_ime, String lozinka) {
        this.id_zaposlenika = id_zaposlenika;
        this.ime = ime;
        this.prezime = prezime;
        this.broj_telefona = broj_telefona;
        this.placa = placa;
        this.korisnicko_ime = korisnicko_ime;
        this.lozinka = lozinka;
    }

    public Zaposlenici(String ime, String prezime, String broj_telefona, float placa, String korisnicko_ime, String lozinka) {
        this.ime = ime;
        this.prezime = prezime;
        this.broj_telefona = broj_telefona;
        this.placa = placa;
        this.korisnicko_ime = korisnicko_ime;
        this.lozinka = lozinka;
    }

    public Zaposlenici() {
    }

    
    
    public int getId_zaposlenika() {
        return id_zaposlenika;
    }

    public void setId_zaposlenika(int id_zaposlenika) {
        this.id_zaposlenika = id_zaposlenika;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getBroj_telefona() {
        return broj_telefona;
    }

    public void setBroj_telefona(String broj_telefona) {
        this.broj_telefona = broj_telefona;
    }

    public float getPlaca() {
        return placa;
    }

    public void setPlaca(float placa) {
        this.placa = placa;
    }

    public String getKorisnicko_ime() {
        return korisnicko_ime;
    }

    public void setKorisnicko_ime(String korisnicko_ime) {
        this.korisnicko_ime = korisnicko_ime;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    @Override
    public void spasi() {
        Baza.spoji();
        try {
           
            
            PreparedStatement statement=connection.prepareStatement("INSERT INTO zaposlenici VALUES(NULL,?,?,?,?,?,?)");
            statement.setString(1,this.ime);
            statement.setString(2,this.prezime);
            statement.setString(3,this.broj_telefona);
            statement.setFloat(4,this.placa);
            statement.setString(5,this.korisnicko_ime);
            statement.setString(6,this.lozinka);
                    
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
            PreparedStatement st=connection.prepareStatement("UPDATE zaposlenici SET ime=?, prezime=?, "
                    + "broj_telefona=?, placa=?, korisnicko_ime=?, lozinka=? WHERE id_zaposlenika = ?");
            st.setString(1, this.ime);
            st.setString(2, this.prezime);
            st.setString(3, this.broj_telefona);
            st.setFloat(4,this.placa);
            st.setString(5, this.korisnicko_ime);
            st.setString(6, this.lozinka);
            st.setInt(7, this.id_zaposlenika);
            
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
            
            PreparedStatement ps=connection.prepareStatement("DELETE FROM zaposlenici WHERE id_zaposlenika = ?");
            ps.setInt(1, this.id_zaposlenika);
            ps.executeUpdate();        
            
            Baza.odspoji();
        } catch (SQLException ex) {
            System.out.println("Nastala je greska prilikom izvrsavanja upita: "+ex.getMessage());
        }
    }
    
    
    public static ObservableList<Zaposlenici> uzmiSveZaposlenike () {
        ObservableList<Zaposlenici> zaposlenici = FXCollections.observableArrayList();
        try {
            Baza.spoji();
            PreparedStatement st= connection.prepareStatement("SELECT * FROM zaposlenici");
            
            
            ResultSet rs= st.executeQuery();
            
            
            while (rs.next()) {
                Zaposlenici z;
                z = new Zaposlenici(rs.getInt(ID),rs.getString(IME),
                        rs.getString(PREZIME),rs.getString(BROJ_TELEFONA),rs.getFloat(PLACA),
                        rs.getString(KORISNICKO_IME),rs.getString(LOZINKA));
                
                zaposlenici.add(z);
            }
            Baza.odspoji();
            
        } catch (SQLException ex) {
            Logger.getLogger(Zaposlenici.class.getName()).log(Level.SEVERE, null, ex);
        }
        return zaposlenici;
    } 
    
    public static boolean logiraj (String kime, String lozinka) {
        Baza.spoji();
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement("SELECT * FROM zaposlenici WHERE korisnicko_ime =? AND lozinka=?");
            ps.setString(1, kime);
            ps.setString(2, lozinka);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
             System.out.println("Nastala je greška: "+ex.getMessage());
            return false;
        }
    }
    
    public int dajID_prijavljenog () {
        try {
            Baza.spoji();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM zaposlenici WHERE korisnicko_ime =? AND "
                    + "lozinka=?");
            
            ps.setString(1, this.korisnicko_ime);
            ps.setString(2, this.lozinka);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id=rs.getInt("id_zaposlenika");
                return id;
            }
            Baza.odspoji();
         
        } catch (SQLException ex) {
            System.out.println("Nastala je greška: "+ex.getMessage());
            
        }
        
        return 0;
       
    }
    
    public static Zaposlenici daj_zaposlenika(int id){
        Zaposlenici zaposlenik=null;
        try {
            Baza.spoji();
            
            PreparedStatement st= connection.prepareStatement("SELECT * FROM zaposlenici WHERE id_zaposlenika=?");
            st.setInt(1, id);
            
            ResultSet rs= st.executeQuery();
            
            
            while(rs.next()){
                zaposlenik=new Zaposlenici(rs.getInt("id_zaposlenika"),rs.getString("ime"),
                        rs.getString("prezime"),rs.getString("broj_telefona"),rs.getFloat("placa"),
                        rs.getString("korisnicko_ime"),rs.getString("lozinka"));
            }
            Baza.odspoji();
        } catch (SQLException ex) {
            System.out.println("Nastala je greska s upitom" + ex.getMessage());
        }
        return zaposlenik;
    }

  
    
    
    
}
