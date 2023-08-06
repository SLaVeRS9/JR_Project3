package jR_Project3.builders;

import jR_Project3.interfaces.UserBuilder;
import jR_Project3.models.User;

public class SimpleUserBuilder implements UserBuilder {
    private String name;

    public SimpleUserBuilder() {}

    @Override
    public UserBuilder name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public User build() {
        return new User(this);
    }
}
