package controllers;

import models.Post;
import play.mvc.Controller;

import java.util.List;

public class Application extends Controller {

    public static void index() {
        // 取出第一篇文章
        Post frontPost = Post.find("order by postedAt desc").first();
        // 取出第二篇到第11篇文章
        List<Post> olderPosts = Post.find("order by postedAt desc").from(1).fetch(10);

        render(frontPost, olderPosts);
    }

    public static void show(Long id){
        Post post = Post.findById(id);
        render(post);
    }

}