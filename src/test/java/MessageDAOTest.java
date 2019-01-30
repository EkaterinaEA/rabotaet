import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MessageDAOTest {

    private EntityManagerFactory factory;
    private EntityManager em;
    private UserDAO users;
    private MessageDAO messages;

    @Before
    public void setup(){
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        em = factory.createEntityManager();
        users = new UserDAO(em);
        messages = new MessageDAO(em);
    }

    @After
    public void stop(){
        if(em != null){
            em.close();
        }
        if (factory != null){
            factory.close();
        }
    }
    @Test
    public void testSendMessage(){
        em.getTransaction().begin();
        Message message = messages.sendMessage("Hello, World!", "picture", MessageKind.TYPE);
        em.getTransaction().commit();
    }

}
