package sk.jaroslavbeno;

import sk.jaroslavbeno.model.*;
import sk.jaroslavbeno.model.enums.Pohlavie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("sk.jaroslavbeno.jpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        deleteSkupina(entityManager);



        entityManager.close();


    }

    private static void deleteSkupina(EntityManager entityManager) {
        entityManager.getTransaction().begin();

        SkupinaKontaktov skupina = entityManager.find(SkupinaKontaktov.class, 1L);
        entityManager.remove(skupina);

        entityManager.getTransaction().commit();
    }


    private static void loadSkupina(EntityManager entityManager) {
        SkupinaKontaktov skupina = entityManager.find(SkupinaKontaktov.class, 1L);
        System.out.println(skupina);
    }


    private static void saveSkupinuAOsoby(EntityManager entityManager) {
        entityManager.getTransaction().begin();

        Osoba osoba = entityManager.find(Osoba.class, 35L);
        Osoba osoba2 = entityManager.find(Osoba.class, 40L);
        Osoba osoba3 = entityManager.find(Osoba.class, 39L);

        SkupinaKontaktov skupina = new SkupinaKontaktov();
        skupina.setNazovSkupiny("Skupna 1");
        skupina.getOsobyVSkupine().add(osoba);
        skupina.getOsobyVSkupine().add(osoba2);

        SkupinaKontaktov skupina2 = new SkupinaKontaktov();
        skupina2.setNazovSkupiny("Skupna 2");
        skupina2.getOsobyVSkupine().add(osoba);
        skupina2.getOsobyVSkupine().add(osoba3);

        entityManager.persist(skupina);
        entityManager.persist(skupina2);

        entityManager.getTransaction().commit();
    }


    private static void saveOsobuTelefony2(EntityManager entityManager) {
        entityManager.getTransaction().begin();

        Osoba osoba = new Osoba();
        osoba.setPohlavie(Pohlavie.ZENA);
        osoba.setMeno(new Meno(null,null, "Janko", null, "Beno"));
        osoba.setCisloOp("X411146D4");

        Telefon telefon = new Telefon();
        telefon.setCislo("0911545111");

        Telefon telefon2 = new Telefon();
        telefon2.setCislo("0912545222");

        osoba.addTelefon(telefon);
        osoba.addTelefon(telefon2);

        entityManager.persist(osoba);
        entityManager.getTransaction().commit();
    }

    private static void saveOsobuTelefony(EntityManager entityManager) {
        entityManager.getTransaction().begin();

        Osoba osoba = new Osoba();
        osoba.setPohlavie(Pohlavie.ZENA);
        osoba.setMeno(new Meno(null,null, "Janko", null, "Beno"));
        osoba.setCisloOp("X411146D4");
        ArrayList<Telefon> telefony = new ArrayList<>();

        Telefon telefon = new Telefon();
        telefon.setCislo("0911545111");
        telefon.setOsoba(osoba);

        Telefon telefon2 = new Telefon();
        telefon2.setCislo("0912545222");
        telefon2.setOsoba(osoba);

        telefony.add(telefon);
        telefony.add(telefon2);
        osoba.setTelefony(telefony);

        entityManager.persist(osoba);
        entityManager.persist(telefon);
        entityManager.persist(telefon2);
        entityManager.getTransaction().commit();
    }


    private static void nacitajOsobuPridajAdresu(EntityManager entityManager) {
        entityManager.getTransaction().begin();

        Osoba osoba = entityManager.find(Osoba.class, 43L);

        Telefon telefon = new Telefon();
        telefon.setCislo("0904123545");
        telefon.setOsoba(osoba);

        osoba.getTelefony().add(telefon);

        entityManager.persist(telefon);
        entityManager.getTransaction().commit();
    }


    private static void saveViacAdriesOsobe(EntityManager entityManager) {
        entityManager.getTransaction().begin();

        Osoba osoba = entityManager.find(Osoba.class, 43L);
        Telefon telefon = new Telefon();
        telefon.setCislo("0901545111");
        telefon.setOsoba(osoba);
        entityManager.persist( telefon );

        Telefon telefon2 = new Telefon();
        telefon2.setCislo("0904545222");
        telefon2.setOsoba(osoba);
        entityManager.persist( telefon2 );

        Telefon telefon3 = new Telefon();
        telefon3.setCislo("0904545333");
        telefon3.setOsoba(osoba);
        entityManager.persist( telefon3 );

        entityManager.getTransaction().commit();
    }



    private static void getTelefon(EntityManager entityManager) {
        entityManager.getTransaction().begin();

        Telefon telefon = entityManager.find(Telefon.class, 3L);
        System.out.println(telefon);
        entityManager.getTransaction().commit();
    }


    private static void deleteOsobaSTelefonom(EntityManager entityManager) {
        entityManager.getTransaction().begin();

        Osoba osoba = entityManager.find(Osoba.class, 43L);
        entityManager.remove(osoba);

        entityManager.getTransaction().commit();
    }


    private static void deleteTelefon(EntityManager entityManager) {
        entityManager.getTransaction().begin();

        Telefon telefon = entityManager.find(Telefon.class, 1L);
        telefon.setOsoba(null);
        entityManager.persist( telefon );

        entityManager.getTransaction().commit();
    }


    private static void saveTelefon(EntityManager entityManager) {
        entityManager.getTransaction().begin();

        Telefon telefon = new Telefon();
        telefon.setCislo("0904545545");
        entityManager.persist( telefon );

        entityManager.getTransaction().commit();
    }


    private static void saveTelefonKOsobe(EntityManager entityManager) {
        entityManager.getTransaction().begin();

        Osoba osoba1 = new Osoba();
        osoba1.setPohlavie(Pohlavie.ZENA);
        osoba1.setMeno(new Meno("Mgr.","Phd.", "Petra", null, "Dlhomenná"));
        osoba1.setCisloOp("XX44546SS");
        entityManager.persist( osoba1 );

        Telefon telefon = new Telefon();
        telefon.setOsoba(osoba1);
        telefon.setCislo("090522346");
        entityManager.persist(telefon);

        entityManager.getTransaction().commit();

    }

    private static void saveOsobaPrintOsoba(EntityManager entityManager) {
        Osoba osoba1 = new Osoba();
        osoba1.setPohlavie(Pohlavie.ZENA);
        osoba1.setMeno(new Meno("Mgr.","Phd.", "Janka", null, "Dlhomenná"));
        osoba1.setCisloOp("XX4546SS");

        entityManager.getTransaction().begin();
        entityManager.persist( osoba1 );
        entityManager.getTransaction().commit();


        entityManager.getTransaction().begin();
        List<Osoba> result = entityManager.createQuery( "from Osoba", Osoba.class ).getResultList();
        for ( Osoba osoba : result ) {
            System.out.println("Osoba id: "+osoba.getId() +
                    ", Pohlavie: "+osoba.getPohlavie()+
                    ", Pohlavie kod: "+osoba.getPohlavie().getKod()+
                    ", meno: "+osoba.getMeno()+
                    ", op: "+osoba.getCisloOp());
        }

        entityManager.getTransaction().commit();
    }
}
