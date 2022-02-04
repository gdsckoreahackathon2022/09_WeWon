package we_won.hackerton.entity;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Sentence{

    @Id
    @Column(nullable = false)
    private long id;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String sentence; //내용

    @Column(nullable = false)
    private String title;


    @Builder
    public Sentence(long id, String sentence, String title) {
        this.id = id;
        this.sentence = sentence;
        this.title = title;
    }
}
