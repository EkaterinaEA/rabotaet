import javax.persistence.EntityManager;

public class MessageDAO extends EntityDAO {

    public MessageDAO(EntityManager manager) {
        super(manager);
    }

    public Message sendMessage(String text, String attechedFiles, MessageKind kind){
        Message message = new Message(text, attechedFiles);
        getManager().getTransaction().begin();
        message.setKind(kind);
        getManager().persist(message);
        getManager().getTransaction().commit();
        return message;
    }

}
