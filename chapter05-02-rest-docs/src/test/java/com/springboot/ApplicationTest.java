package com.springboot;

import com.springboot.controller.PeopleController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @description: //TODO ApplicationTest
 * @author Louis
 * @date 2019/6/22 14:49
 * @param
 * @return
 */
@RunWith(SpringRunner.class)
@WebMvcTest(PeopleController.class)
//表示生成snippets文件夹，并指定存放位置
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test() throws Exception {
        this.mockMvc.perform(get("/people/")).andDo(print()).andExpect(status().isOk())
                .andDo(document("home"));
    }
}
