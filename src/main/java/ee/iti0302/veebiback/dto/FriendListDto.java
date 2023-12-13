package ee.iti0302.veebiback.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*todo add the person's avatar.*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FriendListDto {
    private Long id;
    private FullNameDto name;
    private StatusCodeDto status;
}
