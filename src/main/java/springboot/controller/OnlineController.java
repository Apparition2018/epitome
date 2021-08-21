package springboot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * created on 2019/8/8 19:39
 */
@RestController
@RequestMapping("/online")
@Api(tags = "Online")
public class OnlineController {

    @SuppressWarnings("unchecked")
    @GetMapping("")
    @ApiOperation("获取在线人数")
    public String count(HttpServletRequest request) {
        List<HostInfo> hostInfoList = (List<HostInfo>) request.getSession().getServletContext().getAttribute("hostInfoList");
        return "count : " + hostInfoList.size();
    }
}
