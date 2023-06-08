package spring.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import spring.bean.OtherBean;
import spring.service.BmiService;

import java.util.Map;

/**
 * 数据绑定
 *
 * @author ljh
 * @since 2020/11/25 11:45
 */
@Controller
@RequestMapping("data-binding")
public class DataBindingController {

    private final BmiService bmiService;

    @Autowired
    public DataBindingController(BmiService bmiService) {
        this.bmiService = bmiService;
    }

    /** SpringMVC 默认采用转发方式定位视图 */
    @RequestMapping("toBmi")
    public String toBmi() {
        return "bmi/bmi";
    }

    /** HttpServletRequest 获取参数 */
    @RequestMapping("bmi1")
    public String bmi(HttpServletRequest request) {
        String height = request.getParameter("height");
        String weight = request.getParameter("weight");
        return bmi(Double.parseDouble(height), Double.parseDouble(weight), request);
    }

    /** &#064;RequestParam 获取请求参数并赋值给形参 */
    @RequestMapping("bmi2")
    public String bmi2(@RequestParam("height") String ht, String weight, HttpServletRequest request) {
        return bmi(Double.parseDouble(ht), Double.parseDouble(weight), request);
    }

    /** Bean 获取参数 */
    @RequestMapping("bmi3")
    public String bmi3(BmiParam bp, HttpServletRequest request) {
        return bmi(bp.getHeight(), bp.getWeight(), request);
    }

    /** ModelAndView 传递参数 */
    @RequestMapping("bmi4")
    public ModelAndView bmi4(BmiParam bp) {
        String bmi = bmiService.bmi(bp.getHeight(), bp.getWeight());
        return new ModelAndView("bmi/bmi2", Map.of("status", bmi));
    }

    /** ModelAndView 传递参数 */
    @RequestMapping("bmi5")
    public ModelAndView bmi5(BmiParam bp) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("status", bmiService.bmi(bp.getHeight(), bp.getWeight()));
        modelAndView.setViewName("bmi/bmi2");
        return modelAndView;
    }

    /** ModelMap 传递参数 */
    @RequestMapping("bmi6")
    public String bmi6(BmiParam bp, ModelMap mm) {
        mm.addAttribute("status", bmiService.bmi(bp.getHeight(), bp.getWeight()));
        return "bmi/bmi2";
    }

    /** HttpSession 传递参数 */
    @RequestMapping("bmi7")
    public String bmi7(BmiParam bp, HttpSession session) {
        session.setAttribute("status", bmiService.bmi(bp.getHeight(), bp.getWeight()));
        return "bmi/bmi2";
    }

    /** viewName 重定向 */
    @RequestMapping("redirect1")
    public String redirect1() {
        return "bmi/hello";
    }

    /** ModelAndView 和 RedirectView 重定向 */
    @RequestMapping("redirect2")
    public ModelAndView redirect2() {
        return new ModelAndView(new RedirectView("hello"));
    }

    @RequestMapping("hello")
    public String hello() {
        return "bmi/hello";
    }

    /** HttpServletRequest 传递参数 */
    private String bmi(double height, double weight, HttpServletRequest request) {
        String status = bmiService.bmi(height, weight);
        request.setAttribute("status", status);
        return "bmi/bmi2";
    }

    /** &#064;ExceptionHandler Spring 异常处理 */
    @RequestMapping("error")
    public String error() {
        throw new RuntimeException("error");
    }

    /**
     * &lt;mvc:annotation-driven/> 支持的 @NumberFormat，@DateTimeFormat
     *
     * <p><a href="http://localhost:3333/data-binding/format?salary=1,000&payDate=2008-08-08">data-binding/format?salary=1,000&payDate=2008-08-08</a>
     */
    @ResponseBody
    @RequestMapping("format")
    public OtherBean testFormat(OtherBean otherBean) {
        return otherBean;
    }

    @Getter
    private static class BmiParam {
        private double height;
        private double weight;
    }
}
