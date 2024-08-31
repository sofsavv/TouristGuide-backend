package raf.web.turistickivodic.repositories.comment;

import raf.web.turistickivodic.entities.Comment;
import raf.web.turistickivodic.repositories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlCommentRepository extends MySqlAbstractRepository implements CommentRepository {
    @Override
    public List<Comment> allComments(Integer articleId) {

        List<Comment> comments = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            connection = this.newConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM comments");
            while(resultSet.next()){
                comments.add(new Comment( resultSet.getInt("commentId"),
                        resultSet.getString("author"),
                        resultSet.getString("comment"),
                        resultSet.getString("dateTime"),
                        resultSet.getInt("articleId")));
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return comments;
    }

    @Override
    public Comment addComment(Integer articleId, Comment comment) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            String[] generatedColumns = {"commentId"};
            preparedStatement = connection.prepareStatement("INSERT INTO comments(author, comment, dateTime, articleId) VALUES (?,?,?,?)", generatedColumns);

            preparedStatement.setString(1, comment.getAuthor());
            preparedStatement.setString(2, comment.getComment());
            preparedStatement.setString(3, comment.getDate());
            preparedStatement.setInt(4, articleId);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                comment.setCommId(resultSet.getInt(1));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return comment;
    }

    @Override
    public void deleteComment(Integer commentId) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM comments WHERE commentId = ?");
            preparedStatement.setInt(1, commentId);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
    }
}
