package jR_Project3.models;

import lombok.Getter;

@Getter
public enum ReqParameters {
    USER_NAME("userName"),
    PART("part");

    private final String name;

    ReqParameters(String name) {
        this.name = name;
    }

}
