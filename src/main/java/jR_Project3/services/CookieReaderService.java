package jR_Project3.services;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;

//TODO Разбить через Интерфейс?!
public class CookieReaderService {
    public Optional<String> readCookie(HttpServletRequest req, String cookieName) {
        if (req.getCookies() == null) {
            return Optional.empty();
        }
        return Arrays.stream(req.getCookies())
                .filter(cookie -> cookieName.equals(cookie.getName()))
                .map(Cookie::getValue)
                .findAny();
    }

    public Optional<String> readCookie(Cookie[] cookies, String cookieName) {
        if (cookies == null) {
            return Optional.empty();
        }
        return Arrays.stream(cookies)
                .filter(cookie -> cookieName.equals(cookie.getName()))
                .map(Cookie::getValue)
                .findAny();
    }

    public Optional<Cookie> getCookie(Cookie[] cookies, String cookieName) {
        if (cookies == null) {
            return Optional.empty();
        }
        return Arrays.stream(cookies)
                .filter(cookie -> cookieName.equals(cookie.getName()))
                .findAny();
    }
}
