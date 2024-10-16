package com.polstat.perpustakaan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.polstat.perpustakaan.dto.BookDto;
import com.polstat.perpustakaan.gen.Book;
import com.polstat.perpustakaan.gen.CreateBookRequest;
import com.polstat.perpustakaan.gen.CreateBookResponse;
import com.polstat.perpustakaan.gen.GetBooksResponse;
import com.polstat.perpustakaan.gen.SearchBooksRequest;
import com.polstat.perpustakaan.gen.SearchBooksResponse;
import com.polstat.perpustakaan.service.BookService;
import java.util.List;

@Endpoint
public class BookEndpoint {
    private static final String NAMESPACE_URI = "http://www.polstat.com/perpustakaan/gen";

    @Autowired
    private BookService bookService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createBookRequest")
    @ResponsePayload
    public CreateBookResponse createBook(@RequestPayload CreateBookRequest request) {
        BookDto book = BookDto.builder()
            .title(request.getBook().getTitle())
            .author(request.getBook().getAuthor())
            .description(request.getBook().getDescription())
            .build();
        bookService.createBook(book);

        CreateBookResponse response = new CreateBookResponse();
        response.setStatusCode(201);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBooksRequest")
    @ResponsePayload
    public GetBooksResponse getBooks() {
        GetBooksResponse response = new GetBooksResponse();

        List<BookDto> books = bookService.getBooks();
        for (BookDto book : books) {
            Book it = new Book();
            it.setTitle(book.getTitle());
            it.setAuthor(book.getAuthor());
            it.setDescription(book.getDescription());
            
            response.getBooks().add(it);
        }

        return response;
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "searchBooksRequest")
    @ResponsePayload
    public SearchBooksResponse search(@RequestPayload  SearchBooksRequest request) {
        SearchBooksResponse response = new SearchBooksResponse();

        List<BookDto> books = bookService.search(request.getKeyword());
        for (BookDto book : books) {
            Book it = new Book();
            it.setTitle(book.getTitle());
            it.setAuthor(book.getAuthor());
            it.setDescription(book.getDescription());
            
            response.getBooks().add(it);
        }

        return response;
    }
}

