package jR_Project3.models;

import jR_Project3.interfaces.UserBuilder;

public class User {
    private String name;

    private User() {}

    public User(UserBuilder builder) {
        User user =  builder.build();
        name = user.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
