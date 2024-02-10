package guru.springframework.apifirst.apifirstserver.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Created by jt, Spring Framework Guru.
 */
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "char(36)", updatable = false, nullable = false)
    private UUID id;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private Address shipToAddress;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private Address billToAddress;

    @NotNull
    @Embedded
    private Name name;

    @Size(min=3,max=255)
    private String email;

    @Size(min=3,max=255)
    private String phone;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<PaymentMethod> paymentMethods;

    @PrePersist
    public void prePersist() {
        if (this.paymentMethods != null && !this.paymentMethods.isEmpty()) {
            this.paymentMethods.forEach(paymentMethod -> paymentMethod.setCustomer(this));
        }
    }

    @CreationTimestamp
    private OffsetDateTime dateCreated;

    @UpdateTimestamp
    private OffsetDateTime dateUpdated;
}












