package com.jhjeong.book.springboot.web;

import com.jhjeong.book.springboot.config.auth.LoginUser;
import com.jhjeong.book.springboot.config.auth.dto.SessionUser;
import com.jhjeong.book.springboot.service.PostsService;
import com.jhjeong.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/") //postsService.findAllDesc()로 가져온 결과를 posts로 index,mustache에 전달
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts", postsService.findAllDesc());

        //SessionUser user = (SessionUser) httpsession.getAttribute("user");

        if(user!=null){
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
