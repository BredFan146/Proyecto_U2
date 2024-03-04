import java.util.Date;

public class Seeder {
    public static void initialize(BookRepository bookRepository, AuthorRepository authorRepository, ClientRepository clientRepository) {
        Profile authorProfile1 = new Profile("John", "Doe", new Date());
        Author author1 = new Author(authorProfile1);

        Profile authorProfile2 = new Profile("Jane", "Smith", new Date());
        Author author2 = new Author(authorProfile2);

        authorRepository.addAuthor(author1);
        authorRepository.addAuthor(author2);

        // Crear libros
        Book book1 = new Book("1234567890", "Sample Book 1", author1, new Date());
        Book book2 = new Book("0987654321", "Sample Book 2", author2, new Date());

        bookRepository.addBook(book1);
        bookRepository.addBook(book2);

        // Crear clientes
        Profile clientProfile1 = new Profile("Alice", "Johnson", new Date());
        Client client1 = new Client(clientProfile1);

        Profile clientProfile2 = new Profile("Bob", "Williams", new Date());
        Client client2 = new Client(clientProfile2);

        clientRepository.addClient(client1);
        clientRepository.addClient(client2);
    }
}
