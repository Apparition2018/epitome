import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import springboot.Application;
import springboot.controller.DemoController;

/**
 * MockMvc
 * MockMvc 详解：https://www.cnblogs.com/jpfss/p/10950904.html
 * SpringBoot2 + Junit5测试案例：https://www.cnblogs.com/xumBlog/p/12679134.html
 *
 * @author ljh
 * created on 2021/7/27 22:55
 */
@SpringBootTest(classes = Application.class)
public class MockMvcTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {
        // 实例化方式一
        mockMvc = MockMvcBuilders.standaloneSetup(new DemoController()).build();
        // 实例化方式二
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(
                // 构造一个请求
                MockMvcRequestBuilders.get("/demo/get")
                        // 设置返回值类型为 UTF-8，默认 ISO-8859-1
                        .accept(MediaType.APPLICATION_JSON)
                        // 设置参数
                        .param("id", "1")
                        .param("name", "ljh")
        )
                // 添加执行完成后的断言
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Hello Tom!"))
                // 添加一个结果处理器，表示要对结果做点什么事情；比如 MockMvcResultHandlers.print() 输出整个响应结果信息
                .andDo(MockMvcResultHandlers.print());
                // 执行完成后返回相应的结果
                // .andReturn();
    }
}
