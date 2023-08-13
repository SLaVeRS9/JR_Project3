package jR_Project3.models;

import lombok.Getter;

@Getter
public enum ReqAttributes {
    INIT_PART("initPart"),
    IS_REGISTERED("isRegistered");

    private final String name;
    ReqAttributes(String name) {
        this.name = name;
    }
}
