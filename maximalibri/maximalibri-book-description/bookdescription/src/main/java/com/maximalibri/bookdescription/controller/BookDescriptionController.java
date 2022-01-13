package com.maximalibri.bookdescription.controller;

import com.maximalibri.bookdescription.dto.PrimitiveWrapper;
import com.maximalibri.bookdescription.service.BookDescriptionService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/book-description")
public class BookDescriptionController {

    private final BookDescriptionService bookDescriptionService;

    public BookDescriptionController(BookDescriptionService bookDescriptionService) {
        this.bookDescriptionService = bookDescriptionService;
    }

    @GetMapping("/{isbn}")
    public PrimitiveWrapper<String> getBookDescription(@PathVariable("isbn") String isbn) {
        return new PrimitiveWrapper<>(bookDescriptionService.getBookDescription(isbn));
    }
}
