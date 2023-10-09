package ee.iti0302.veebiback.controller;

import ee.iti0302.veebiback.dto.AddPostDto;
import ee.iti0302.veebiback.dto.BaseDto;
import ee.iti0302.veebiback.dto.PostDto;
import ee.iti0302.veebiback.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService service;

    @GetMapping(path = "/person/{id}")
    public List<PostDto> getPostList(@PathVariable Long id) {
        return service.getPostList(id);
    }

    @PostMapping(path = "/add")
    public BaseDto addPost(@RequestBody AddPostDto postDto) {
        return service.addPost(postDto);
    }

    @DeleteMapping(path = "/delete/{id}")
    public BaseDto deletePost(@PathVariable Long id) {
        return service.deletePost(id);
    }
}
