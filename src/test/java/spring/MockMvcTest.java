package spring;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import springboot.EpitomeApplication;
import springboot.controller.DemoController;

/**
 * MockMvc
 * MockMvc 详解：https://www.cnblogs.com/jpfss/p/10950904.html
 * SpringBoot2 + Junit5 测试案例：https://www.cnblogs.com/xumBlog/p/12679134.html
 *
 * @author ljh
 * created on 2021/7/27 22:55
 */
@SpringBootTest(classes = EpitomeApplication.class)
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
    public void testRestFul() throws Exception {
        RequestBuilder requestBuilder;

        // postPerson
        requestBuilder = MockMvcRequestBuilders.post("/persons")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"张三\"}");
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        // getPersonList
        requestBuilder = MockMvcRequestBuilders.get("/persons")
                .param("keyword", "张");
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        // putUser
        requestBuilder = MockMvcRequestBuilders.put("/persons/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"张山\"}");
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        // getPerson
        requestBuilder = MockMvcRequestBuilders.get("/persons/1");
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        // deletePerson
        requestBuilder = MockMvcRequestBuilders.delete("/persons/1");
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("")));

        requestBuilder = MockMvcRequestBuilders.get("/persons");
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("[ ]")));
    }
}
