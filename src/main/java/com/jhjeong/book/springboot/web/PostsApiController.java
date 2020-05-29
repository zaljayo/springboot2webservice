package com.jhjeong.book.springboot.web;

import com.jhjeong.book.springboot.service.PostsService;
import com.jhjeong.book.springboot.web.dto.PostsResponseDto;
import com.jhjeong.book.springboot.web.dto.PostsSaveRequestDto;
import com.jhjeong.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts") //책에서는 @PutMapping되어있어서 에러났음, @PostMapping 으로 수정
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById (@PathVariable Long id){
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);;
        return id;
    }

}
