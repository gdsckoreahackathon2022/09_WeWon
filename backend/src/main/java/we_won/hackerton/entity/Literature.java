package we_won.hackerton.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Literature extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = true)
    private String site_url;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String content; //내용

    @Column(nullable = false)
    private long category; //시 == 1, 소설 == 2, 수필 == 3

    @Builder
    public Literature(String title, String writer, String site_url, String content, long category) {
        this.title = title;
        this.writer = writer;
        this.site_url = site_url;
        this.content = content;
        this.category = category;
    }
}
