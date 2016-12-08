package com.jfcode.blog.repositories;

import com.jfcode.blog.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by root on 08/12/16.
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {
    public Author findById(Long id);
    public Author findByEmail(String email);
}
