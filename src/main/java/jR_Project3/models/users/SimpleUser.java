package jR_Project3.models.users;

import jR_Project3.interfaces.UserBuilder;

public class SimpleUser extends AbstractUser {
    private String name;

    public SimpleUser(UserBuilder builder) {
        name = builder.getName();
    }

    @Override
    public String getName() {
        return name;
    }
}
