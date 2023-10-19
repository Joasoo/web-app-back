package ee.iti0302.veebiback.dto;

import lombok.Data;

@Data
public class FriendRequestDto {
    private Long personId;
    private Long friendId;
}
