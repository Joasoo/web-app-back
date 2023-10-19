package ee.iti0302.veebiback.controller;

import ee.iti0302.veebiback.dto.BaseDto;
import ee.iti0302.veebiback.dto.FriendshipDto;
import ee.iti0302.veebiback.service.FriendshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/friend")
@RequiredArgsConstructor
public class FriendshipController {
    private final FriendshipService friendshipService;

    @GetMapping(path="/status")
    public FriendshipDto getFriendshipStatus(@RequestBody FriendshipDto dto) {
        return friendshipService.getFriendshipStatus(dto);
    }

    @PostMapping(path="/request/make")
    public BaseDto makeFriendRequest(@RequestBody FriendshipDto request) {
        return friendshipService.makeFriendRequest(request);
    }

    @PostMapping(path="/request/accept")
    @Transactional
    public BaseDto acceptFriendRequest(@RequestBody FriendshipDto request) {
        return friendshipService.acceptFriendRequest(request);
    }

    @DeleteMapping(path="/request/cancel")
    public BaseDto cancelFriendRequest(@RequestBody FriendshipDto request) {
        return friendshipService.cancelFriendRequest(request);
    }
}
