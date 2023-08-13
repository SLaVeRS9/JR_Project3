package jR_Project3.models;

public enum CookiesNames {
    USER_NAME_COOKIE("UserName"),
    USER_COUNT_ENTRANCES_COOKIE("UserEntrances");
    private final String cookieName;
    CookiesNames(String cookieName) {
        this.cookieName = cookieName;
    }

    public String getName(){
        return cookieName;
    }
}
