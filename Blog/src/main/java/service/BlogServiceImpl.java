package service;

import beans.Blog;
import beans.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import repository.BlogRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class BlogServiceImpl implements BlogService {
    @Autowired
    BlogRepository blogRepository;

    @Override
    public Blog createBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public Boolean deleteBlog(String id) {
        blogRepository.deleteById(String.valueOf(id));
        return true;
    }

    @Override
    public Boolean disableBlog(String id) {
        Blog blog = blogRepository.findById(String.valueOf(id)).get();
        blog.setStatus("INACTIVE");
        blogRepository.save(blog);
        return true;
    }

    @Override
    public void likeBlog(String id) {
        Blog blog = blogRepository.findById(String.valueOf(id)).get();
        blog.setLikes(blog.getLikes() + 1);
        blogRepository.save(blog);
    }

    @Override
    public void dislikeBlog(String id) {
        Blog blog = blogRepository.findById(String.valueOf(id)).get();
        blog.setDislikes(blog.getDislikes() + 1);
        blogRepository.save(blog);
    }

    @Override
    public void commentOnBlog(String comment, String blogId, int commentParentId) {
        Blog blog = blogRepository.findById(String.valueOf(blogId)).get();
        List<Comments> commentsList = blog.getComments();
        if (!CollectionUtils.isEmpty(commentsList)) {
            Comments commentObj = new Comments();
            commentObj.setComment(comment);
            commentObj.setParentId(commentParentId == 0 ? -1 : commentParentId);
            commentsList.add(commentObj);
            blog.setComments(commentsList);
            blogRepository.save(blog);
        } else {
            List<Comments> commentsList1 = new ArrayList<>();
            Comments commentObj = new Comments();
            commentObj.setComment(comment);
            commentObj.setParentId(commentParentId == 0 ? -1 : commentParentId);
            commentsList1.add(commentObj);
            blog.setComments(commentsList1);
            blogRepository.save(blog);
        }

    }

    @Override
    public void editComment(String comment, int commentId, String blogId) {
        Blog blog = blogRepository.findById(String.valueOf(blogId)).get();
        List<Comments> commentsList = blog.getComments();
        List<Comments> updatedComments = new ArrayList<>();
        Iterator iterator = commentsList.iterator();
        while (iterator.hasNext()) {
            Comments comments = (Comments) iterator.next();
            if (comments.getId() == commentId) {
                Comments comments1 = comments;
                comments1.setComment(comment);
                updatedComments.add(comments1);
            } else {
                updatedComments.add(comments);
            }
        }
        blog.setComments(updatedComments);
        blogRepository.save(blog);
    }

    @Override
    public Blog getSummary(String id) {
        return blogRepository.findById(String.valueOf(id)).get();
    }
}
