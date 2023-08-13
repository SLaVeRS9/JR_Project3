package jR_Project3.servlets;

import jR_Project3.models.books.Book;
import jR_Project3.services.BookReaderServiceJSON;
import jR_Project3.services.CookieReaderService;
import jR_Project3.mappers.InfoFormMapper;
import jR_Project3.builders.users.SimpleUserDTOBuilder;
import jR_Project3.dto.InfoFormDTO;
import jR_Project3.dto.UserDTO;
import jR_Project3.models.CookiesNames;
import jR_Project3.models.SessionContextForForm;
import jR_Project3.services.StepsInGameService;
import jR_Project3.services.UserCountEntrancesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/start")
public class StartBookServlet extends HttpServlet {
    private static final String DEFAULT_NAME = "Путник";
    private static final Integer INIT_USER_COUNT_ENTRANCES = 0;
    private static final String BOOK_PATH = "/WEB-INF/classes/book.json";
    private static final Integer INIT_PART = 0;
    private static Logger LOGGER = LoggerFactory.getLogger(TestServlet.class);
    private final static CookieReaderService cookieReaderService = new CookieReaderService();
    private final static UserCountEntrancesService userCountEntrancesService = new UserCountEntrancesService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("doGet");

        HttpSession session = req.getSession();
        session.getServletContext().getSessionCookieConfig();
        Integer stepsInGame = StepsInGameService.initStepsInGame();
        session.setAttribute("stepsInGame", stepsInGame);

        Cookie[] cookies = req.getCookies();

        ServletContext servletContext = getServletContext();
        String fullBookPath = servletContext.getRealPath(BOOK_PATH);

        File bookFile = new File(fullBookPath);
        Book book = new BookReaderServiceJSON().jsonToBook(bookFile);
        session.setAttribute("book", book);
        req.setAttribute("initPart", INIT_PART);

        Optional<String> optionalUserName = cookieReaderService.readCookie(cookies, CookiesNames.USER_NAME_COOKIE.getName());
        String userName = optionalUserName.orElse(DEFAULT_NAME);
        if (userName.equalsIgnoreCase(DEFAULT_NAME)) {
            req.setAttribute("isRegistered", false);
        } else {
            req.setAttribute("isRegistered", true);
        }

        Optional<String> optionalUserCountEntrances = cookieReaderService.readCookie(cookies, CookiesNames.USER_COUNT_ENTRANCES_COOKIE.getName());
        String userCountEntrances = optionalUserCountEntrances.orElse(Integer.toString(INIT_USER_COUNT_ENTRANCES));
        if (Integer.valueOf(userCountEntrances) != INIT_USER_COUNT_ENTRANCES) {
            userCountEntrances = String.valueOf(userCountEntrancesService.incrementUserCountEntrances(userCountEntrances));
            resp.addCookie(new Cookie(CookiesNames.USER_COUNT_ENTRANCES_COOKIE.getName(), userCountEntrances));
        }

        UserDTO user = new SimpleUserDTOBuilder().name(userName).build();

        SessionContextForForm sessionContextForForm = new SessionContextForForm().getSessionContextForForm(req);

        InfoFormDTO infoFormDTO = InfoFormMapper.getInfoFormDTO(user, sessionContextForForm, Integer.valueOf(userCountEntrances));

        session.setAttribute("infoFormDTO", infoFormDTO);
        session.setAttribute("user", user);

        req.getRequestDispatcher("/start.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("doPost");
        HttpSession session = req.getSession();

        String name = req.getParameter("userName");
        InfoFormDTO infoFormDTO = (InfoFormDTO) session.getAttribute("infoFormDTO");
        infoFormDTO.setUserName(name);
        UserDTO user = new SimpleUserDTOBuilder().name(name).build();

        LOGGER.info("Create user request received: {}", user);

        Integer userCountEntrances = INIT_USER_COUNT_ENTRANCES;
        resp.addCookie(new Cookie(CookiesNames.USER_NAME_COOKIE.getName(), name));
        resp.addCookie(new Cookie(CookiesNames.USER_COUNT_ENTRANCES_COOKIE.getName(), Integer.toString(++userCountEntrances)));
        infoFormDTO.setCountPlayedGame(userCountEntrances);

        session.setAttribute("user", user);
        session.setAttribute("infoFormDTO", infoFormDTO);

        req.setAttribute("isRegistered", true);

        resp.sendRedirect("/page");
    }
}
