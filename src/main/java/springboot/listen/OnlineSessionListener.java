package springboot.listen;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * OnlineSessionListener
 *
 * @author Arsenal
 * created on 2019/7/22 11:44
 */
@WebListener
public class OnlineSessionListener implements HttpSessionListener {

    private int count = 0;

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        String oldSessionId = (String) httpSessionEvent.getSession().getServletContext().getAttribute("sessionId");
        String curSessionId = httpSessionEvent.getSession().getId();
        if (!oldSessionId.equals(curSessionId)) {
            count++;
            httpSessionEvent.getSession().getServletContext().setAttribute("count", count);
        } else {
            httpSessionEvent.getSession().getServletContext().setAttribute("count", count);
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        count--;
        httpSessionEvent.getSession().getServletContext().setAttribute("count", count);
    }
}