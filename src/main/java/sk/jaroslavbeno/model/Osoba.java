package sk.jaroslavbeno.model;

import sk.jaroslavbeno.converters.PohlavieConverter;
import sk.jaroslavbeno.model.enums.Pohlavie;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Osoba extends Obcan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = PohlavieConverter.class)
    private Pohlavie pohlavie;

    @Embedded
    private Meno meno;

    @OneToMany(mappedBy = "osoba")
    private List<Telefon> telefony = new ArrayList<>();

    public Osoba() {
    }

    public Osoba(Pohlavie pohlavie) {
        this.pohlavie = pohlavie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pohlavie getPohlavie() {
        return pohlavie;
    }

    public void setPohlavie(Pohlavie pohlavie) {
        this.pohlavie = pohlavie;
    }

    public Meno getMeno() {
        return meno;
    }

    public void setMeno(Meno meno) {
        this.meno = meno;
    }

    @Override
    public String toString() {
        return "Osoba{" +
                "id=" + id +
                ", pohlavie=" + pohlavie +
                ", meno=" + meno +
                ", telefony=" + telefony +
                "} " + super.toString();
    }

    public List<Telefon> getTelefony() {
        return telefony;
    }

    public void setTelefony(List<Telefon> telefony) {
        this.telefony = telefony;
    }

}

