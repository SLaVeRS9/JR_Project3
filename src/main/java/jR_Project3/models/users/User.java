package jR_Project3.models.users;

import jR_Project3.interfaces.UserBuilder;

public class User extends AbstractUser {
    private User() {}

    public User(UserBuilder builder) {
        AbstractUser user =  builder.build();
        name = user.getName();
    }
}
