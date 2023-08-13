package jR_Project3.servlets;

import jR_Project3.dto.InfoFormDTO;
import jR_Project3.models.ReqParameters;
import jR_Project3.models.SessionAttributes;
import jR_Project3.models.books.Book;
import jR_Project3.models.books.Part;
import jR_Project3.services.BookPartsService;
import jR_Project3.services.StepsInGameService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static jR_Project3.models.ReqParameters.PART;
import static jR_Project3.models.SessionAttributes.*;

@WebServlet("/page")
@Slf4j
public class PageServlet  extends HttpServlet {
    //TODO Нужно ли объявлять экземпляры тут? Или лучше внутри методов? Или Все таки методы BookPartsService делать статическими?
    private final BookPartsService bookPartsService = new BookPartsService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("page servlet start");
        HttpSession session = req.getSession();
        StepsInGameService.incrementStepsInGame(session);

        InfoFormDTO infoFormDTO = (InfoFormDTO) session.getAttribute(INFO_FORM_DTO.getName());
        infoFormDTO.setStepsInGame((Integer)session.getAttribute(STEPS_IN_GAME.getName()));
        session.setAttribute(INFO_FORM_DTO.getName(), infoFormDTO);

        Book book = (Book) session.getAttribute(BOOK.getName());

        Integer currentPartNumber = req.getParameter(PART.getName()) != null
                ? Integer.valueOf(req.getParameter(PART.getName()))
                : book.getFirstPart();

        Part currentPart = bookPartsService.getPart(book, currentPartNumber);
        session.setAttribute(CURRENT_PART.getName(), currentPart);

        req.getRequestDispatcher("/page.jsp").forward(req, resp);
    }
}
