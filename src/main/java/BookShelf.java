import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@ToString
@EqualsAndHashCode
public class BookShelf {
    @Id
    @Getter
    private UUID id;
    @OneToMany
    private List<Book> books;

    @Override
    public String toString(){
        return "Bookshelf(" + id + ")";
    }
}
