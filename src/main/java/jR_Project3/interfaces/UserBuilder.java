package jR_Project3.interfaces;

import jR_Project3.models.users.AbstractUser;
import jR_Project3.models.users.User;

public interface UserBuilder {
    public UserBuilder name(String name);
    public AbstractUser build();
}
