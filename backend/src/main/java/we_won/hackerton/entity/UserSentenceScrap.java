package we_won.hackerton.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "UserSentenceScrap")
@Entity
@Getter
@Setter
@NoArgsConstructor
@IdClass(UserSentenceScrapID.class)
public class UserSentenceScrap {

    @Id
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private User_ user;

    @Id
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "sentence_id")
    private Sentence sentence;


}
