package raf.web.turistickivodic.services;

import raf.web.turistickivodic.entities.Comment;
import raf.web.turistickivodic.repositories.comment.CommentRepository;

import javax.inject.Inject;
import java.util.List;

public class CommentService {

    @Inject
    private CommentRepository commentRepository;

    public void addComment(Integer articleId, Comment comment) {
        this.commentRepository.addComment(articleId, comment);
    }

    public List<Comment> allComments(Integer articleId, int currentPage, int pageSize) {
        return this.commentRepository.allCommentsInArticle(articleId, currentPage, pageSize);
    }
    public void deleteComment(Integer commentId){
        this.commentRepository.deleteComment(commentId);
    }


}
