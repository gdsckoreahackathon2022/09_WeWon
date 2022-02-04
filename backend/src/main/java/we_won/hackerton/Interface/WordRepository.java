package we_won.hackerton.Interface;

import org.springframework.data.jpa.repository.JpaRepository;
import we_won.hackerton.entity.Word;

public interface WordRepository extends JpaRepository<Word,Long> {
}
