package sk.jaroslavbeno;

import sk.jaroslavbeno.model.Meno;
import sk.jaroslavbeno.model.Message;
import sk.jaroslavbeno.model.Osoba;
import sk.jaroslavbeno.model.enums.Pohlavie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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

//        entityManager.getTransaction().begin();
//        entityManager.persist(new Message("Prva sprava"));
//        entityManager.persist(new Message("Druha sprava"));
//        entityManager.getTransaction().commit();

//        entityManager.getTransaction().begin();
//        List<Message> result = entityManager.createQuery("from Message", Message.class).getResultList();
//        for ( Message msg : result ) {
//            System.out.println( "Správa " + msg.getMessage() + " , id " + msg.getId()
//            + ", Datum: "+msg.getCREATE_DATE());
//        }
//        entityManager.getTransaction().commit();
//        entityManager.close();


//        Osoba osoba1 = new Osoba();
//        osoba1.setPohlavie(Pohlavie.MUZ);
//        osoba1.setMeno(new Meno("Mgr.","Peter", "Juraj", "Dlhomenný", "Phd."));
//
//        entityManager.getTransaction().begin();
//        entityManager.persist( osoba1 );
//        entityManager.getTransaction().commit();


        entityManager.getTransaction().begin();
        List<Osoba> result = entityManager.createQuery( "from Osoba", Osoba.class ).getResultList();
        for ( Osoba osoba : result ) {
            System.out.println("Osoba id: "+osoba.getId() +
                    ", Pohlavie: "+osoba.getPohlavie()+
                    ", Pohlavie kod: "+osoba.getPohlavie().getKod()+
                    ", meno: "+osoba.getMeno());
        }

        entityManager.getTransaction().commit();
        entityManager.close();


    }
}
