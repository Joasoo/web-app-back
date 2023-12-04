package ee.iti0302.veebiback.controller;

import ee.iti0302.veebiback.dto.BaseDto;
import ee.iti0302.veebiback.dto.FriendListDto;
import ee.iti0302.veebiback.dto.FriendRequestDto;
import ee.iti0302.veebiback.service.FriendshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Transactional
@RestController
@RequestMapping("api/friend")
@RequiredArgsConstructor
public class FriendshipController {
    private final FriendshipService friendshipService;

    @GetMapping()
    public FriendListDto getFriendshipStatus(@RequestParam Long personId, @RequestParam Long friendId) {
        return friendshipService.getFriendshipStatus(personId, friendId);
    }

    @PostMapping()
    public BaseDto addFriend(@RequestBody FriendRequestDto request) {
        return friendshipService.addFriend(request);
    }

    @DeleteMapping()
    public BaseDto removeFriend(@RequestParam Long personId, @RequestParam Long friendId) {
        return friendshipService.removeFriend(personId, friendId);
    }

    @GetMapping("/all/{id}")
    public List<FriendListDto> getFriendsList(@PathVariable Long id) {
        return friendshipService.getFriendsList(id);
    }
}
