package jR_Project3.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jR_Project3.models.books.Book;

import java.io.File;
import java.io.IOException;

public class BookReaderServiceJSON {
    public Book jsonToBook(String jsonBook) throws JsonProcessingException {
        return new ObjectMapper().readValue(jsonBook, Book.class);
    }

    public Book jsonToBook(File jsonBook) throws IOException {
        return new ObjectMapper().readValue(jsonBook, Book.class);
    }
}
