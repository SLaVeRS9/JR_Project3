package jR_Project3.models;

public enum CookiesNames {
    USER_NAME_COOKIE("userName"),
    USER_COUNT_ENTRANCES_COOKIE("userEntrances"),
    PART_WHERE_STOPPED("partWhereStopped");
    private final String cookieName;
    CookiesNames(String cookieName) {
        this.cookieName = cookieName;
    }

    public String getName(){
        return cookieName;
    }
}
