package raf.web.turistickivodic.repositories.comment;

import raf.web.turistickivodic.entities.Comment;

import java.util.List;

public interface CommentRepository {

    public List<Comment> allCommentsInArticle(Integer articleId,int currentPage, int pageSize);
    public Comment addComment(Integer articleId, Comment comment);
    public void deleteComment(Integer commentId);


}
