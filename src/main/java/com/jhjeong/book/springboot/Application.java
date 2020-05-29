package com.jhjeong.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityListeners;

@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication //스프링부트의 자동 설정, 스프링 Bean 읽기와 생성, 프로젝트 최상단에 위치해야함
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args); //run으로 내장 WAS 실행됨
    }
}
