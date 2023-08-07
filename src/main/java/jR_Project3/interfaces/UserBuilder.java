package jR_Project3.interfaces;

import jR_Project3.models.users.AbstractUser;

public interface UserBuilder {
    public UserBuilder name(String name);
    public AbstractUser build();

    public String getName();
}
