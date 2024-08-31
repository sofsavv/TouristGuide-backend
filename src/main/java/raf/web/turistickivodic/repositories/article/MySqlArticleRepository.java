package raf.web.turistickivodic.repositories.article;

import raf.web.turistickivodic.entities.Article;
import raf.web.turistickivodic.repositories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlArticleRepository extends MySqlAbstractRepository implements ArticleRepository{


    @Override
    public Article findArticle(Integer id) {

        Article article = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM articles WHERE articleId = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                String title = resultSet.getString("title");
                String dateTime = resultSet.getString("dateTime");
                int visits = resultSet.getInt("visits");
                int destination = resultSet.getInt("destination");
                String user = resultSet.getString("author");
                article = new Article(id, title, dateTime, visits, destination, user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return article;
    }

    @Override
    public Article addArticle(Article article) {

        Connection connection = null;
        PreparedStatement articleStatement = null;
        PreparedStatement activityStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            connection.setAutoCommit(false);

            String[] generatedColumns = {"articleId"};
            articleStatement = connection.prepareStatement("INSERT INTO articles (title, dateTime, visits, destination, author) VALUES (?, ?, ?, ?, ?)", generatedColumns);
            articleStatement.setString(1, article.getTitle());
            articleStatement.setString(2, article.getDateTime());
            articleStatement.setInt(3, article.getVisits());
            articleStatement.setInt(4, article.getDestinationId());
            articleStatement.setString(5, article.getAuthor());
            resultSet = articleStatement.getGeneratedKeys();

            if (resultSet.next()) {
                article.setArticleId(resultSet.getInt(1));
            }

            activityStatement = connection.prepareStatement("INSERT INTO article_activity (articleId, activityId) VALUES (?, ?)");

            for(Integer activityId: article.getActivityIds()){
                activityStatement.setInt(1, article.getArticleId());
                activityStatement.setInt(2, activityId);
                activityStatement.executeUpdate();
            }

            connection.commit();


        }catch (SQLException ex){

            try {
                if (connection != null)
                    connection.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }

            ex.printStackTrace();

        }finally {
            this.closeResultSet(resultSet);
            this.closeStatement(activityStatement);
            this.closeStatement(articleStatement);
            this.closeConnection(connection);
        }

        return article;
    }

    @Override
    public List<Article> allArticles() {

        List<Article> articles = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            connection = this.newConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM articles");
            while(resultSet.next()){
                articles.add(new Article(resultSet.getInt("articleId"),
                        resultSet.getString("title"),
                        resultSet.getString("dateTime"),
                        resultSet.getInt("visits"),
                        resultSet.getInt("destination"),
                        resultSet.getString("author")));

            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return articles;
    }

    @Override
    public Article editArticle(Integer id, Article article) {

        Connection connection = null;
        PreparedStatement articleStatement = null;
        PreparedStatement activityDeleteStat = null;
        PreparedStatement activityInsertStat = null;

        try{
            connection = newConnection();
            connection.setAutoCommit(false);

            articleStatement = connection.prepareStatement("UPDATE articles SET title = ?, dateTime = ?, " +
                    "visits = ?, destination = ?, author = ? WHERE articleId = ?");
            articleStatement.setString(1, article.getTitle());
            articleStatement.setString(2, article.getDateTime());
            articleStatement.setInt(3, article.getVisits());
            articleStatement.setInt(4, article.getDestinationId());
            articleStatement.setString(5, article.getAuthor());
            articleStatement.setInt(6, id);
            articleStatement.executeUpdate();

            activityDeleteStat = connection.prepareStatement("DELETE FROM article_activity WHERE articleId = ?");
            activityDeleteStat.setInt(1, id);
            activityDeleteStat.executeUpdate();

            activityInsertStat = connection.prepareStatement("INSERT INTO article_activity (articleId, activityId) VALUES (?, ?)");

            for(Integer activityId: article.getActivityIds()){
                activityInsertStat.setInt(1, id);
                activityInsertStat.setInt(2, activityId);
                activityInsertStat.executeUpdate();
            }

            connection.commit();

        }catch (SQLException ex){
            ex.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
        }finally {
            this.closeConnection(connection);
            this.closeStatement(articleStatement);
            this.closeStatement(activityDeleteStat);
            this.closeStatement(activityInsertStat);
        }
        return article;
    }

    @Override
    public void deleteArticle(Integer id) {

        Connection connection = null;
        PreparedStatement articleStatement = null;
        PreparedStatement commentStatement = null;

        try{
            connection = this.newConnection();

            connection.setAutoCommit(false);

            commentStatement = connection.prepareStatement("DELETE FROM comments WHERE articleId = ?");
            commentStatement.setInt(1, id);
            commentStatement.executeUpdate();

            articleStatement = connection.prepareStatement("DELETE FROM articles WHERE articleId = ?");
            articleStatement.setInt(1, id);
            articleStatement.executeUpdate();

            connection.commit();


        }catch (SQLException ex){
            ex.printStackTrace();
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        }finally{
            this.closeStatement(articleStatement);
            this.closeStatement(commentStatement);
            this.closeConnection(connection);
        }


    }

}
