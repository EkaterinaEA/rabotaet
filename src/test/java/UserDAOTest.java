import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;


public class UserDAOTest {

    private EntityManagerFactory factory;
    private EntityManager em;
    private UserDAO dao;

    @Before
    public void setup() {
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        em = factory.createEntityManager();
        dao = new UserDAO(em);
    }

    @After
    public void stop() {
        if (em != null) {
            em.close();
        }
        if (factory != null) {
            factory.close();
        }
    }

    @Test
    public void testInviteUser(String login, String password, String email) throws Exception {
        User user = dao.inviteUser("login", "password", "email");
        assertNotEquals(0L, user.getUserID());
        assertEquals("login", user.getLogin());
        assertEquals("password", user.getPassword());
        assertEquals("email", user.getEmail());
        assertNotNull(user.getMessageList());
        assertTrue(user.getMessageList().isEmpty());
    }

    @Test
    public void deleteUser() throws Exception {
        User user = dao.inviteUser("login", "password", "email");
        User userFound = em.find(User.class, user.getUserID());
        dao.deleteUser(userFound);
        assertNull(em.find(User.class, user.getUserID()));
    }

    @Test
    public void findById() throws Exception {
        User user = dao.inviteUser("login", "password", "email");
        User userFound = dao.findById(user.getUserID());
        assertEquals(user, userFound);
    }

    @Test
    public void findByLogin() throws Exception {
        User user = dao.inviteUser("login", "password", "email");
        User userFound = dao.findByLogin(user.getLogin());
        assertEquals(user, userFound);
    }

    @Test
    public void findByEmail() throws Exception {
        User user = dao.inviteUser("login", "password", "email");
        User userFound = dao.findByEmail(user.getEmail());
        assertEquals(user, userFound);
    }

    @Test
    public void findAll() throws Exception {
        List<User> users = dao.findAll();
        assertNotNull(users);
    }

}