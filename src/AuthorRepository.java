import java.util.ArrayList;

public class AuthorRepository {
    private ArrayList<Author>authors;
    public AuthorRepository(){
        this.authors=new ArrayList<>();
    }
    public void addAuthor(Author author){
        authors.add(author);
    }
    public void removeAuthor(Author author){
        authors.remove(author);
    }
    public void updateAuthor(Author oldAuthor, Author newAuhtor){
        int index=authors.indexOf(oldAuthor);
            if (index!=-1){
                authors.set(index, newAuhtor);
        }

    }
    public ArrayList<Author>getAllAuthors(){
        return authors;
    }

}
