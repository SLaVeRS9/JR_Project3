package jR_Project3.services;

import lombok.extern.slf4j.Slf4j;

import static jR_Project3.models.SessionAttributes.STEPS_IN_GAME;

@Slf4j
public class UserCountEntrancesService {
    private static final Integer INIT_USER_COUNT_ENTRANCES = 0;
    public Integer incrementUserCountEntrances (String userCountEntrances) {
        log.debug("method incrementUserCountEntrances(String) started");
        if (userCountEntrances == null) {
            log.info("User count entrances = null");
            return INIT_USER_COUNT_ENTRANCES;
        }
        Integer intUserCountEntrances = Integer.valueOf(userCountEntrances);
        log.info("User count entrances = {}", intUserCountEntrances);
        log.debug("method incrementUserCountEntrances(String) ended");
        return ++intUserCountEntrances;
    }

    public Integer incrementUserCountEntrances (Integer userCountEntrances) {
        log.debug("method incrementUserCountEntrances(Integer) lunched");
        return ++userCountEntrances;
    }
}
