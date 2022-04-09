import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("lab4");
        BookRespository bookRespository = new BookRespository(entityManagerFactory, Book.class);
        BookShelfRespository bookShelfRespository = new BookShelfRespository(entityManagerFactory, BookShelf.class);

        BookShelf bookShelf1 = BookShelf.builder().id(UUID.randomUUID()).build();
        bookShelfRespository.add(bookShelf1);

        BookShelf bookShelf2 = BookShelf.builder().id(UUID.randomUUID()).build();
        bookShelfRespository.add(bookShelf2);

        System.out.print("Presentation of add: \n");
        Long jkRowlingBooks = bookShelfRespository.getBooksWithAuthor("J. K. Rowling");
        System.out.print("J.K. Rowling books: " + jkRowlingBooks + "\n");
        System.out.print("Adding: J.K. Rowling Ikabog\n");

        Book ikabog = Book.builder().id(0)
                .title("Ikabog").author("J. K. Rowling").bookShelf(bookShelf1).build();
        bookRespository.add(ikabog);

        jkRowlingBooks = bookShelfRespository.getBooksWithAuthor("J. K. Rowling");
        System.out.print("J.K. Rowling books: " + jkRowlingBooks + "\n");

        Book fantastyczneZwierzeta = Book.builder().id(1)
                .title("Fantastyczne zwierzęta i jak je znaleźć").author("J. K. Rowling").bookShelf(bookShelf1).build();
        bookRespository.add(fantastyczneZwierzeta);

        Book quiddichPrzezWieki = Book.builder().id(2)
                .title("Quiddich przez wieki").author("J. K. Rowling").bookShelf(bookShelf2).build();
        bookRespository.add(quiddichPrzezWieki);

        Book to = Book.builder().id(3)
                .title("To").author("Stephen King").bookShelf(bookShelf1).build();
        bookRespository.add(to);

        Book Lsnienie = Book.builder().id(4)
                .title("Lsnienie").author("Stephen King").bookShelf(bookShelf2).build();
        bookRespository.add(Lsnienie);

        Book Instytut = Book.builder().id(5)
                .title("Instytut").author("Stephen King").bookShelf(bookShelf2).build();
        bookRespository.add(Instytut);

        System.out.print("\nPresentation of delete: \n");
        jkRowlingBooks = bookShelfRespository.getBooksWithAuthor("J. K. Rowling");
        System.out.print("J.K. Rowling books: " + jkRowlingBooks + "\n");
        System.out.print("Deleting: J.K. Rowling Ikabog\n");
        bookRespository.deleteObject(ikabog);
        jkRowlingBooks = bookShelfRespository.getBooksWithAuthor("J. K. Rowling");
        System.out.print("J.K. Rowling books: " + jkRowlingBooks + "\n");

        System.out.print("\nPresentation of showing all entities: \n");
        List<Book> books = bookRespository.findAll();
        System.out.print(books + "\n");

        System.out.print("\nPresentation of custom request: \n");

        java.util.List<java.util.Map.Entry<String,Integer>> pairList = bookShelfRespository.countAuthorsBooks(bookShelf2.getId());

        System.out.print(pairList);

    }
}