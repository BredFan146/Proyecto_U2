import java.util.ArrayList;
import java.util.Date;

public class Controller {
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private ClientRepository clientRepository;
    private TransactionRepository transactionRepository;

    public Controller(BookRepository bookRepository, AuthorRepository authorRepository, ClientRepository clientRepository, TransactionRepository transactionRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.clientRepository = clientRepository;
        this.transactionRepository = transactionRepository;
    }

    // Métodos para operaciones del administrador

    // CRUD de Libros
    public void createBook(Book book) {
        bookRepository.addBook(book);
        System.out.println("Libro creado con exito.");
    }

    public void removeBook(Book book) {
        if (bookRepository.getBorrowedBooks().contains(book)) {
            System.out.println("No se puede eliminar el libro porque está prestado a un cliente.");
            return;
        }
        Author author = book.getAuthor();
        author.getBooks().remove(book);
        bookRepository.removeBook(book);
    }

    public void updateBook(Book oldBook, Book newBook) {
        bookRepository.updateBook(oldBook, newBook);
    }

    public void showBooks(String option) {
        ArrayList<Book> booksToShow = new ArrayList<>();
        switch (option) {
            case "todos":
                booksToShow = bookRepository.getAllBooks();
                break;
            case "prestados":
                booksToShow = bookRepository.getBorrowedBooks();
                break;
            case "disponibles":
                booksToShow = bookRepository.getAvailableBooks();
                break;
            default:
                System.out.println("Opción no válida.");
                return;
        }
        for (Book book : booksToShow) {
            System.out.println(book.getTitle());
        }
    }
    public ArrayList<Book> getAvailableBooks() {
        ArrayList<Book> availableBooks = new ArrayList<>();
        for (Book book : bookRepository.getAllBooks()) {
            if (book.isAvailable()) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }

    // CRUD de Clientes
    public void createClient(Client client) {
        clientRepository.addClient(client);
        System.out.println("Cliente creado con exito.");
    }


    public void removeClient(Client client) {
        if (!client.getBorrowedBooks().isEmpty()) {
            System.out.println("No se puede eliminar el cliente porque tiene libros en su poder.");
            return;
        }
        clientRepository.removeClient(client);
    }

    public void updateClient(Client oldClient, Client newClient) {
        clientRepository.updateClient(oldClient, newClient);
    }

    public ArrayList<Client> showClients() {
        ArrayList<Client> clients = clientRepository.getAllClients();
        for (Client client : clients) {
            System.out.println("Nombre: " + client.getProfile().getName() + " " + client.getProfile().getLastName());
            System.out.println("Libros prestados:");
            for (Book book : client.getBorrowedBooks()) {
                System.out.println("- " + book.getTitle());
            }
            System.out.println("-------------------------");
        }
        return clients;
    }

    // CRUD de Autores
    public void createAuthor(Author author) {
        authorRepository.addAuthor(author);
        System.out.println("Autor creado con exito.");
    }

    public void removeAuthor(Author author) {
        if (!author.getBooks().isEmpty()) {
            System.out.println("No se puede eliminar el autor porque tiene libros asociados.");
            return;
        }
        authorRepository.removeAuthor(author);
    }

    public void updateAuthor(Author oldAuthor, Author newAuthor) {
        authorRepository.updateAuthor(oldAuthor, newAuthor);
    }

    public void showAuthors() {
        ArrayList<Author> authors = authorRepository.getAllAuthors();
        for (Author author : authors) {
            System.out.println("Autor: " + author.getProfile().getName() + " " + author.getProfile().getLastName());
            System.out.println("Libros:");
            for (Book book : author.getBooks()) {
                System.out.println("- " + book.getTitle());
            }
            System.out.println();
        }
    }

    // Ejecución de préstamos y devoluciones de libros
    public void loanBook(Client client, Book book) {
        if (client.getBorrowedBooks().size() >= 3) {
            System.out.println("El cliente ya tiene 3 libros prestados.");
            return;
        }
        if (!book.isAvailable()) {
            System.out.println("El libro no está disponible para prestamo.");
            return;
        }
        book.setAvailable(false);
        client.getBorrowedBooks().add(book);
        Transaction transaction = new Transaction("Prestamo", client, book, new Date());
        transactionRepository.addTransaction(transaction);
    }

    public void returnBook(Client client, Book book) {
        if (!client.getBorrowedBooks().contains(book)) {
            System.out.println("El cliente no tiene este libro en su poder.");
            return;
        }
        book.setAvailable(true);
        client.getBorrowedBooks().remove(book);
        Transaction transaction = new Transaction("Devolución", client, book, new Date());
        transactionRepository.addTransaction(transaction);
    }

    // Generación de reporte de movimientos
    public void generateReport(String reportType, Object object) {
        if (reportType.equals("cliente")) {
            Client client = (Client) object;
            System.out.println("Reporte de movimientos para cliente: " + client.getProfile().getName() + " " + client.getProfile().getLastName());
            ArrayList<Transaction> transactions = transactionRepository.getTransactionsByClient(client);
            if (!transactions.isEmpty()) {
                for (Transaction transaction : transactions) {
                    System.out.println("ID de transaccion: " + transaction.getId());
                    System.out.println("Tipo de transaccion: " + transaction.getType());
                    System.out.println("Libro: " + transaction.getBook().getTitle());
                    System.out.println("Fecha: " + transaction.getDate());
                    System.out.println("--------------------------------------");
                }
            } else {
                System.out.println("El cliente no ha realizado ninguna transaccion.");
            }
        } else if (reportType.equals("libro")) {
            Book book = (Book) object;
            System.out.println("Reporte de movimientos para libro: " + book.getTitle());
            ArrayList<Transaction> transactions = transactionRepository.getTransactionsByBook(book);
            if (!transactions.isEmpty()) {
                for (Transaction transaction : transactions) {
                    System.out.println("ID de transaccion: " + transaction.getId());
                    System.out.println("Tipo de transaccion: " + transaction.getType());
                    System.out.println("Cliente: " + transaction.getClient().getProfile().getName() + " " + transaction.getClient().getProfile().getLastName());
                    System.out.println("Fecha: " + transaction.getDate());
                    System.out.println("--------------------------------------");
                }
            } else {
                System.out.println("El libro no tiene ninguna transaccion registrada.");
            }
        } else if (reportType.equals("rangoFechas")) {
            // Implementar lógica para filtrar por rango de fechas
        } else {
            System.out.println("Tipo de reporte no valido.");
        }
    }
}