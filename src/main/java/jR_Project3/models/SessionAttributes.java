package jR_Project3.models;

import lombok.Getter;

@Getter
public enum SessionAttributes {
    STEPS_IN_GAME("stepsInGame"),
    BOOK("book"),
    INFO_FORM_DTO("infoFormDTO"),
    USER("user");

    private final String name;
    SessionAttributes(String name){
        this.name = name;
    }
}
