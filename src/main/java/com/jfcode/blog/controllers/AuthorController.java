package com.jfcode.blog.controllers;

import com.jfcode.blog.entities.Author;
import com.jfcode.blog.repositories.AuthorRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by root on 08/12/16.
 */
@RestController
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/api/v1/authors")
    public void create(@RequestBody Author author) {
        System.out.println(author);
        author.setPassword(BCrypt.hashpw(author.getPassword(), BCrypt.gensalt(12)));
        authorRepository.save(author);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/v1/authors/login")
    public Author login(@RequestParam String email, @RequestParam String password, HttpServletResponse response) {
        Author author = authorRepository.findByEmail(email);
        if (BCrypt.checkpw(password, author.getPassword())) {
            return author;
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
    }

    @RequestMapping(value = "/api/v1/authors", method = RequestMethod.GET)
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @RequestMapping(value = "/api/v1/authors/{id}", method = RequestMethod.GET)
    public Author get(@PathVariable Long id) {
        return authorRepository.findById(id);
    }

    @RequestMapping(value = "/api/v1/authors/{id}", method = RequestMethod.PUT)
    public Author update(@PathVariable Long id, @RequestBody Author author) {
        Author authorToUpdate = authorRepository.findById(id);
        author.setPassword(BCrypt.hashpw(author.getPassword(), BCrypt.gensalt(12)));
        authorToUpdate = author;
        authorRepository.saveAndFlush(authorToUpdate);
        return authorToUpdate;
    }

    @RequestMapping(value = "/api/v1/authors/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        authorRepository.delete(id);
    }
}
