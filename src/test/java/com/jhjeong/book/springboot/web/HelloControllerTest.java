package com.jhjeong.book.springboot.web;

import com.jhjeong.book.springboot.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//2차추가
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class) // 테스트를 진행할때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킨다. 여기서는 SpringRunner 라는 스프링 실행자를 사용, 즉 스프링부트 테스트와 JUnit 사이에 연결자 역할
//Web(Spring MVC)에 집중할수있는 어노테이션, 선연할 경우 @Controller, @ControllerAdvice 등을 사용, 단 @Service , @component, @Repository 사용불가, 여기서는 컨트롤러만 사용하기때문에 선언
@WebMvcTest(controllers = HelloController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
        }
)

public class HelloControllerTest {

    @Autowired // 스프링이 관리하는 빈(Bean)을 주입받음
    private MockMvc mvc; // 웹 API를 테스트할때 사용, 스프링 MVC 테스트의 시작점, 이 클래스를 통해 HTTP GET, POST 등에 대한 API 테스트 가능

    @WithMockUser(roles="USER")
    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";
        mvc.perform(get("/hello")) //MockMvc를 통해 /hello 주소로 HTTP GET 요청, 체이닝이 지원되어 여러 검증 기능 이어서 선언 가능
                .andExpect(status().isOk()) //mvc.perform의 결과를 검증, HTTP Header의 Status를 검증, 200,404,500등의 상태 검증, ok는 200 검증
                .andExpect(content().string(hello)); //mvc.perform의 결과를 검증, 응답 본문의 내용 검증, Controller에서 "hello"를 리턴하므로 이 값이 맞는지 검증
    }

    @WithMockUser(roles="USER")
    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                .param("name", name) //api를 테스트할때 사용될 요청 파라미터를 설정, 단 값은 String만 허용, 숫자날짜등의 데이터도 등록할때는 문자열로 변경
                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) //jsonpath: JSON 응답값을 필드별로 검증할수있는 메소드, $를 기준으로 필드명 명시, name과 amount를 검증하기위해 $.name, $.amount로 검증
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
