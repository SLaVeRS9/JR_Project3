package jR_Project3.models.books;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Part {
    private final Integer number;
    private final String title;
    private final String background;
    private final String text;
    private final List<Button> buttons;
}
