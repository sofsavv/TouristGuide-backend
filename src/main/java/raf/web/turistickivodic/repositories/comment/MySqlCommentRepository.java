package raf.web.turistickivodic.repositories.comment;

import raf.web.turistickivodic.entities.Comment;
import raf.web.turistickivodic.repositories.MySqlAbstractRepository;

import java.util.List;

public class MySqlCommentRepository extends MySqlAbstractRepository implements CommentRepository {
    @Override
    public List<Comment> allComments(Integer articleId) {
        return null;
    }

    @Override
    public Comment addComment(Integer articleId, Comment comment) {
        return null;
    }

    @Override
    public void deleteComment(Integer commentId) {

    }
}
