package ru.akiboba.spring_in_action;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.akiboba.spring_in_action.web.HomeController;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@WebMvcTest(HomeController.class)
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testHomePageDone() {
        try {
            mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("home")).andExpect(content().string(containsString("Welcome to...")));

        } catch (Exception e) {
            log.error("ошибка тестового соединения с домашней страницей {}", e.getMessage());
        }
    }

    @Test
    void testHomePageFail() {
        try {
            mockMvc.perform(get("/a")).andExpect(status().is4xxClientError());

        } catch (Exception e) {
            log.error("ошибка тестового соединения с домашней страницей {}", e.getMessage());
        }
    }
}
