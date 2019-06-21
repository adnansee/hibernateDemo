package intec.be;


import javax.persistence.*;


public class TestMessage {

    private static EntityManagerFactory emf;

    private static void print(int pos, Message1 memMessage) {
        EntityManager em = emf.createEntityManager();
        Message1 dbMessage = em.find(Message1.class,
                memMessage.getId());

        em.close();
        System.out.println(pos + ": " + memMessage.getMessage() + "\t"
                + ((dbMessage != null) ? dbMessage.getMessage() : "null"));
    }


    public static void main(String[] args) {
        EntityManager em = null;
        try {
            emf = Persistence.createEntityManagerFactory("datasource");

            em = emf.createEntityManager();


            EntityTransaction tx = em.getTransaction(); // We remove existing record
            tx.begin();
            Message1 m = em.find(Message1.class, 100);
            if (m != null) {
                em.remove(m);
            }

            tx.commit();
            // Test begins here
            tx.begin();
            m = new Message1(100, "AAA");
            print(1, m);
            em.persist(m);
            tx.commit();
            print(2, m);
            m.setMessage("BBB");
            tx.begin();
            em.refresh(m);
            print(3, m);
            em.detach(m);
            m.setMessage("CCC");
            print(4, m);
            tx.commit();
            print(5, m);
            tx.begin();
            m = em.merge(m);
            print(6, m);
            tx.commit();
            print(7, m);
            em.clear();
            m.setMessage("DDD");
            print(8, m);
            tx.begin();
            tx.commit();
            print(9, m);
            m = em.find(Message1.class, 100);
            print(10, m);
            m.setMessage("EEE");
            print(11, m);
            tx.begin();
            tx.commit();
            m.setMessage("FFF");
            tx.begin();
            em.flush();
            m.setMessage("GGG");
            print(12, m);
            em.refresh(m);
            print(13, m);
            tx.rollback();
            print(14, m);
            tx.begin();
            m.setMessage("HHH");
            print(15, m);
            tx.commit();
            print(16, m);
            tx.begin();
            m = em.merge(m);
            print(17, m);
            tx.commit();
            print(18, m);
            em.close();
        }
        finally
        {
            if (em != null) em.close();
            if (emf != null) emf.close();
        }

    }
}