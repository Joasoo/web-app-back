package ee.iti0302.veebiback.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FullNameDto {
    private String firstName;
    private String lastName;
}
