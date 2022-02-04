package we_won.hackerton.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "UserLiteratureScrap")
@Entity
@Getter
@Setter
@NoArgsConstructor
@IdClass(UserLiteratureScrapId.class)
public class UserLiteratureScrap {

    @Id
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private User_ user;

    @Id
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "literature_id")
    private Literature literature;


}
