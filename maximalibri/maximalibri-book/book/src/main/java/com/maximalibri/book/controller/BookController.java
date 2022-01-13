package com.maximalibri.book.controller;

import com.maximalibri.book.dto.CustomPageImpl;
import com.maximalibri.book.dto.PrimitiveWrapper;
import com.maximalibri.book.model.Book;
import com.maximalibri.book.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/search")
    public CustomPageImpl<Book> search(
            @RequestParam(value = "searchParam", required = false) String searchParam,
            @RequestParam("page") Integer page
    ) {
        return bookService.getSearchResults(searchParam, page);
    }

    @PostMapping("/find-by-isbn-list")
    public List<Book> findByIsbnList(@RequestBody PrimitiveWrapper<List<String>> isbnList) {
        return bookService.findByIsbnList(isbnList.getValue());
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Book> getBook(@PathVariable("isbn") String isbn) {
        Optional<Book> maybeBook = this.bookService.findByIsbn(isbn);
        return maybeBook.map(book -> new ResponseEntity<>(book, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }
}
