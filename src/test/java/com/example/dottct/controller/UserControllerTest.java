package com.example.dottct.controller;

import com.example.dottct.model.dto.UserDto;
import com.example.dottct.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    //픽스쳐
    private final String EMAIL = "JIW@naver.com";
    private final String PW = "1234";

    @BeforeEach
    void setUp() {
        UserDto userDto = UserDto.builder()
                .email(EMAIL)
                .pw((PW))
                .build();

        userService.signup(userDto);
    }

    @Test
    void 회원가입() throws Exception {
        String email = "jj@naver.com";
        String pw = "1234";
        UserDto userDto = UserDto.builder()
                                .email(email)
                                .pw(pw)
                                .build();
        String contentData = new ObjectMapper().writeValueAsString(userDto);

        ResultActions perform = mockMvc.perform(post("/signup")
                .content(String.valueOf(contentData))
                .contentType(MediaType.APPLICATION_JSON_VALUE));

        perform.andExpect(status().isCreated());
    }

    @Test
    void 로그인() throws Exception {
        String email = "JIW@naver.com";
        String pw = "1234";

        ResultActions perform = login(email, pw);

        perform.andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    private ResultActions login(String email, String pw) throws Exception {
        return mockMvc.perform(formLogin("/login")
                        .user(email)
                        .password(pw));
    }

    @Test
    void 로그아웃() throws Exception {
        String email = "JIW@naver.com";
        String pw = "1234";
        login(email, pw);

        ResultActions perform = mockMvc.perform(post("/logout")
                .with(csrf()));

        perform.andExpect(unauthenticated());
    }

}
