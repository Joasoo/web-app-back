package ee.iti0302.veebiback.dto;

import lombok.Data;

/*todo add the person's avatar.*/
@Data
public class FriendListDto {
    private Long id;
    private FullNameDto name;
    private StatusCodeDto status;
}
