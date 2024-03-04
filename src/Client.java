import java.util.ArrayList;

public class Client {
    private Profile profile;
    private ArrayList<Book> borrowedBooks;

    public Client(Profile profile) {
        this.profile = profile;
        this.borrowedBooks = new ArrayList<>();
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(ArrayList<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
}