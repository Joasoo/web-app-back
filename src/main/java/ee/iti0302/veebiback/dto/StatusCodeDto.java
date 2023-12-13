package ee.iti0302.veebiback.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StatusCodeDto {
    private String code;
    private String codeClass;
    private String value;
}
