package com.jfcode.blog.controllers;

import com.jfcode.blog.entities.Article;
import com.jfcode.blog.repositories.ArticleRepository;
import com.jfcode.blog.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by root on 08/12/16.
 */
@RestController
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/api/v1/articles")
    public void create(@RequestBody Article article) {
        articleRepository.save(article);
    }

    @RequestMapping(value = "/api/v1/articles", method = RequestMethod.GET)
    public List<Article> getAll() {
        return articleRepository.findAll();
    }

    @RequestMapping(value = "/api/v1/articles/{id}", method = RequestMethod.GET)
    public Article get(@PathVariable Long id) {
        return articleRepository.findById(id);
    }

    @RequestMapping(value = "/api/v1/articles/{id}", method = RequestMethod.PUT)
    public Article update(@PathVariable Long id, @RequestBody Article article) {
        Article articleToUpdate = articleRepository.findById(id);
        articleToUpdate = article;
        articleRepository.saveAndFlush(articleToUpdate);
        return articleToUpdate;
    }

    @RequestMapping(value = "/api/v1/articles/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        articleRepository.delete(id);
    }
}
