package jR_Project3.builders.users;

import jR_Project3.dto.UserDTO;
import jR_Project3.interfaces.UserBuilder;

public class SimpleUserDTOBuilder implements UserBuilder {
    private String name;

    public SimpleUserDTOBuilder() {
        super();
    }

    @Override
    public SimpleUserDTOBuilder name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public UserDTO build() {
        return new UserDTO(this);
    }

    @Override
    public String getName() {
        return name;
    }
}
