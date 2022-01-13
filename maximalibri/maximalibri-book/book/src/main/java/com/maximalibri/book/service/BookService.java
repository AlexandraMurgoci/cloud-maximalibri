package com.maximalibri.book.service;

import com.maximalibri.book.dto.CustomPageImpl;
import com.maximalibri.book.model.Book;
import com.maximalibri.book.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public CustomPageImpl<Book> getSearchResults(String searchParameter, Integer page) {
        Pageable pageRequest = PageRequest.of(page, 10, Sort.by("title"));
        Page<Book> books = searchParameter!= null ?
                bookRepository.findAllByIsbnContainingIgnoreCaseOrTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(searchParameter, searchParameter, searchParameter, pageRequest) :
                bookRepository.findAll(pageRequest);
        return new CustomPageImpl<>(books.getContent(), books.getTotalPages(), books.getTotalElements());
    }

    @Transactional
    public Optional<Book> findByIsbn(String isbn) {
        return bookRepository.findById(isbn);
    }

    @Transactional
    public List<Book> findByIsbnList(List<String> isbnList) {
        return this.bookRepository.findAllById(isbnList);
    }
}
