package jR_Project3.builders.users;

import jR_Project3.interfaces.UserBuilder;
import jR_Project3.models.users.AbstractUser;
import jR_Project3.models.users.User;

public class SimpleUserBuilder implements UserBuilder {
    private String name;

    //public SimpleUserBuilder() {}

    @Override
    public UserBuilder name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public AbstractUser build() {
        return new User(this);
    }
}
