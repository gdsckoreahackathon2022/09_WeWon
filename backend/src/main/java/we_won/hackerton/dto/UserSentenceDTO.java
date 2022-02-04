package we_won.hackerton.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import we_won.hackerton.entity.Sentence;

@Getter
@NoArgsConstructor
public class UserSentenceDTO {

    private long sentenceId;
    private String username;
    private String sentence;
    private String title;



    @Builder
    public UserSentenceDTO(String username, String sentence, String title, long sentenceId) {
        this.username = username;
        this.sentence = sentence;
        this.title = title;
        this.sentenceId = sentenceId;
    }
    public Sentence toEntity(){
        return Sentence.builder()
                .id(this.sentenceId)
                .sentence(this.sentence)
                .title(this.title)
                .build();
    }
}
