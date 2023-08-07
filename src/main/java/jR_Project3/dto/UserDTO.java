package jR_Project3.dto;

import jR_Project3.interfaces.UserBuilder;
import jR_Project3.models.users.AbstractUser;

import java.io.Serializable;

public class UserDTO extends AbstractUser implements Serializable {
    private String name;

    public UserDTO(UserBuilder builder) {
        name = builder.getName();
    }

    @Override
    public String getName() {
        return name;
    }
}

