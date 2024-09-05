package raf.web.turistickivodic.repositories.article;

import raf.web.turistickivodic.entities.Article;

import java.util.List;

public interface ArticleRepository {

    public Article findArticle(Integer id);
    public Article addArticle(Article article);
    public List<Article> allArticles(int currentPage, int pageSize);
    public Article editArticle(Integer id, Article article);
    public void deleteArticle(Integer id);
    public List<Article> findArticlesByDestination(Integer destinationId);
    List<Article> findMostReadArticles();
    void inc(Integer articleId);

}
