package jR_Project3.interfaces;

import jR_Project3.models.User;

public interface UserBuilder {
    public UserBuilder name(String name);
    public User build();
}
