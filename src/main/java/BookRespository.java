import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class BookRespository extends Repository<Book, String>{

    public BookRespository(EntityManagerFactory entityManagerFactory, Class<Book> clazz) {
        super(entityManagerFactory, clazz);
    }
}