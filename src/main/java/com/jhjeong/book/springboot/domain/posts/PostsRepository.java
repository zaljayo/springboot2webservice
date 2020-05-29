package com.jhjeong.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts,Long>{

    //SpringDataJpa에서 제공하지 않는 메소드는 이렇게 쿼리로 작성가능, 조회용 프레임워크는 Querydsl 추천, Mybatis, jooq는 별로
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
