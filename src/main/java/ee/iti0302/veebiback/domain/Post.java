package ee.iti0302.veebiback.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @JoinColumn(name="fk_person_id")
    @ManyToOne
    private Person person;

    @Column(name="created_at", insertable = false)
    private LocalDateTime createdAt;

    @NotNull
    @Column(name="content")
    private String content;
}
