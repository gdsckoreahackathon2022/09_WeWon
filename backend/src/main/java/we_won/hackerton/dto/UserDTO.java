package we_won.hackerton.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserDTO {

    private String username;

    private String role;

    public UserDTO(String username, String role) {
        super();
        this.username = username;
        this.role = role;
    }
}