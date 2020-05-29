package com.jhjeong.book.springboot.service;

import com.jhjeong.book.springboot.domain.posts.Posts;
import com.jhjeong.book.springboot.domain.posts.PostsRepository;
import com.jhjeong.book.springboot.web.dto.PostsListResponseDto;
import com.jhjeong.book.springboot.web.dto.PostsResponseDto;
import com.jhjeong.book.springboot.web.dto.PostsSaveRequestDto;
import com.jhjeong.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor //final이 선언된 모든 필드를 인자값으로 하는 생성자를 롬복의 @RequiredArgsConstructor가 대신 생성해줌
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById (Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 사용자가 없습니다 id =" + id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true) //postsRepository 결과로 넘어온 Posts의 Stream을 map을 통해 PostsListResponseDto 변환 -> List로 반환
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete (Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        postsRepository.delete(posts); //JpaRepository에서 이미 delete 메소드를 지원하므로 활용, 파라미터로도 삭제가능하며 deleteById 메소드를 이용하면 id로도 삭제가능, 존재하는 Posts인지 확인을 위해 엔티티 조회 후 그대로 삭제
    }

}
