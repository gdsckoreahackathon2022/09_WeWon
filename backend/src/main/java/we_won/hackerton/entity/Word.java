package we_won.hackerton.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Word {

    @Id
    @Column(nullable = false)
    private long id;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String sentence; //문장

//    @Column(nullable = false)
//    private String meaning; //단어의 뜻

    @Column(nullable = false)
    private String word;


    @Builder
    public Word(long id, String sentence, String word) {
        this.id = id;
        this.sentence = sentence;
//        this.meaning = meaning;
        this.word = word;
    }
}
