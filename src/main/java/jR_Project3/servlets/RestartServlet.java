package jR_Project3.servlets;

import jR_Project3.services.CookieReaderService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

import static jR_Project3.models.CookiesNames.*;

@WebServlet("/restart")
@Slf4j
public class RestartServlet extends HttpServlet {
    private static final CookieReaderService COOKIE_READER_SERVICE = new CookieReaderService();
    private static final Integer ZERO_COOKIE_AGE = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("started");
        Cookie[] cookies = req.getCookies();
        Optional<Cookie> optionalUserNameCookie = COOKIE_READER_SERVICE
                .getCookie(cookies, USER_NAME_COOKIE.getName());
        Optional<Cookie> optionalUserCountEntrancesCookie = COOKIE_READER_SERVICE
                .getCookie(cookies, USER_COUNT_ENTRANCES_COOKIE.getName());
        Optional<Cookie> optionalUserPartWhereStopped = COOKIE_READER_SERVICE
                .getCookie(cookies, PART_WHERE_STOPPED.getName());

        try {
            Cookie userNameCookie = optionalUserNameCookie.orElseThrow();
            userNameCookie.setMaxAge(ZERO_COOKIE_AGE);
            resp.addCookie(userNameCookie);
        } catch (NoSuchElementException exception) {
            log.warn(exception.getMessage());
        }

        try {
            Cookie userCountEntrancesCookie = optionalUserCountEntrancesCookie.orElseThrow();
            userCountEntrancesCookie.setMaxAge(ZERO_COOKIE_AGE);
            resp.addCookie(userCountEntrancesCookie);
        } catch (NoSuchElementException exception) {
            log.warn(exception.getMessage());
        }

        try {
            Cookie userPartWhereStopped = optionalUserPartWhereStopped.orElseThrow();
            userPartWhereStopped.setMaxAge(ZERO_COOKIE_AGE);
            resp.addCookie(userPartWhereStopped);
        } catch (NoSuchElementException exception) {
            log.warn(exception.getMessage());
        }

        req.getSession().invalidate();
        log.info("ended");
        resp.sendRedirect("/start");
    }
}
