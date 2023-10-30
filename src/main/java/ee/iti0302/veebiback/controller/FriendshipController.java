package ee.iti0302.veebiback.controller;

import ee.iti0302.veebiback.dto.BaseDto;
import ee.iti0302.veebiback.dto.FriendRequestDto;
import ee.iti0302.veebiback.dto.FriendshipDto;
import ee.iti0302.veebiback.service.FriendshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/friend")
@RequiredArgsConstructor
public class FriendshipController {
    private final FriendshipService friendshipService;

    @GetMapping("/status")
    public FriendshipDto getFriendshipStatus(@RequestParam Long personId, @RequestParam Long friendId) {
        return friendshipService.getFriendshipStatus(personId, friendId);
    }

    @Transactional
    @PostMapping("/add")
    public BaseDto makeFriendRequest(@RequestBody FriendRequestDto request) {
        return friendshipService.addFriend(request);
    }

    @Transactional
    @DeleteMapping("/remove")
    public BaseDto cancelFriendRequest(@RequestParam Long personId, @RequestParam Long friendId) {
        return friendshipService.removeFriendship(personId, friendId);
    }
}
