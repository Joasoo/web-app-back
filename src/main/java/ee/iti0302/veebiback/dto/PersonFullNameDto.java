package ee.iti0302.veebiback.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PersonFullNameDto extends FullNameDto {
    private Long id; // Person's ID
}
