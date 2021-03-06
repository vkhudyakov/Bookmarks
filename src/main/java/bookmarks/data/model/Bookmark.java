package bookmarks.data.model;

import bookmarks.data.model.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by vkhudiakov on 13/09/15.
 */

@Entity
public class Bookmark {

    @JsonIgnore
    @ManyToOne
    private Account account;

    @Id
    @GeneratedValue
    private Long id;

    public String uri;
    public String description;

    public Account getAccount() {
        return account;
    }

    public Long getId() {
        return id;
    }

    public String getUri() {
        return uri;
    }

    public String getDescription() {
        return description;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bookmark(Account account, String uri, String description) {
        this.account = account;
        this.uri = uri;
        this.description = description;
    }

    public Bookmark() {
    }
}
