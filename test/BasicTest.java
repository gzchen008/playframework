import javafx.geometry.Pos;
import org.apache.commons.logging.Log;
import org.junit.*;

import java.util.*;

import play.Logger;
import play.test.*;
import models.*;

public class BasicTest extends UnitTest {

    @Before
    public void setup() {
        Fixtures.deleteDatabase();
        Logger.info("test setup");
    }

    @Test
    public void aVeryImportantThingToTest() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void createUser() {
        new User("cgz@gmail.com", "124", "gzPAPA", true).save();
        User cgz = User.find("byEmail", "cgz@gmail.com").first();
        assertNotNull(cgz);
        assertEquals("gzPAPA", cgz.fullName);
    }

    @Test
    public void testCheckUser() {
        new User("cgz@gmail.com", "124", "gzPAPA", true).save();
        assertNotNull(User.check("cgz@gmail.com", "124"));
    }

    @Test
    public void testCreatePost() {
        User cgz = new User("cgz@gmail.com", "124", "gzPAPA", true);
        Logger.info("before save id：" + cgz.id);
        cgz.save();
        new Post(cgz, "my first post", "hello world", new Date()).save();
        Logger.info("after save id:" + cgz.getId());
        new Post(cgz, "my twice post", "hello world 2", new Date()).save();

        List<Post> posts = Post.find("byAuthor", cgz).fetch();

        assertEquals(posts.size(), 2);
        Post post = posts.get(0);
        assertNotNull(post.content);
    }

    @Test
    public void testPostComments() {
        User cgz = new User("cgz@gmail.com", "124", "gzPAPA", true).save();
        Post post = new Post(cgz, "my first post", "hello world", new Date()).save();
        new Comment(post, "Jerry", "good job").save();
        new Comment(post, "Tom", "labshi job").save();
        List<Comment> comments = Comment.find("byPost", post).fetch();
        assertNotNull(comments);
        assertEquals(comments.size(), 2);
    }

    @Test
    public void fullTest() {
        Fixtures.loadModels("data.yml");
        assertEquals(2, User.count());
        assertEquals(3, Post.count());

        Post frontPost = Post.find("order by postedAt desc").first();
        User user = frontPost.author;
        List<Comment> comments = Comment.find("byPost", frontPost).fetch();
        assertEquals(2, comments.size());
        long postsNum = Post.count("byAuthor", user);
        assertEquals(2, postsNum);
    }

}
