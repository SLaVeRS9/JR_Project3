package jR_Project3.servlets;

import jR_Project3.builders.users.SimpleUserDTOBuilder;
import jR_Project3.dto.UserDTO;
import jR_Project3.models.SessionContextForForm;
import org.apache.logging.log4j.core.config.plugins.convert.TypeConverters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.stream.events.Characters;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet("/start")
public class InitServlet extends HttpServlet {
    private static Logger LOGGER = LoggerFactory.getLogger(TestServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("doGet");
        HttpSession session = req.getSession();
        session.getServletContext().getSessionCookieConfig();

        if (session.getAttribute("stepsInGame") != null) {
            String steps = String.valueOf(session.getAttribute("stepsInGame"));
            Integer intSteps = Integer.parseInt(steps);
            intSteps++;
            session.setAttribute("stepsInGame", intSteps.toString());
        } else {
            session.setAttribute("stepsInGame", "1");
        }

        session.setAttribute("sessionAttribute", new SessionContextForForm().get(req));

        req.getRequestDispatcher("/start.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("doPost");
        HttpSession session = req.getSession();
        /*session.getServletContext().getSessionCookieConfig();*/

        String name = req.getParameter("userName");

        byte[] nameUtf8Byte = name.getBytes("windows-1251");
        String nameUtf8String = new String(nameUtf8Byte, Charset.forName("windows-1251"));

        UserDTO user = new SimpleUserDTOBuilder().name(name).build();

        LOGGER.info("Create user request received: {}", user);

        session.setAttribute("user", user);

        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
