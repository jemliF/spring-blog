package com.jfcode.blog.repositories;

import com.jfcode.blog.entities.Article;
import com.jfcode.blog.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by root on 08/12/16.
 */
public interface ArticleRepository extends JpaRepository<Article, Long> {
    public Article findById(Long id);
}
