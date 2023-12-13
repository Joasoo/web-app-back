package ee.iti0302.veebiback.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class PersonFullNameDto extends FullNameDto {
    private Long id; // Person's ID

    public PersonFullNameDto(String firstName, String lastName, Long id) {
        super(firstName, lastName);
        this.id = id;
    }
}
