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
    private final PostService postService;

    @PostMapping()
    public BaseDto addPost(@RequestBody AddPostDto postDto) {
        return postService.addPost(postDto);
    }

    @DeleteMapping("/{id}")
    public BaseDto deletePost(@PathVariable Long id) {
        return postService.deletePost(id);
    }

    @GetMapping("/person/{id}")
    public List<PostDto> getPostList(@PathVariable Long id) {
        return postService.getPostList(id);
    }

    @GetMapping("/feed/{id}")
    public List<PostDto> getFeed(@PathVariable Long id, @RequestParam Integer pageNumber, @RequestParam Integer limit) {
        return postService.getFeed(id, pageNumber, limit);
    }
}
