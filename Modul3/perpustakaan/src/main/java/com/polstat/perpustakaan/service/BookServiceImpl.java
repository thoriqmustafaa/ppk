package com.polstat.perpustakaan.service;

import com.polstat.perpustakaan.dto.BookDto;
import com.polstat.perpustakaan.entity.Book;
import com.polstat.perpustakaan.mapper.BookMapper;
import com.polstat.perpustakaan.repository.BookRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void createBook(BookDto bookDto) {
        bookRepository.save(BookMapper.mapToBook(bookDto));
    }

    @Override
    public List<BookDto> getBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookDto> bookDtos = books.stream()
                .map((product) -> (BookMapper.mapToBookDto(product)))
                .collect(Collectors.toList());
        return bookDtos;
    }

    @Override
    public List<BookDto> search(String book) {
        List<Book> books = bookRepository.findByAuthorContainingOrTitleContaining(book, book);
        List<BookDto> dtos = books.stream()
            .map((product) -> (BookMapper.mapToBookDto(product)))
            .collect(Collectors.toList());
        return dtos;
    }
}
