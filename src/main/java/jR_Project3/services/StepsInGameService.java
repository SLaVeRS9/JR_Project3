package jR_Project3.services;

import jR_Project3.models.SessionAttributes;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpSession;

import static jR_Project3.models.SessionAttributes.STEPS_IN_GAME;

@Slf4j
public class StepsInGameService {
    private static final Integer INIT_STEP = 0;
    public static Integer initStepsInGame() {
        return INIT_STEP;
    }

    public static void incrementStepsInGame(HttpSession session) {
        log.debug("method incrementStepsInGame(HttpSession) started");
        if (session.getAttribute(STEPS_IN_GAME.getName()) != null) {
            Integer step = (Integer) session.getAttribute(STEPS_IN_GAME.getName());
            log.info("Attribute {} = {}", STEPS_IN_GAME.getName(), step);
            step++;
            session.setAttribute(STEPS_IN_GAME.getName(), step);
        } else {
            log.info("Attribute {} not found", STEPS_IN_GAME.getName());
            session.setAttribute(STEPS_IN_GAME.getName(), INIT_STEP);
        }
        log.debug("method incrementStepsInGame(HttpSession) ended");
    }
}
