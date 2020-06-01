package com.jhjeong.book.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) //이 어노테이션이 생성될수 있는 위치 지정, PARAMETER로 지정했으니 메소드으 ㅣ마라미터로 선언된 객체에서만 사용할수있음, 이외에도 클래스 선언문에 쓸수있는 TYPE등이있음
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser { // 이 파일을 어노테이션 클래스로 지정, LoginUser라는 이름을 가진 어노테이션이 생성되었다고 보면됨.
}
