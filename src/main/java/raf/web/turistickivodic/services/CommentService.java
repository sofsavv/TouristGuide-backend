package raf.web.turistickivodic.services;

import raf.web.turistickivodic.entities.Comment;
import raf.web.turistickivodic.repositories.comment.CommentRepository;

import javax.inject.Inject;
import java.util.List;

public class CommentService {

    @Inject
    private CommentRepository commentRepository;

    public Comment addComment(Integer articleId, Comment comment) {
        return this.commentRepository.addComment(articleId, comment);
    }

    public List<Comment> allComments(Integer articleId) {
        return this.commentRepository.allComments(articleId);
    }



}
