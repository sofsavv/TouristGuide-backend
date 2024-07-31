package raf.web.turistickivodic.repositories.article;

import raf.web.turistickivodic.entities.Article;
import raf.web.turistickivodic.repositories.MySqlAbstractRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                String user = resultSet.getString("user");
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
        return null;
    }

    @Override
    public List<Article> allArticles() {
        return null;
    }

    @Override
    public Article addComment(Integer articleId) {
        return null;
    }

    @Override
    public Article editArticle(Integer id, Article article) {
        return null;
    }

    @Override
    public void deleteArticle(Integer id) {

    }

}
