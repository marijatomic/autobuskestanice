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
public class Karte extends Baza {
    public static final int ID=1;
    public static final int DATUM_IZDAVANJA=2;
    public static final int VRIJEME_IZDAVANJA=3;
    public static final int MJESTO_POLASKA=4;
    public static final int MJESTO_DOLASKA=5;
    public static final int VRIJEME_POLASKA=6;
    public static final int VRIJEME_DOLASKA=7;
    public static final int SJEDISTE=8;
    public static final int CIJENA=9;
    public static final int NAZIV_VRSTE=10;
    public static final int POPUST=11;
    public static final int IME=12;
    public static final int PREZIME=13;
    public static final int TIP=14;
    
    
    private int id_karte;
    private String datum_izdavanja;
    private String vrijeme_izdavanja;
    private String mjesto_polaska;
    private String mjesto_dolaska;
    private String vrijeme_polaska;
    private String vrijeme_dolaska;
    private int sjediste;
    private float cijena;
    private String naziv_vrste;
    private float popust;
    private String ime;
    private String prezime;
    private int id_zaposlenika;
    private int id_voznje;
    private int id_vrste;
    private String tip;

    public Karte() {
        this.id_karte=0;
        this.datum_izdavanja="";
        this.vrijeme_izdavanja="";
        this.sjediste=0;
        this.id_zaposlenika=0;
        this.id_voznje=0;
        this.id_vrste=0;
        this.tip="";
    }

    public Karte(int id_karte, String datum_izdavanja, String vrijeme_izdavanja, int sjediste, int id_zaposlenika, int id_voznje, int id_vrste, String tip) {
        this.id_karte = id_karte;
        this.datum_izdavanja = datum_izdavanja;
        this.vrijeme_izdavanja = vrijeme_izdavanja;
        this.sjediste = sjediste;
        this.id_zaposlenika = id_zaposlenika;
        this.id_voznje = id_voznje;
        this.id_vrste = id_vrste;
        this.tip=tip;
    }
    
    public Karte(String datum_izdavanja, String vrijeme_izdavanja, int sjediste, int id_zaposlenika, int id_voznje, int id_vrste, String tip) {
        this.datum_izdavanja = datum_izdavanja;
        this.vrijeme_izdavanja = vrijeme_izdavanja;
        this.sjediste = sjediste;
        this.id_zaposlenika = id_zaposlenika;
        this.id_voznje = id_voznje;
        this.id_vrste = id_vrste;
        this.tip=tip;
    }

    public Karte(int id_karte, String datum_izdavanja, String vrijeme_izdavanja, String mjesto_polaska, String mjesto_dolaska, String vrijeme_polaska, String vrijeme_dolaska, int sjediste, float cijena, String naziv_vrste, float popust, String ime, String prezime, String tip) {
        this.id_karte = id_karte;
        this.datum_izdavanja = datum_izdavanja;
        this.vrijeme_izdavanja = vrijeme_izdavanja;
        this.mjesto_polaska = mjesto_polaska;
        this.mjesto_dolaska = mjesto_dolaska;
        this.vrijeme_polaska = vrijeme_polaska;
        this.vrijeme_dolaska = vrijeme_dolaska;
        this.sjediste = sjediste;
        this.cijena = cijena;
        this.naziv_vrste = naziv_vrste;
        this.popust = popust;
        this.ime = ime;
        this.prezime = prezime;
        this.tip=tip;
    }

    public Karte(float cijena, float popust) {
        this.cijena = cijena;
        this.popust = popust;
        
    }
    
    

    public int getId_karte() {
        return id_karte;
    }

    public void setId_karte(int id_karte) {
        this.id_karte = id_karte;
    }

    public String getDatum_izdavanja() {
        return datum_izdavanja;
    }

    public void setDatum_izdavanja(String datum_izdavanja) {
        this.datum_izdavanja = datum_izdavanja;
    }

    public String getVrijeme_izdavanja() {
        return vrijeme_izdavanja;
    }

    public void setVrijeme_izdavanja(String vrijeme_izdavanja) {
        this.vrijeme_izdavanja = vrijeme_izdavanja;
    }

    public int getSjediste() {
        return sjediste;
    }

    public void setSjediste(int sjediste) {
        this.sjediste = sjediste;
    }

    public int getId_zaposlenika() {
        return id_zaposlenika;
    }

    public void setId_zaposlenika(int id_zaposlenika) {
        this.id_zaposlenika = id_zaposlenika;
    }

    public int getId_voznje() {
        return id_voznje;
    }

    public void setId_voznje(int id_voznje) {
        this.id_voznje = id_voznje;
    }

    public int getId_vrste() {
        return id_vrste;
    }

    public void setId_vrste(int id_vrste) {
        this.id_vrste = id_vrste;
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

    public float getCijena() {
        return cijena;
    }

    public void setCijena(float cijena) {
        this.cijena = cijena;
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

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
    
    

    @Override
    public void spasi() {
        try {
            Baza.spoji();
            
            PreparedStatement statement=connection.prepareStatement("INSERT INTO karte VALUES(NULL,?,?,?,?,?,?,?)");
            statement.setString(1,this.datum_izdavanja);
            statement.setString(2,this.vrijeme_izdavanja);
            statement.setInt(3,this.sjediste);
            statement.setInt(4,this.id_zaposlenika);
            statement.setInt(5,this.id_voznje);
            statement.setInt(6,this.id_vrste);
            statement.setString(7,this.tip);
            
            
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
            PreparedStatement st=connection.prepareStatement("UPDATE karte SET datum_izdavanja=?, vrijeme_izdavanja=?, "
                    + "sjediste=?, id_zaposlenika=?, id_voznje=?, id_vrste=? WHERE id_karte=?");
            st.setString(1,this.datum_izdavanja);
            st.setString(2,this.vrijeme_izdavanja);
            st.setInt(3,this.sjediste);
            st.setInt(4,this.id_zaposlenika);
            st.setInt(5,this.id_voznje);
            st.setInt(6,this.id_vrste);
            st.setInt(7,this.id_karte);
            
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
            
            PreparedStatement ps=connection.prepareStatement("DELETE FROM karte WHERE id_karte = ?");
            ps.setInt(1, this.id_karte);
            ps.executeUpdate();        
            
            Baza.odspoji(); 
        } catch (SQLException ex) {
            System.out.println("Nastala je greska prilikom izvrsavanja upita: "+ex.getMessage());
        }
    }
    
   
    public void otkazi_rezervaciju() {
        try {
            Baza.spoji();
            
            PreparedStatement ps=connection.prepareStatement("DELETE FROM karte WHERE id_karte = ? AND tip = ?");
            ps.setInt(1, this.id_karte);
            String tip="Rezervirana";
            ps.setString(2, tip);
            ps.executeUpdate();        
            
            Baza.odspoji(); 
        } catch (SQLException ex) {
            System.out.println("Nastala je greska prilikom izvrsavanja upita: "+ex.getMessage());
        }
    }
    
    public static ObservableList<Karte> uzmiSveKarte () {
        ObservableList<Karte> karte = FXCollections.observableArrayList();
      
        try {
            Baza.spoji();
            PreparedStatement st= connection.prepareStatement("SELECT karte.id_karte, karte.datum_izdavanja, karte.vrijeme_izdavanja, autobuska_linija.mjesto_polaska, autobuska_linija.mjesto_dolaska, voznje.vrijeme_polaska, voznje.vrijeme_dolaska, karte.sjediste, autobuska_linija.cijena, vrste_karti.naziv_vrste, vrste_karti.popust, zaposlenici.ime, zaposlenici.prezime, karte.tip\n" +
            "FROM karte\n" +
            "LEFT JOIN zaposlenici ON karte.id_zaposlenika=zaposlenici.id_zaposlenika\n" +
            "\n" +
            "LEFT JOIN vrste_karti ON karte.id_vrste=vrste_karti.id_vrste\n" +
            "\n" +
            "LEFT JOIN voznje ON karte.id_voznje=voznje.id_voznje\n"+
            "LEFT JOIN autobuska_linija ON voznje.id_linije=autobuska_linija.id_linije \n" +
            "GROUP BY karte.id_karte;");
            
            
            ResultSet rs= st.executeQuery();
            
            
            while (rs.next()) {
                Karte k;
                
                k = new Karte(rs.getInt(ID),rs.getString(DATUM_IZDAVANJA),rs.getString(VRIJEME_IZDAVANJA),rs.getString(MJESTO_POLASKA),
                rs.getString(MJESTO_DOLASKA),rs.getString(VRIJEME_POLASKA),rs.getString(VRIJEME_DOLASKA),rs.getInt(SJEDISTE),
                rs.getFloat(CIJENA),rs.getString(NAZIV_VRSTE),rs.getFloat(POPUST),rs.getString(IME),rs.getString(PREZIME), rs.getString(TIP));
                
                karte.add(k);
               
            }
            Baza.odspoji();
            
        } catch (SQLException ex) {
            Logger.getLogger(Zaposlenici.class.getName()).log(Level.SEVERE, null, ex);
        }
        return karte;
    }
    
    public static ObservableList<Karte> uzmiSveKarteZaposlenika (int id) {
        ObservableList<Karte> karte = FXCollections.observableArrayList();
        try {
           
            Baza.spoji();
         PreparedStatement st= connection.prepareStatement("SELECT karte.id_karte, karte.datum_izdavanja, karte.vrijeme_izdavanja, autobuska_linija.mjesto_polaska, autobuska_linija.mjesto_dolaska, voznje.vrijeme_polaska, voznje.vrijeme_dolaska, karte.sjediste, autobuska_linija.cijena, vrste_karti.naziv_vrste, vrste_karti.popust, zaposlenici.ime, zaposlenici.prezime, karte.tip\n" +
            "FROM karte\n" +
            "LEFT JOIN zaposlenici ON karte.id_zaposlenika=zaposlenici.id_zaposlenika\n" +
            "\n" +
            "LEFT JOIN vrste_karti ON karte.id_vrste=vrste_karti.id_vrste\n" +
            "\n" +
            "LEFT JOIN voznje ON karte.id_voznje=voznje.id_voznje\n"+
            "LEFT JOIN autobuska_linija ON voznje.id_linije=autobuska_linija.id_linije \n" +
            "WHERE karte.id_zaposlenika=? ");
            
            st.setInt(1,id);
          
            ResultSet rs= st.executeQuery();
            while (rs.next()) {
                Karte k;
                k = new Karte(rs.getInt(ID),rs.getString(DATUM_IZDAVANJA),rs.getString(VRIJEME_IZDAVANJA),rs.getString(MJESTO_POLASKA),
                rs.getString(MJESTO_DOLASKA),rs.getString(VRIJEME_POLASKA),rs.getString(VRIJEME_DOLASKA),rs.getInt(SJEDISTE),
                rs.getFloat(CIJENA),rs.getString(NAZIV_VRSTE),rs.getFloat(POPUST),rs.getString(IME),rs.getString(PREZIME), rs.getString(TIP));
                karte.add(k);
            }
            Baza.odspoji();
            
        } catch (SQLException ex) {
           System.out.println("Nastala je greska prilikom izvrsavanja upita: "+ex.getMessage());
        }
        return karte;
    }
    
    public static ObservableList<Karte> uzmiSveProdaneKarteZaposlenika (int id) {
        ObservableList<Karte> karte = FXCollections.observableArrayList();
        try {
           
            Baza.spoji();
         PreparedStatement st= connection.prepareStatement("SELECT karte.id_karte, karte.datum_izdavanja, karte.vrijeme_izdavanja, autobuska_linija.mjesto_polaska, autobuska_linija.mjesto_dolaska, voznje.vrijeme_polaska, voznje.vrijeme_dolaska, karte.sjediste, autobuska_linija.cijena, vrste_karti.naziv_vrste, vrste_karti.popust, zaposlenici.ime, zaposlenici.prezime, karte.tip\n" +
            "FROM karte\n" +
            "LEFT JOIN zaposlenici ON karte.id_zaposlenika=zaposlenici.id_zaposlenika\n" +
            "\n" +
            "LEFT JOIN vrste_karti ON karte.id_vrste=vrste_karti.id_vrste\n" +
            "\n" +
            "LEFT JOIN voznje ON karte.id_voznje=voznje.id_voznje\n"+
            "LEFT JOIN autobuska_linija ON voznje.id_linije=autobuska_linija.id_linije \n" +
            "WHERE karte.id_zaposlenika=? AND karte.tip=?;");
            
            st.setInt(1,id);
            String tip="Prodana";
            st.setString(2,tip);
            ResultSet rs= st.executeQuery();
            while (rs.next()) {
                Karte k;
                k = new Karte(rs.getInt(ID),rs.getString(DATUM_IZDAVANJA),rs.getString(VRIJEME_IZDAVANJA),rs.getString(MJESTO_POLASKA),
                rs.getString(MJESTO_DOLASKA),rs.getString(VRIJEME_POLASKA),rs.getString(VRIJEME_DOLASKA),rs.getInt(SJEDISTE),
                rs.getFloat(CIJENA),rs.getString(NAZIV_VRSTE),rs.getFloat(POPUST),rs.getString(IME),rs.getString(PREZIME), rs.getString(TIP));
                karte.add(k);
            }
            Baza.odspoji();
            
        } catch (SQLException ex) {
           System.out.println("Nastala je greska prilikom izvrsavanja upita: "+ex.getMessage());
        }
        return karte;
    }
    
    public static float uzmiCijenu (int id){
        Float cijena=0.0f;
        try {
            Baza.spoji();
            PreparedStatement st= connection.prepareStatement("SELECT * FROM voznje LEFT JOIN autobuska_linija ON voznje.id_linije=autobuska_linija.id_linije WHERE voznje.id_voznje=?;");
            st.setInt(1,id);
            
            ResultSet rs= st.executeQuery();
            while (rs.next()) {
                cijena=rs.getFloat(CIJENA);
            }
            Baza.odspoji();
        } catch (SQLException ex) {
            System.out.println("Nastala je greska prilikom izvrsavanja upita: "+ex.getMessage());
        }
        return cijena;
    }
    
    public static int brojKarti(int id){
        int brojKarti=0;
        try {
            Baza.spoji();
            PreparedStatement st=connection.prepareStatement("SELECT COUNT(id_karte) FROM karte WHERE karte.id_voznje=?;");
            st.setInt(1, id);
            
            ResultSet rs= st.executeQuery();
            while (rs.next()) {
                brojKarti=rs.getInt(ID);
            }
            
            Baza.odspoji();
        } catch (SQLException ex) {
            Logger.getLogger(Karte.class.getName()).log(Level.SEVERE, null, ex);
        }
        return brojKarti;
    }
    
}
