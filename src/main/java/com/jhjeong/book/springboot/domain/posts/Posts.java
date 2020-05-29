package com.jhjeong.book.springboot.domain.posts;

import com.jhjeong.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter // 클래스 내 모든 필드의 Getter 메소드를 자동 생성
@NoArgsConstructor //기본 생성자 자동추가, Public Posts(){} 같은 효과
@Entity //테이블과 링크될 클래스임을 나타냄, 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍(_)으로 테이블 이름을 매칭함 ex) SalesManager.java -> sales_manager table
public class Posts extends BaseTimeEntity { //글 작성시 자동 날짜 생성을위해 BaseTimeEntity 확장

    @Id //해당 테이블의 PK 필드를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK의 생성 규칙을 나타냄, 스프링부트 2.0에서는 GenerationType.IDENTITY 옵션을 추가해야만 auto_increment됨
    private Long id;

    @Column(length = 500, nullable = false) //테이블의 칼럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이됨, 사용하는 이유는 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용 ex) 문자열은 VARCHAR(255)가 기본인데 500으로 늘리고 싶거나, 타입을 TEXT로 변경하고 싶거나 등
    private String title;

    @Column(columnDefinition ="TEXT", nullable = false)
    private String content;

    private String author;

    @Builder //해당 클래스의 빌더 패턴 클래스를 생성, 생성자 상단에 선언 시 생성자에 포함된 빌드만 빌더에 포함
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    //책에서 해당 메소드 누락되어 추가해줌
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
