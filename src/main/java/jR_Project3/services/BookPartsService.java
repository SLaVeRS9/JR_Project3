package jR_Project3.services;

import jR_Project3.models.books.Book;
import jR_Project3.models.books.Part;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class BookPartsService {
    public HashMap<Integer, Part> getParts(Book book) {
        log.debug("method getParts started");
        List<Part> partsList = book.getParts();
        Map<Integer, Part> partsMap = partsList.stream()
                .collect(Collectors.toMap(Part::getNumber, part -> part));
        log.debug("result = {}", partsMap);
        log.debug("method getParts ended");
        return new HashMap<>(partsMap);
    }

    public Part getPart(Book book, Integer part) {
        log.debug("method getParts lunched");
        return getParts(book).get(part);
    }
}
