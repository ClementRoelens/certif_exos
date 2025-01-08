import java.io.Serializable;

public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String title;
    private final String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String toString(){
        return String.format("%s écrit par %s",title,author);
    }
}
