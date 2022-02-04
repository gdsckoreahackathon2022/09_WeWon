package we_won.hackerton.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import we_won.hackerton.entity.UserWordScrap;
import we_won.hackerton.entity.UserWordScrapID;

import java.util.List;

public interface UserWordScrapDAO extends JpaRepository<UserWordScrap, UserWordScrapID> {
    List<UserWordScrap> findAllByUser_id(long user_id);
}
