import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;
import java.util.UUID;

public class BookShelfRespository extends Repository<BookShelf, String> {

    public BookShelfRespository(EntityManagerFactory entityManagerFactory, Class<BookShelf> clazz) {
        super(entityManagerFactory, clazz);
    }

    public Long getBooksWithAuthor(String author) {
        EntityManager entityManager = getEmf().createEntityManager();
        Query query = entityManager.createQuery("select COUNT(book) from Book book where book.author=:author");
        Long count = (Long) query.setParameter("author", author).getSingleResult();
        entityManager.close();
        return count;
    }

    public java.util.List<java.util.Map.Entry<String,Integer>> countAuthorsBooks(UUID id) {
        EntityManager entityManager = getEmf().createEntityManager();

        Query query1 = entityManager.createQuery("select bookShelf from BookShelf bookShelf where bookShelf.id=:id");
        BookShelf bookshelf = (BookShelf) query1.setParameter("id", id).getSingleResult();

        Query query2 = entityManager.createQuery("select distinct book.author from Book book where book.bookShelf=:bookShelf");
        List<String> listOfAuthorsOnShelf = query2.setParameter("bookShelf", bookshelf).getResultList();

        Query query = entityManager.createQuery("select book from Book book where book.bookShelf=:bookShelf");
        List<Book> list = query.setParameter("bookShelf", bookshelf).getResultList();

        java.util.List<java.util.Map.Entry<String,Integer>> pairList= new java.util.ArrayList<>();

        for (String author:listOfAuthorsOnShelf) {
            Integer count = 0;
            for (Book book:list) {
                if(author == book.getAuthor()){
                    count += 1;
                }
            }
            java.util.Map.Entry<String,Integer> pair1=new java.util.AbstractMap.SimpleEntry<>(author,count);
            pairList.add(pair1);
        }

        entityManager.close();

        return pairList;
    }
}
