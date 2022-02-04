package we_won.hackerton.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserScrapDTO {

    private String username;
    private String literatureTitle;

    @Builder
    public UserScrapDTO(String username, String literatureTitle) {
        this.username = username;
        this.literatureTitle = literatureTitle;
    }
}
