package springboot.listen;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * MyServletRequestListener
 *
 * @author Arsenal
 * created on 2019/7/22 11:57
 */
public class MyServletRequestListener implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        String sessionId = (String) servletRequestEvent.getServletContext().getAttribute("sessionId");
        HttpServletRequest request = (HttpServletRequest) servletRequestEvent.getServletRequest();
        if (sessionId == null)  {
            sessionId = request.getSession().getId();
            servletRequestEvent.getServletContext().setAttribute("sessionId", sessionId);
        }

    }

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {

    }

}
