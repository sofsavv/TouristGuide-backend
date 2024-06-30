package raf.web.turistickivodic.repositories.comment;

import raf.web.turistickivodic.entities.Comment;

import java.util.List;

public interface CommentRepository {

    public List<Comment> allComments(Integer articleId);
    public Comment addComment(Integer articleId, Comment comment);


}
