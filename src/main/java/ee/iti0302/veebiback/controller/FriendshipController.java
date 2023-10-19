package ee.iti0302.veebiback.controller;

import ee.iti0302.veebiback.dto.BaseDto;
import ee.iti0302.veebiback.dto.FriendRequestDto;
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
    public FriendshipDto getFriendshipStatus(@RequestBody FriendRequestDto dto) {
        return friendshipService.getFriendshipStatus(dto);
    }

    @Transactional
    @PostMapping(path="/request/make")
    public BaseDto makeFriendRequest(@RequestBody FriendRequestDto request) {
        return friendshipService.makeFriendRequest(request);
    }

    @Transactional
    @DeleteMapping(path="/request/cancel")
    public BaseDto cancelFriendRequest(@RequestBody FriendRequestDto request) {
        return friendshipService.cancelFriendRequest(request);
    }

    @Transactional
    @PostMapping(path="/request/accept")
    public BaseDto acceptFriendRequest(@RequestBody FriendRequestDto request) {
        return friendshipService.acceptFriendRequest(request);
    }
}
