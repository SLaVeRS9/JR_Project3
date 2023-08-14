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

import static jR_Project3.models.CookiesNames.USER_COUNT_ENTRANCES_COOKIE;
import static jR_Project3.models.CookiesNames.USER_NAME_COOKIE;

@WebServlet("/restart")
@Slf4j
public class RestartServlet extends HttpServlet {
    private static final CookieReaderService cookieReaderService = new CookieReaderService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        Optional<Cookie> optionalUserNameCookie = cookieReaderService
                .getCookie(cookies, USER_NAME_COOKIE.getName());
        Optional<Cookie> optionalUserCountEntrancesCookie = cookieReaderService
                .getCookie(cookies, USER_COUNT_ENTRANCES_COOKIE.getName());

        try {
            Cookie userNameCookie = optionalUserNameCookie.orElseThrow();
            userNameCookie.setMaxAge(0);
            resp.addCookie(userNameCookie);
        } catch (NoSuchElementException exception) {
            log.warn(exception.getMessage());
        }

        try {
            Cookie userCountEntrancesCookie = optionalUserCountEntrancesCookie.orElseThrow();
            userCountEntrancesCookie.setMaxAge(0);
            resp.addCookie(userCountEntrancesCookie);
        } catch (NoSuchElementException exception) {
            log.warn(exception.getMessage());
        }

        req.getSession().invalidate();
        resp.sendRedirect("/start");
    }
}
