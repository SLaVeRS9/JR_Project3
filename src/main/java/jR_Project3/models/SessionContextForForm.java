package jR_Project3.models;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionContextForForm {
    private String userIP;
    private String JSessionID;
    private Integer stepsInGame;

    public SessionContextForForm getSessionContextForForm(HttpServletRequest req) {
        HttpSession session = req.getSession();

        JSessionID = session.getId();

        userIP = req.getRemoteAddr();

        stepsInGame = (Integer) session.getAttribute("stepsInGame");

        return this;
    }

    public String getUserIP() {
        return userIP;
    }

    public String getJSessionID() {
        return JSessionID;
    }

    public Integer getStepsInGame() {
        return stepsInGame;
    }

}
