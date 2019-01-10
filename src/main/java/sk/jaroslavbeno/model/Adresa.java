package sk.jaroslavbeno.model;

import javax.persistence.*;

@Entity
public class Adresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String ulica;
    private String mesto;
    private String psc;

    @OneToOne
    private Osoba osoba;

    public Adresa() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public String getPsc() {
        return psc;
    }

    public void setPsc(String psc) {
        this.psc = psc;
    }

    public Osoba getOsoba() {
        return osoba;
    }

    public void setOsoba(Osoba osoba) {
        this.osoba = osoba;
    }

    /*
    CREATE TABLE Adresa (
  ID int(19) NOT NULL AUTO_INCREMENT,
  ULICA varchar(100) DEFAULT NULL,
  MESTO varchar(100) DEFAULT NULL,
  PSC varchar(100) DEFAULT NULL,
  OSOBA_ID int(19) DEFAULT NULL,
  PRIMARY KEY (`ID`)
);
ALTER TABLE Adresa ADD CONSTRAINT fk_adresa_osoba_id FOREIGN KEY (OSOBA_ID) REFERENCES osoba(ID);

     */
}
