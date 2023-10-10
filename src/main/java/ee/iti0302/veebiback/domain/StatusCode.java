package ee.iti0302.veebiback.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "status_code", schema = "public")
public class StatusCode {
    @Id
    @NotNull
    @Column(name = "code", insertable = false, updatable = false)
    private String code;

    @NotNull
    @Column(name = "code_class", insertable = false, updatable = false)
    private String codeClass;

    @NotNull
    @Column(name = "value", insertable = false, updatable = false)
    private String value;
}
