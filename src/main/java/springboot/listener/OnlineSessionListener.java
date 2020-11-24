package springboot.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Listener     监听器
 * 生命周期相关：
 * -    1.ServletRequestListener
 * -    2.HttpSessionListener：统计网站在线人数
 * -    3.ServletContextListener：统计网站历史访问次数，系统启动时初始化信息
 * 绑定数据相关：
 * -    1.ServletRequestAttributeListener
 * -    2.HttpSessionAttributeListener
 * -    3.ServletContextAttributeListener
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
        if (!curSessionId.equals(oldSessionId)) {
            count++;
        }
        httpSessionEvent.getSession().getServletContext().setAttribute("count", count);
    }

    /**
     * 关闭浏览器，web 服务器是不知道，所以并不会触发 sessionDestroyed。
     * session 被销毁只有两个途径，一个是 session 过期，另外一个是前端调用 session.invalidate()
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        httpSessionEvent.getSession().getServletContext().setAttribute("count", --count);
    }
}