package jR_Project3.services;

import jR_Project3.models.books.Book;
import jR_Project3.models.books.Part;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BookPartsService {
    public HashMap<Integer, Part> getParts(Book book) {
        List<Part> partsList = book.getParts();
        Map<Integer, Part> partsMap = partsList.stream()
                .collect(Collectors.toMap(Part::getNumber, part -> part));
        return new HashMap<>(partsMap);
    }

    public Part getPart(Book book, Integer part) {
        return getParts(book).get(part);
    }
}
