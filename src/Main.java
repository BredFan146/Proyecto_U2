import java.util.Date;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Inicializar repositorios
        BookRepository bookRepository = new BookRepository();
        AuthorRepository authorRepository = new AuthorRepository();
        ClientRepository clientRepository = new ClientRepository();
        TransactionRepository transactionRepository = new TransactionRepository();

        // Sembrar datos iniciales
        seedInitialData(bookRepository, authorRepository, clientRepository);

        // Crear un controlador
        Controller controller = new Controller(bookRepository, authorRepository, clientRepository, transactionRepository);

        // Ejecutar operaciones en la biblioteca
        performLibraryOperations(controller);
    }

    // Método para sembrar datos iniciales en el sistema
    private static void seedInitialData(BookRepository bookRepository, AuthorRepository authorRepository, ClientRepository clientRepository) {
        System.out.println("Sembrando datos iniciales en el sistema...");
        // Crear autores
        Profile authorProfile1 = new Profile("Elena", "Chavez", new Date());
        Author author1 = new Author(authorProfile1);

        Profile authorProfile2 = new Profile("Arturo", "Perez", new Date());
        Author author2 = new Author(authorProfile2);

        authorRepository.addAuthor(author1);
        authorRepository.addAuthor(author2);

        // Crear libros
        Book book1 = new Book("1245789036", "El gran corruptor", author1, new Date());
        Book book2 = new Book("0987654321", "El problema final", author2, new Date());

        bookRepository.addBook(book1);
        bookRepository.addBook(book2);

        // Crear clientes
        Profile clientProfile1 = new Profile("Sebastian", "Damian", new Date());
        Client client1 = new Client(clientProfile1);

        Profile clientProfile2 = new Profile("Cristiano", "Ronaldo", new Date());
        Client client2 = new Client(clientProfile2);

        clientRepository.addClient(client1);
        clientRepository.addClient(client2);

        System.out.println("Datos iniciales sembrados con exito.");
    }

    // Método para realizar operaciones en la biblioteca
    private static void performLibraryOperations(Controller controller) {
        System.out.println("\nEjecutando operaciones en la biblioteca...");
        // Mostrar todos los libros
        System.out.println("\nMostrando todos los libros:");
        controller.showBooks("todos");

        // Mostrar libros prestados
        System.out.println("\nMostrando libros prestados:");
        controller.showBooks("prestados");

        // Mostrar libros disponibles para prestamo
        System.out.println("\nMostrando libros disponibles para préstamo:");
        controller.showBooks("disponibles");

        // Mostrar todos los autores despues de la creacion del nuevo autor
        System.out.println("\nMostrando todos los autores despues de la creacion del nuevo autor:");
        controller.showAuthors();

        // Obtener todos los clientes del repositorio
        ArrayList<Client> allClients = controller.showClients();

        // Prestar un libro a cada cliente
        System.out.println("\nPrestar un libro a cada cliente...");
        ArrayList<Book> availableBooks = controller.getAvailableBooks();
        if (!availableBooks.isEmpty()) {
            for (Client client : allClients) {
                Book bookToLoan = availableBooks.get(0); // Tomamos el primer libro disponible para prestamo
                controller.loanBook(client, bookToLoan);
                availableBooks.remove(bookToLoan); // Eliminamos el libro prestado de la lista de disponibles
            }
        } else {
            System.out.println("No hay libros disponibles para préstamo.");
        }

        // Mostrar clientes y sus libros prestados
        System.out.println("\nMostrando clientes y sus libros prestados:");
        controller.showClients();

        // Devolver un libro por cada cliente
        System.out.println("\nDevolver el libro prestado por cada cliente...");
        for (Client client : allClients) {
            Book bookToReturn = client.getBorrowedBooks().get(0); // Tomamos el primer libro prestado por cada cliente
            controller.returnBook(client, bookToReturn);
        }


        // Generar un reporte de movimientos para el primer cliente
        if (!allClients.isEmpty()) {
            System.out.println("\nGenerando un reporte de movimientos para el primer cliente...");
            Client firstClient = allClients.getFirst();
            controller.generateReport("cliente", firstClient);
        } else {
            System.out.println("No hay clientes para generar un reporte de movimientos.");
        }
    }
}