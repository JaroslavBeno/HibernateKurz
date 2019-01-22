package sk.jaroslavbeno;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.jaroslavbeno.model.*;
import sk.jaroslavbeno.model.enums.Pohlavie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Hello world!
 *          <plugin>
 *             <groupId>com.mysema.maven</groupId>
 *             <artifactId>apt-maven-plugin</artifactId>
 *             <version>1.1.3</version>
 *             <executions>
 *               <execution>
 *                 <goals>
 *                   <goal>process</goal>
 *                 </goals>
 *                 <configuration>
 *                   <outputDirectory>target/generated-sources/java</outputDirectory>
 *                   <processor>com.querydsl.apt.jpa.JPAAnnotationProcessor</processor>
 *                 </configuration>
 *               </execution>
 *             </executions>
 *           </plugin>
 */
public class App 
{
    private static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main( String[] args ) throws InterruptedException {
//        String hodnota = "hodnota";
//        String hodnota2 = "hodnota2";
//        String hodnota3 = "hodnota3";
//        logger.debug("Hello word from logger");
//        logger.debug("Debug text "+hodnota+".");
//        logger.debug("Debug text {}.",hodnota);
//        logger.debug("Debug text {} {}.",hodnota, hodnota2);
//        Object[] parametre = {hodnota, hodnota2, hodnota3};
//        logger.debug("Debug {} text {}, {}.",parametre);

        while(true) {
            Thread.sleep(1000);
            Message message = new Message("Sprava");
        }

//        EntityManagerFactory entityManagerFactory =
//                Persistence.createEntityManagerFactory("sk.jaroslavbeno.jpa");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//
//        try{
//            entityManager.getTransaction().begin();
//            criteria(entityManager);
//            entityManager.getTransaction().commit();
//
//        }catch (Exception e){
//            try{
//                entityManager.getTransaction().rollback();
//            }catch (Exception ex){
//                //nepodarilo sa rollbacknúť
//            }
//        }finally {
//            entityManager.close();
//        }


    }

    private static void queryDSL(EntityManager entityManager) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);

        QOsoba osoba = QOsoba.osoba;
        List<Osoba> osoby = jpaQueryFactory.selectFrom(osoba)
                .where(osoba.meno.priezvisko.like("%atka%")).fetch();
        System.out.println(osoby);

    }

    private static void criteria(EntityManager entityManager) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Osoba> criteria = builder.createQuery( Osoba.class );
        Root<Osoba> from = criteria.from( Osoba.class );
        criteria.select( from );
        criteria.where( builder.equal( from.get("id"), 25L ) );

        List<Osoba> persons = entityManager.createQuery( criteria ).getResultList();

        //https://en.wikibooks.org/wiki/Java_Persistence/Criteria
    }

    private static void nativeQuery(EntityManager entityManager) {
        entityManager.getTransaction().begin();
        List<Osoba> osoby =
                entityManager.createNativeQuery("select * from osoba",Osoba.class)
                        .getResultList();

        for (Osoba osoba : osoby){
            System.out.println("id = "+osoba.getId()+", priezvisko = "+osoba.getMeno().getPriezvisko());
        }

        entityManager.getTransaction().commit();

    }

    private static void addAdresaOsobe(EntityManager entityManager) {
        entityManager.getTransaction().begin();

        Osoba osoba = entityManager.find(Osoba.class, 48L);

        Adresa adresa = new Adresa();
        adresa.setUlica("Daja ulica");
        adresa.setMesto("Dajake mesto");
        adresa.setPsc("90010");
        adresa.setOsoba(osoba);

        entityManager.flush();

        entityManager.persist(adresa);
        entityManager.getTransaction().commit();
    }


    private static void loadOsoba(EntityManager entityManager) {
        Osoba osoba = entityManager.find(Osoba.class, 48L);
        Set<SkupinaKontaktov> skupiny = osoba.getSkupinyOsoby();
        skupiny.size();
        osoba.getTelefony().size();
    }

    private static void deleteSkupina(EntityManager entityManager) {
        entityManager.getTransaction().begin();

        SkupinaKontaktov skupina = entityManager.find(SkupinaKontaktov.class, 2L);
        entityManager.remove(skupina);

        entityManager.getTransaction().commit();
    }


    private static void loadSkupina(EntityManager entityManager) {
        SkupinaKontaktov skupina = entityManager.find(SkupinaKontaktov.class, 2L);
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

//    private static void saveOsobuTelefony(EntityManager entityManager) {
//        entityManager.getTransaction().begin();
//
//        Osoba osoba = new Osoba();
//        osoba.setPohlavie(Pohlavie.ZENA);
//        osoba.setMeno(new Meno(null,null, "Janko", null, "Beno"));
//        osoba.setCisloOp("X411146D4");
//        ArrayList<Telefon> telefony = new ArrayList<>();
//
//        Telefon telefon = new Telefon();
//        telefon.setCislo("0911545111");
//        telefon.setOsoba(osoba);
//
//        Telefon telefon2 = new Telefon();
//        telefon2.setCislo("0912545222");
//        telefon2.setOsoba(osoba);
//
//        telefony.add(telefon);
//        telefony.add(telefon2);
//        osoba.setTelefony(telefony);
//
//        entityManager.persist(osoba);
//        entityManager.persist(telefon);
//        entityManager.persist(telefon2);
//        entityManager.getTransaction().commit();
//    }


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
