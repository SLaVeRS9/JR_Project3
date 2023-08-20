package jR_Project3.models.books;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@ToString
public class Book {
    private final String name;
    private final Integer firstPart;
    private final List<Part> parts;
}
