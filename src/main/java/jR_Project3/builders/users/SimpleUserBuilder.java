package jR_Project3.builders.users;

import jR_Project3.interfaces.UserBuilder;
import jR_Project3.models.users.SimpleUser;

public class SimpleUserBuilder implements UserBuilder {
    private String name;

    public SimpleUserBuilder() {
        super();
    }

    @Override
    public UserBuilder name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public SimpleUser build() {
        return new SimpleUser(this);
    }

    @Override
    public String getName() {
        return name;
    }
}
