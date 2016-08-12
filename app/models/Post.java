package models;

import play.db.jpa.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by cgz on 16-8-2.
 */
@Entity
@Table(name = "tb_post")
public class Post extends Model {
    public String title;
    public Date postedAt;

    @Lob
    public String content;

    @ManyToOne
    public User author;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    public List<Comment> comments;

    public Post(User author, String title, String content, Date postedAt) {
        this.comments = new ArrayList<Comment>();
        this.title = title;
        this.postedAt = postedAt;
        this.content = content;
        this.author = author;
    }

    public Post previous() {
        return Post.find("postedAt < ? order by postedAt desc", postedAt).first();
    }

    public Post next() {
        return Post.find("postedAT > ? order by postedAt asc", postedAt).first();
    }
}
