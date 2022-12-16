package springboot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.listener.OnlineNumberListener.HostInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 使用 HttpSessionListener 实现统计在线人数
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
@RestController
@RequestMapping("online")
@Tag(name = "Online")
public class OnlineController {

    @SuppressWarnings("unchecked")
    @GetMapping
    @Operation(summary = "获取在线人数")
    public String count(HttpServletRequest request) {
        List<HostInfo> hostInfoList = (List<HostInfo>) request.getSession().getServletContext().getAttribute("hostInfoList");
        return "count : " + hostInfoList.size();
    }
}
