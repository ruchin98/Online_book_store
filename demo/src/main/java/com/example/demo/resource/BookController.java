package com.example.demo.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;

@RestController
public class BookController {

    @Autowired
    private BookRepository repository;

    @PostMapping("/addBook")
    public String saveBook(@RequestBody Book book) {
        if(repository.findById(book.getId()) != null) return "book with that id already exists";
        repository.save(book);
        return "Added book with id : " + book.getId();
    }

    @GetMapping("/findAllBooks")
    public List<Book> getBooks() {
        return repository.findAll();
    }

    @GetMapping("/findAllBooks/{id}")
    public Optional<Book> getBook(@PathVariable int id) {

        if(repository.findById(id) == null)
        {
            System.out.println(" No book found");
        }
        return repository.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable int id) {

        if(repository.findById(id) == null) return "No book found for deletion for this id";
        repository.deleteById(id);
        return "book deleted with id : " + id;
    }

    @PutMapping("/put/{id}")
    public String updateBook(@PathVariable int id, @RequestBody Book newBook) {

        if (repository.findById(id) == null) return " No book available for that id";
        repository.findById(id).map(book-> {
            book.setId(newBook.getId());
            book.setAuthorName(newBook.getAuthorName());
            book.setBookName(newBook.getBookName());
            return repository.save(book);
        });
        return "book updated with id : " + id;
    }

}