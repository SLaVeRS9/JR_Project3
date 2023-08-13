package jR_Project3.models;

import lombok.Getter;

@Getter
public enum ReqParameters {
    USER_NAME("userName");

    private final String name;

    ReqParameters(String name) {
        this.name = name;
    }

}
