package we_won.hackerton.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "user_word_scrap")
@Entity
@Getter
@Setter
@NoArgsConstructor
@IdClass(UserWordScrapID.class)
public class UserWordScrap {
    @Id
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private User_ user;

    @Id
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "word_id")
    private Word word;
}
