package guru.springframework.apifirst.apifirstserver.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * Created by jt, Spring Framework Guru.
 */
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Name {

    @Size(min=0,max=25)
    private String prefix;

    @NotNull
    @Size(min=2,max=100)
    private String firstName;

    @NotNull
    @Size(min=2,max=100)
    private String lastName;

    @Size(min=0,max=25)
    private String suffix;
}
