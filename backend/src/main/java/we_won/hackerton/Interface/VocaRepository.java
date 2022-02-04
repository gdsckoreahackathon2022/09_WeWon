package we_won.hackerton.Interface;

import org.springframework.data.jpa.repository.JpaRepository;
import we_won.hackerton.entity.Vocabulary;

public interface VocaRepository extends JpaRepository<Vocabulary,Long> {
}
