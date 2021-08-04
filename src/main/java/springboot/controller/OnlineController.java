package springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.listener.OnlineNumberListener;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 使用 HttpSessionListener 实现统计在线人数
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
@RestController
@RequestMapping("/online")
public class OnlineController {

    @SuppressWarnings("unchecked")
    @RequestMapping("/number")
    public String online(HttpServletRequest request) {
        List<OnlineNumberListener.HostInfo> hostInfoList = (List<OnlineNumberListener.HostInfo>) request.getSession().getServletContext().getAttribute("hostInfoList");
        return "count : " + hostInfoList.size();
    }
}
