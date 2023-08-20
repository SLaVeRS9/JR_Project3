package jR_Project3.dto;

import jR_Project3.interfaces.UserBuilder;
import jR_Project3.models.users.AbstractUser;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class UserDTO extends AbstractUser implements Serializable {
    private final String name;

    public UserDTO(UserBuilder builder) {
        name = builder.getName();
    }
}

