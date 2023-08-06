package jR_Project3.builders.users;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import jR_Project3.interfaces.UserBuilder;
import jR_Project3.models.users.AbstractUser;
import jR_Project3.models.users.UserDto;

@JsonPOJOBuilder(withPrefix = "set")
public class SimpleUserDtoBuilder implements UserBuilder {
    private String name;
    @Override
    public UserBuilder name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public AbstractUser build() {
        return new UserDto(this);
    }
}
