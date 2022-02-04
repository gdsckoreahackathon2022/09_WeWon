package we_won.hackerton.entity;

import java.io.Serializable;

public class UserLiteratureScrapId implements Serializable {

    private long user;
    private long literature;

    public UserLiteratureScrapId() {}

    public UserLiteratureScrapId(long user, long literature) {
        this.user = user;
        this.literature = literature;
    }
}
