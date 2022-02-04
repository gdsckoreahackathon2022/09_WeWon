package we_won.hackerton.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import we_won.hackerton.entity.UserLiteratureScrap;
import we_won.hackerton.entity.UserLiteratureScrapId;

import java.util.List;

public interface UserLiteratureScrapDAO extends JpaRepository<UserLiteratureScrap, UserLiteratureScrapId> {
    List<UserLiteratureScrap> findAllByUser_id(long user_id);
}
