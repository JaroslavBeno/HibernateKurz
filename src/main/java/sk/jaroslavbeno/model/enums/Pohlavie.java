package sk.jaroslavbeno.model.enums;

public enum Pohlavie {
    MUZ('M'),
    ZENA('Z');

    private char kod;

    Pohlavie(char kod) {
        this.kod = kod;
    }

    public static Pohlavie getEnumFromKod(Character kod) {
        if(kod.equals('M') || kod.equals('m')){
            return MUZ;
        }

        if(kod.equals('Z') || kod.equals('z')){
            return ZENA;
        }

        throw new UnsupportedOperationException("Pre daný kód "+kod+" neexistuje enum typu Pohlavie");
    }

    public char getKod() {
        return kod;
    }
}

//@Enumerated(EnumType.ORDINAL)
//@Enumerated(EnumType.STRING)
//
//AttributeConverter<X,Y>.
//
//@Convert( converter = PohlavieConverter.class )