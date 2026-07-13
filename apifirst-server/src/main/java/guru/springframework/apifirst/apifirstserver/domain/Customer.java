package guru.springframework.apifirst.apifirstserver.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "char(36)", nullable = false, updatable = false)
    private UUID id;


    @Embedded
    private Name name;

    @OneToOne
    private Address shipToAddress;

    @OneToOne
    private Address billToAddress;


    private String email;
    private String phone;

    @OneToMany(mappedBy = "customer")
    private List<PaymentMethod> paymentMethods;

    @CreationTimestamp
    private OffsetDateTime dateCreated;
    @UpdateTimestamp
    private OffsetDateTime dateUpdated;

}
