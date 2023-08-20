package jR_Project3.interfaces;

import javax.servlet.http.HttpServletRequest;

public interface SessionContext<T> {
    T get(HttpServletRequest req);
}
