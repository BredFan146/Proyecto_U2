import java.util.Date;
import java.util.UUID;

public class Transaction {
        private String id;
        private String type;
        private Client client;
        private Book book;
        private Date date;

    public Transaction(String type,Client client, Book book, Date date) {
        this.setId(UUID.randomUUID().toString());
        this.setType(type);
        this.setClient(client);
        this.setBook(book);
        this.setDate(date);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
