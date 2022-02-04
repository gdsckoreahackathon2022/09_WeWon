package we_won.hackerton.Interface;

import org.springframework.data.jpa.repository.JpaRepository;
import we_won.hackerton.entity.Sentence;

public interface SentenceRepository extends JpaRepository<Sentence,Long> {
}
