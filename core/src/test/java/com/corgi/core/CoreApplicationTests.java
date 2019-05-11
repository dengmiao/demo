package com.corgi.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.corgi.core.modules.sys.entity.SysDemo;
import com.corgi.core.modules.sys.mapper.SysDemoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CoreApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Resource
    SysDemoMapper demoMapper;

    @Test
    public void contextLoads() {
    }

    @Test
    public void apiTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/auth/limited"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        int status = response.getStatus();
        System.out.println(status);
    }

    @Test
    public void json() {
        List<SysDemo> list = demoMapper.selectJson();
        System.out.println(list);
    }

    @Test
    public void inset() {
        /*demoMapper.inset(new SysDemo().setName("Tom").setJson(new JSONArray(){
            {
                add(new JSONObject(){
                    {
                        put("name", "Tom");
                        put("age", "20");
                    }
                });
                add(new JSONObject(){
                    {
                        put("name", "Tom");
                        put("sex", "男");
                    }
                });
            }
        }));*/
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        System.out.println(jsonObject instanceof JSON);
        System.out.println(jsonArray instanceof JSON);
        System.out.println(JSON.isValidArray("[{\"name\":\"Tom\",\"age\":\"20\"},{\"sex\":\"男\",\"name\":\"Tom\"}]"));
        System.out.println(JSON.isValidObject("[{\"age\": \"20\", \"name\": \"Tom\"}, {\"sex\": \"男\", \"name\": \"Tom\"}]"));
        System.out.println(JSON.isValidObject("{\"age\": \"20\", \"name\": \"Tom\"}"));
    }

}
