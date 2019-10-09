package com.joking.javamockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JavaMockitoApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void test1() {
        List list = mock(List.class);
        list.add("one");
        list.add("two");
        list.clear();

        verify(list).add("one");
        verify(list).add("two");
        verify(list).clear();
    }

    @Test
    public void test2(){
        List list = mock(List.class);

        when(list.get(0)).thenReturn("first");
        when(list.get(1)).thenThrow(new NullPointerException());

        System.out.println(list.get(0));

//        System.out.println(list.get(1));

        System.out.println(list.get(999));

        verify(list.get(0));
    }

    @Test
    public void testHello() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(TestController.class).build();
        mockMvc.perform(get("/hello?name=joking").param("name", "joking1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testReturnParam() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(TestController.class).build();
        mockMvc.perform(
                get("/entity")
                        .param("id", anyInt() + "")
                        .param("name", anyString())
                .param("text", anyString())
        ).andDo(print())
                .andExpect(status().isOk());
    }

}
