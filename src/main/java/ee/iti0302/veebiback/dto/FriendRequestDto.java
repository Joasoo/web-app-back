package ee.iti0302.veebiback.dto;

import lombok.Data;

@Data
public class FriendRequestDto {
    private PersonFullNameDto person;
    private PersonFullNameDto friend;
}
