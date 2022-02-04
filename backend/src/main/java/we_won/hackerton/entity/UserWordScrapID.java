package we_won.hackerton.entity;

import java.io.Serializable;

public class UserWordScrapID implements Serializable {

    private long user;
    private long word;

    public UserWordScrapID() {
    }

    public UserWordScrapID(long user, long word) {
        this.user = user;
        this.word = word;
    }
}
