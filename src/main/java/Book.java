import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@ToString
@EqualsAndHashCode
public class Book {
    @Id
    private int id;
    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private String author;
    @ManyToOne
    private BookShelf bookShelf;

    @Override
    public String toString(){
        return "Book(" + id + "," + title + "," + author + "," + bookShelf + ")";
    }
}
