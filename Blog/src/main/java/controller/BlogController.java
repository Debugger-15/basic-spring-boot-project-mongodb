package controller;

import beans.Blog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.BlogService;

@RestController
@RequestMapping("/blog")
public class BlogController {
    private static Logger logger =  LoggerFactory.getLogger(BlogController.class);

    @Autowired
    private BlogService blogService;


    @PostMapping("/createBlog")
    public Blog createBlog(@RequestBody Blog blog){
        return blogService.createBlog(blog);
    }
    @PostMapping("/deleteBlog")
    public Boolean createBlog(@RequestParam String blogId){
        return blogService.deleteBlog(blogId);
    }
    @PostMapping("/disableBlog")
    public Boolean disableBlog(@RequestParam String blogId){
        return blogService.disableBlog(blogId);
    }
    @PostMapping("/likeBlog")
    public void likeBlog(@RequestParam String blogId){
       blogService.likeBlog(blogId);
    }

    @PostMapping("/dislikeBlog")
    public void dislikeBlog(@RequestParam String blogId){
        blogService.dislikeBlog(blogId);
    }

    @PostMapping("/commentOnBlog")
    public void commentOnBlog(@RequestParam String comment, @RequestParam String blogId, @RequestParam int commentParentId){
        blogService.commentOnBlog(comment, blogId, commentParentId);
    }

    @PostMapping("/editComment")
    public void editComment(@RequestParam String comment, @RequestParam int commentId,  @RequestParam String blogId){
        blogService.editComment(comment, commentId, blogId);
    }
    @GetMapping("/getSummary")
    public Blog getSummary(@RequestParam String blogId){
        return blogService.getSummary(blogId);
    }

}
