package raf.web.turistickivodic.repositories.article;

import raf.web.turistickivodic.entities.Article;
import raf.web.turistickivodic.repositories.MySqlAbstractRepository;

import java.util.List;

public class MySqlArticleRepository extends MySqlAbstractRepository implements ArticleRepository{


    @Override
    public Article findArticle(Integer id) {
        return null;
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
