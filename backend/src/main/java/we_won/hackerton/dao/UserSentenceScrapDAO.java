package we_won.hackerton.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import we_won.hackerton.entity.UserSentenceScrap;
import we_won.hackerton.entity.UserSentenceScrapID;

import java.util.List;

public interface UserSentenceScrapDAO extends JpaRepository<UserSentenceScrap, UserSentenceScrapID> {
    List<UserSentenceScrap> findAllByUser_id(long user_id);

}
