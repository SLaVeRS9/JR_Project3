package jR_Project3.servlets;

import jR_Project3.models.*;
import jR_Project3.models.books.Book;
import jR_Project3.services.BookReaderServiceJSON;
import jR_Project3.services.CookieReaderService;
import jR_Project3.mappers.InfoFormMapper;
import jR_Project3.builders.users.SimpleUserDTOBuilder;
import jR_Project3.dto.InfoFormDTO;
import jR_Project3.dto.UserDTO;
import jR_Project3.services.StepsInGameService;
import jR_Project3.services.UserCountEntrancesService;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static jR_Project3.models.CookiesNames.USER_COUNT_ENTRANCES_COOKIE;
import static jR_Project3.models.CookiesNames.USER_NAME_COOKIE;
import static jR_Project3.models.ReqAttributes.IS_REGISTERED;
import static jR_Project3.models.ReqParameters.USER_NAME;
import static jR_Project3.models.SessionAttributes.*;

@WebServlet("/start")
public class StartBookServlet extends HttpServlet {
    private static final String DEFAULT_NAME = "Путник";
    private static final Integer INIT_USER_COUNT_ENTRANCES = 0;
    private static final String BOOK_PATH = "/WEB-INF/classes/book.json";
    private static final Integer INIT_PART = 0;
    private static final Logger LOGGER = LoggerFactory.getLogger(StartBookServlet.class);
    private final static CookieReaderService COOKIE_READER_SERVICE = new CookieReaderService();
    private final static UserCountEntrancesService USER_COUNT_ENTRANCES_SERVICE = new UserCountEntrancesService();
    private final static BookReaderServiceJSON BOOK_READER_SERVICE_JSON = new BookReaderServiceJSON();
    private final static SimpleUserDTOBuilder SIMPLE_USER_DTO_BUILDER = new SimpleUserDTOBuilder();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("doGet start");

        HttpSession session = req.getSession();
        session.getServletContext().getSessionCookieConfig();

        //TODO Добавить логику была ли концовка хорошей или нет
        /*try {
            if ((Integer) session.getAttribute(STEPS_IN_GAME.getName()) > 0) {}
        } catch (NullPointerException exception) {
            Integer stepsInGame = StepsInGameService.initStepsInGame();
            session.setAttribute(STEPS_IN_GAME.getName(), stepsInGame);
        }*/

        Integer stepsInGame = StepsInGameService.initStepsInGame();
        session.setAttribute(STEPS_IN_GAME.getName(), stepsInGame);
        LOGGER.info("Set steps in game = {}", stepsInGame);

        Cookie[] cookies = req.getCookies();

        ServletContext servletContext = getServletContext();
        String fullBookPath = servletContext.getRealPath(BOOK_PATH);
        LOGGER.info("Book path = {}", fullBookPath);

        File bookFile = new File(fullBookPath);
        Book book = BOOK_READER_SERVICE_JSON.jsonToBook(bookFile);
        session.setAttribute(SessionAttributes.BOOK.getName(), book);
        req.setAttribute(ReqAttributes.INIT_PART.getName(), INIT_PART);
        LOGGER.debug("Set book to session and part to request");

        Optional<String> optionalUserName = COOKIE_READER_SERVICE.readCookie(cookies, USER_NAME_COOKIE.getName());
        String userName = optionalUserName.orElse(DEFAULT_NAME);
        LOGGER.info("User name = {}", userName);
        if (userName.equalsIgnoreCase(DEFAULT_NAME)) {
            req.setAttribute(IS_REGISTERED.getName(), false);
            LOGGER.info("User is not registered");
        } else {
            req.setAttribute(IS_REGISTERED.getName(), true);
            LOGGER.info("User is registered");
        }

        Optional<String> optionalUserCountEntrances = COOKIE_READER_SERVICE.readCookie(cookies, USER_COUNT_ENTRANCES_COOKIE.getName());
        String userCountEntrances = optionalUserCountEntrances.orElse(Integer.toString(INIT_USER_COUNT_ENTRANCES));
        if (Integer.valueOf(userCountEntrances) != INIT_USER_COUNT_ENTRANCES) {
            userCountEntrances = String.valueOf(USER_COUNT_ENTRANCES_SERVICE.incrementUserCountEntrances(userCountEntrances));
            resp.addCookie(new Cookie(USER_COUNT_ENTRANCES_COOKIE.getName(), userCountEntrances));
        }

        UserDTO user = SIMPLE_USER_DTO_BUILDER.name(userName).build();

        //TODO Подумать переделать
        SessionContextForForm sessionContextForForm = new SessionContextForForm().getSessionContextForForm(req);

        InfoFormDTO infoFormDTO = InfoFormMapper.getInfoFormDTO(user, sessionContextForForm, Integer.valueOf(userCountEntrances));

        session.setAttribute(INFO_FORM_DTO.getName(), infoFormDTO);
        session.setAttribute(USER.getName(), user);

        LOGGER.debug("doGet end");
        LOGGER.debug("start forward");
        req.getRequestDispatcher("/start.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("doPost start");
        HttpSession session = req.getSession();

        String name = req.getParameter(USER_NAME.getName());
        LOGGER.info("User name = {}", name);
        InfoFormDTO infoFormDTO = (InfoFormDTO) session.getAttribute(INFO_FORM_DTO.getName());
        infoFormDTO.setUserName(name);
        UserDTO user = SIMPLE_USER_DTO_BUILDER.name(name).build();

        Integer userCountEntrances = INIT_USER_COUNT_ENTRANCES;
        LOGGER.info("User count entrances = {}", userCountEntrances);
        resp.addCookie(new Cookie(USER_NAME_COOKIE.getName(), name));
        resp.addCookie(new Cookie(USER_COUNT_ENTRANCES_COOKIE.getName(), Integer.toString(++userCountEntrances)));
        infoFormDTO.setCountPlayedGame(userCountEntrances);

        session.setAttribute(USER.getName(), user);
        session.setAttribute(INFO_FORM_DTO.getName(), infoFormDTO);

        req.setAttribute(IS_REGISTERED.getName(), true);

        LOGGER.debug("doPost end");
        LOGGER.debug("doPost start redirect");
        resp.sendRedirect("/page");
    }
}
