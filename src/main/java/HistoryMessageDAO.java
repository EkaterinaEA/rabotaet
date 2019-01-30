import javax.persistence.EntityManager;
import java.util.List;


public class HistoryMessageDAO extends EntityDAO {

    public HistoryMessageDAO(EntityManager manager) {
        super(manager);
    }

    public List<Message> findByUser(User user){
        return getManager().createNamedQuery("findAllMessagesByUser", Message.class)
                .setParameter("user", user)
                .getResultList();
    }

    public List<Message> findByRoom(Room room){
        return getManager().createNamedQuery("findAllMessagesByRoom", Message.class)
                .setParameter("room", room)
                .getResultList();
    }

}
