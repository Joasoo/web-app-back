package ee.iti0302.veebiback.dto;

import lombok.Data;

@Data
public class FriendshipDto {
    private Long id;
    private PersonFullNameDto person;
    private PersonFullNameDto friend;
    private boolean confirmed;
}
