package jR_Project3.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jR_Project3.models.books.Book;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

@Slf4j
public class BookReaderServiceJSON {
    public Book jsonToBook(String jsonBook) throws JsonProcessingException {
        log.debug("method jsonToBook(String) lunched");
        return new ObjectMapper().readValue(jsonBook, Book.class);
    }

    public Book jsonToBook(File jsonBook) throws IOException {
        log.debug("method jsonToBook(File) lunched");
        return new ObjectMapper().readValue(jsonBook, Book.class);
    }
}
