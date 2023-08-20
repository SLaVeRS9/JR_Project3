package jR_Project3.mappers;

import jR_Project3.dto.InfoFormDTO;
import jR_Project3.models.SessionContextForForm;
import jR_Project3.models.users.AbstractUser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InfoFormMapper {
    public static InfoFormDTO getInfoFormDTO(AbstractUser user, SessionContextForForm sessionContextForForm, Integer countPlayedGame) {
        log.debug("InfoFormMapper called");
        return new InfoFormDTO.InfoFormDTOBuilder()
                .setUserName(user.getName())
                .setUserIP(sessionContextForForm.getUserIP())
                .setJSessionID(sessionContextForForm.getJSessionID())
                .setStepsInGame(sessionContextForForm.getStepsInGame())
                .setCountPlayedGame(countPlayedGame)
                .build();
    }
}
