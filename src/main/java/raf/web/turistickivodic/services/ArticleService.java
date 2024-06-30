package raf.web.turistickivodic.services;

import raf.web.turistickivodic.entities.Article;
import raf.web.turistickivodic.repositories.article.ArticleRepository;

import javax.inject.Inject;
import java.util.List;

public class ArticleService {

    @Inject
    private ArticleRepository articleRepository;

    public Article addArticle(Article destination) {
        return this.articleRepository.addArticle(destination);
    }

    public List<Article> allArticles() {
        return this.articleRepository.allArticles();
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



}
