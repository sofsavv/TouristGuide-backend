package raf.web.turistickivodic.repositories.article;

import raf.web.turistickivodic.entities.Article;

import java.util.List;

public interface ArticleRepository {

    public Article findArticle(Integer id);
    public Article addArticle(Article article);
    public List<Article> allArticles();
    public Article addComment(Integer articleId); // ??
    public Article editArticle(Integer id, Article article);
    public void deleteArticle(Integer id);


}
