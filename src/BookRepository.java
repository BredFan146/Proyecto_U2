import java.util.ArrayList;

public class BookRepository {
    private ArrayList<Book> books;
    public BookRepository(){
        this.books=new ArrayList<>();
    }
    public void addBook(Book book){
        books.add(book);
    }
    public void removeBook(Book book){
        books.remove(book);
    }
    public void updateBook(Book oldBook, Book newBook) {
        int index = books.indexOf(oldBook);
        if (index != -1) {
            books.set(index, newBook);
        }
    }
    public ArrayList<Book>getAllBooks(){
        return books;
    }
    public ArrayList<Book>getAvailableBooks(){
        ArrayList<Book>availableBooks=new ArrayList<>();
        for (Book book:books){
            if (book.isAvailable()){
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }
    public ArrayList<Book>getBorrowedBooks(){
        ArrayList<Book>borrowedBooks=new ArrayList<>();
        for (Book book:books){
            if (!book.isAvailable()){
                borrowedBooks.add(book);
            }
        }
        return borrowedBooks;
    }
}
