package we_won.hackerton.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Vocabulary extends BaseEntity{

    @Column(nullable = false)
    private String voca; //단어

    @Column(nullable = false)
    private String meaning; //단어의 뜻

    @Builder
    public Vocabulary(String voca, String meaning) {
        this.voca = voca;
        this.meaning = meaning;
    }
}
