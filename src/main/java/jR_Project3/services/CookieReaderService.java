package jR_Project3.services;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;
@Slf4j
//TODO Разбить через Интерфейс?!
public class CookieReaderService {
    public Optional<String> readCookie(HttpServletRequest req, String cookieName) {
        log.debug("method readCookie(HttpServletRequest, String) started");
        if (req.getCookies() == null) {
        log.info("cookie {} not found", cookieName);
            return Optional.empty();
        }
        log.debug("method readCookie(HttpServletRequest, String) ended");
        return Arrays.stream(req.getCookies())
                .filter(cookie -> cookieName.equals(cookie.getName()))
                .map(Cookie::getValue)
                .findAny();
    }

    public Optional<String> readCookie(Cookie[] cookies, String cookieName) {
        log.debug("method readCookie(Cookie[], String) started");
        if (cookies == null) {
            log.info("cookie {} not found", cookieName);
            return Optional.empty();
        }
        log.debug("method readCookie(Cookie[], String) ended");
        return Arrays.stream(cookies)
                .filter(cookie -> cookieName.equals(cookie.getName()))
                .map(Cookie::getValue)
                .findAny();
    }

    public Optional<Cookie> getCookie(Cookie[] cookies, String cookieName) {
        log.debug("method getCookie(Cookie[], String) started");
        if (cookies == null) {
            log.info("cookie {} not found", cookieName);
            return Optional.empty();
        }
        log.debug("method getCookie(Cookie[], String) ended");
        return Arrays.stream(cookies)
                .filter(cookie -> cookieName.equals(cookie.getName()))
                .findAny();
    }
}
