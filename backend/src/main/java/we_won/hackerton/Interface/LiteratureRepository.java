package we_won.hackerton.Interface;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import we_won.hackerton.entity.Literature;

import java.util.List;

public interface LiteratureRepository extends JpaRepository<Literature,Long> {

    List<Literature> findByCategory(long id);
    @Query(value = "SELECT * FROM hackerton.literature order by RAND() limit 1",nativeQuery = true)
    List<Literature> findAll();

    Literature getByTitle(String title);
    Literature findByTitle(String title);
}
