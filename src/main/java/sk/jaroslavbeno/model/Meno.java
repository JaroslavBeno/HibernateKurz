package sk.jaroslavbeno.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Meno {
    @Column(name = "titul_pred")
    private String titulPred;

    @Column(name = "titul_za")
    private String titulZa;

    @Column(name = "prve_meno")
    private String prveMeno;

    @Column(name = "stredne_meno")
    private String stredneMeno;

    private String priezvisko;

    public Meno() {
    }

    public Meno(String titulPred, String titulZa, String prveMeno, String stredneMeno, String priezvisko) {
        this.titulPred = titulPred;
        this.titulZa = titulZa;
        this.prveMeno = prveMeno;
        this.stredneMeno = stredneMeno;
        this.priezvisko = priezvisko;
    }

    public String getTitulPred() {
        return titulPred;
    }

    public void setTitulPred(String titulPred) {
        this.titulPred = titulPred;
    }

    public String getTitulZa() {
        return titulZa;
    }

    public void setTitulZa(String titulZa) {
        this.titulZa = titulZa;
    }

    public String getPrveMeno() {
        return prveMeno;
    }

    public void setPrveMeno(String prveMeno) {
        this.prveMeno = prveMeno;
    }

    public String getStredneMeno() {
        return stredneMeno;
    }

    public void setStredneMeno(String stredneMeno) {
        this.stredneMeno = stredneMeno;
    }

    public String getPriezvisko() {
        return priezvisko;
    }

    public void setPriezvisko(String priezvisko) {
        this.priezvisko = priezvisko;
    }

    @Override
    public String toString() {
        return "Meno{" +
                "titulPred='" + titulPred + '\'' +
                ", titulZa='" + titulZa + '\'' +
                ", prveMeno='" + prveMeno + '\'' +
                ", stredneMeno='" + stredneMeno + '\'' +
                ", priezvisko='" + priezvisko + '\'' +
                '}';
    }

    //    ALTER TABLE Osoba ADD COLUMN titul_pred nvarchar(15);
//    ALTER TABLE Osoba ADD COLUMN titul_za nvarchar(15);
//    ALTER TABLE Osoba ADD COLUMN prve_meno nvarchar(100);
//    ALTER TABLE Osoba ADD COLUMN stredne_meno nvarchar(100);
//    ALTER TABLE Osoba ADD COLUMN priezvisko nvarchar(100);

}
