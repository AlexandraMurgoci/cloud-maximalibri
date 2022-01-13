package com.maximalibri.book.repository;

import com.maximalibri.book.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, String>, PagingAndSortingRepository<Book, String> {

    Page<Book> findAllByIsbnContainingIgnoreCaseOrTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(String searchParam1, String searchParam2, String searchParam3, Pageable pageable);
}
