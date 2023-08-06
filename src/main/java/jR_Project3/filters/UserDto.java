package jR_Project3.filters;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jR_Project3.builders.users.SimpleUserDtoBuilder;
import jR_Project3.interfaces.UserBuilder;
import jR_Project3.models.users.AbstractUser;

@JsonDeserialize(builder = SimpleUserDtoBuilder.class)
public class UserDto extends AbstractUser {
    //private UserDto() {}

    public UserDto(UserBuilder builder) {
        AbstractUser user =  builder.build();
        name = user.getName();
    }
}
