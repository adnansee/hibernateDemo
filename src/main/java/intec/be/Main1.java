package intec.be;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main1 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("datasource");
        EntityManager em = emf.createEntityManager();


        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Message1 msg = new Message1().setMessage("TESTING RESISTQNC");
        em.persist(msg);

        em.refresh(msg);



        tx.rollback();


        tx.commit();



        em.close(); //ALWAYS CLOSE BOTH
        emf.close();


    }
}
