package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by cgz on 16-8-2.
 */
@Entity
@Table(name = "tb_comments")
public class Comment extends Model {
    public String author;
    public Date postedAt;

    @Lob
    public String content;

    @ManyToOne
    public Post post;

    public Comment(Post post, String author, String content) {
        this.author = author;
        this.postedAt = new Date();
        this.content = content;
        this.post = post;
    }
}
