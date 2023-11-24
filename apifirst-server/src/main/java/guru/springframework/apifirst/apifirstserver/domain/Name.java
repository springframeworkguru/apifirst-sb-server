package guru.springframework.apifirst.apifirstserver.domain;

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

    private String prefix;
    private String firstName;
    private String lastName;
    private String suffix;
}
