import javax.persistence.*;
import javax.xml.crypto.Data;

@NamedQueries({
    @NamedQuery(name = Message.FIND_MESSAGE_ID_QUERY, query = "from Message where id = : id"),
    @NamedQuery(name = Message.FIND_MESSAGE_USER_QUERY, query = "from Message where user = : user"),

})

@Entity
@Table(name = "message")
public class Message {

    public static final String FIND_MESSAGE_ID_QUERY = "findMessageById";
    public static final String FIND_MESSAGE_USER_QUERY = "findMessageByUser";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_id_generator")
    @SequenceGenerator(name = "message_id_generator", sequenceName = "message_id_seq")
    @Column(name = "message_id")
    private long messageId;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "attechedFiles")
    private String attechedFiles;

    @ManyToOne
    private Admin admin;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Room room;

    @ManyToOne
    private HistoryMessage historyMessage;

    public Message() {}

    public Message(String text, String attechedFiles) {
        this.text = text;
        this.attechedFiles = attechedFiles;
    }


    @Column
    //@Enumerated(EnumType.STRING)
    @Convert(converter = MessageKindConverter.class)
    private MessageKindConverter kind;

    public MessageKindConverter getKind() {
        return kind;
    }

    public void setKind(MessageKindConverter kind) {
        this.kind = kind;
    }

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Data lastModificationTime;

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAttechedFiles() {
        return attechedFiles;
    }

    public void setAttechedFiles(String attechedFiles) {
        this.attechedFiles = attechedFiles;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public HistoryMessage getHistoryMessage() {
        return historyMessage;
    }

    public void setHistoryMessage(HistoryMessage historyMessage) {
        this.historyMessage = historyMessage;
    }

    public Data getLastModificationTime() {
        return lastModificationTime;
    }

    public void setLastModificationTime(Data lastModificationTime) {
        this.lastModificationTime = lastModificationTime;
    }
}
