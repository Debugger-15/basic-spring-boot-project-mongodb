package service;

import beans.Blog;
import org.springframework.stereotype.Component;

@Component
public interface BlogService {

    Blog createBlog(Blog blog);

    Boolean deleteBlog(String id);

    Boolean disableBlog(String id);

    void likeBlog(String id);

    void dislikeBlog(String id);

    void commentOnBlog(String comment, String blogId, int commentParentId);

    void editComment(String comment, int commentId, String blogId);

    Blog getSummary(String id);

}
