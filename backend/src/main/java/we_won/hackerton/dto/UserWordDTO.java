package we_won.hackerton.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import we_won.hackerton.entity.Word;

@Getter
@NoArgsConstructor
public class UserWordDTO {

    private long wordId;
    private String username;
    private String sentence;
    private String word;

    @Builder
    public UserWordDTO(long wordId, String username, String sentence, String word) {
        this.wordId = wordId;
        this.username = username;
        this.sentence = sentence;
        this.word = word;
    }




    public Word toEntity(){
        return Word.builder()
                .id(this.wordId)
                .sentence(this.sentence)
                .word(this.word)
                .build();
    }
}
