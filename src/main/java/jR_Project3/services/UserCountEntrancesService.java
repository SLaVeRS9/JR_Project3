package jR_Project3.services;

public class UserCountEntrancesService {
    private static final Integer INIT_USER_COUNT_ENTRANCES = 0;
    public Integer incrementUserCountEntrances (String userCountEntrances) {
        if (userCountEntrances == null) {
            return INIT_USER_COUNT_ENTRANCES;
        }
        Integer intUserCountEntrances = Integer.valueOf(userCountEntrances);
        return ++intUserCountEntrances;
    }

    public Integer incrementUserCountEntrances (Integer userCountEntrances) {
        if (userCountEntrances == null) {
            return INIT_USER_COUNT_ENTRANCES;
        }
        return ++userCountEntrances;
    }
}
