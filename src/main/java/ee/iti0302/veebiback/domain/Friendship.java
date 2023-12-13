package ee.iti0302.veebiback.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "person_friend")
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "fk_person_id")
    private Person person;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "fk_friend_id")
    private Person friend;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "fk_friend_status")
    private StatusCode status;
}

