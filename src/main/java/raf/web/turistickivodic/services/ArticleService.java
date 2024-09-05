package raf.web.turistickivodic.services;

import raf.web.turistickivodic.entities.Article;
import raf.web.turistickivodic.repositories.article.ArticleRepository;

import javax.inject.Inject;
import java.util.List;

public class ArticleService {

    @Inject
    private ArticleRepository articleRepository;

    public Article addArticle(Article article) {
        return this.articleRepository.addArticle(article);
    }

    public List<Article> allArticles(int currentPage, int pageSize) {
        return this.articleRepository.allArticles(currentPage, pageSize);
    }

    public Article findArticle(Integer id) {
        return this.articleRepository.findArticle(id);
    }
    public Article updateArticle(Integer id, Article article){
        return this.articleRepository.editArticle(id, article);
    }
    public void deleteArticle(Integer id) {
        this.articleRepository.deleteArticle(id);
    }

    public List<Article> findArticlesByDestination(Integer destinationId){
         return this.articleRepository.findArticlesByDestination(destinationId);
    }
    public List<Article> findMostReadArticles(){
        return this.articleRepository.findMostReadArticles();
    }
    public void incrementViews(Integer articleId){
        this.articleRepository.inc(articleId);
    }

}
