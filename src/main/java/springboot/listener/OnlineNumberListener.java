package springboot.listener;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;
import java.util.List;

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
 * @author ljh
 * @since 2019/7/22 11:44
 */
@WebListener
public class OnlineNumberListener implements HttpSessionListener, ServletRequestListener {

    private HttpServletRequest request;

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        request = (HttpServletRequest) sre.getServletRequest();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession httpSession = se.getSession();
        String sessionId = httpSession.getId();
        ServletContext servletContext = httpSession.getServletContext();
        List<HostInfo> hostInfoList = (List<HostInfo>) servletContext.getAttribute("hostInfoList");
        if (hostInfoList != null) {
            for (HostInfo hostInfo : hostInfoList) {
                if (StringUtils.equals(sessionId, hostInfo.getSessionId())) return;
            }
        } else {
            hostInfoList = new ArrayList<>();
        }
        hostInfoList.add(new HostInfo().setSessionId(sessionId).setRemoteHost(request.getRemoteHost()).setRemoteAddress(request.getRemoteAddr()));
        servletContext.setAttribute("hostInfoList", hostInfoList);
    }

    /**
     * 关闭浏览器，web 服务器是不知道的，所以并不会触发 sessionDestroyed
     * session 被销毁只有两个途径，一个是 session 过期，另外一个是前端调用 session.invalidate()
     */
    @SuppressWarnings("unchecked")
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession httpSession = se.getSession();
        String sessionId = httpSession.getId();
        ServletContext servletContext = httpSession.getServletContext();
        List<HostInfo> hostInfoList = (List<HostInfo>) servletContext.getAttribute("hostInfoList");
        for (HostInfo hostInfo : hostInfoList) {
            if (StringUtils.equals(sessionId, hostInfo.getSessionId())) {
                hostInfoList.remove(hostInfo);
                break;
            }
        }
        servletContext.setAttribute("hostInfoList", hostInfoList);
    }

    @Data
    @Accessors(chain = true)
    public static class HostInfo {
        private String sessionId;
        private String remoteHost;
        private String remoteAddress;
    }
}
