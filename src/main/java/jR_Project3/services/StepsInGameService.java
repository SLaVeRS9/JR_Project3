package jR_Project3.services;

import javax.servlet.http.HttpSession;

public class StepsInGameService {
    private static final Integer INIT_STEP = 0;
    public static Integer initStepsInGame() {
        return INIT_STEP;
    }

    public static void incrementStepsInGame(HttpSession session) {
        if (session.getAttribute("stepsInGame") != null) {
            Integer step = (Integer) session.getAttribute("stepsInGame");
            step++;
            session.setAttribute("stepsInGame", step);
        } else {
            session.setAttribute("stepsInGame", INIT_STEP);
        }
    }
}
