package guru.springframework.apifirst.apifirstserver.domain;

import guru.springframework.apifirst.model.CategoryDto;
import guru.springframework.apifirst.model.DimensionsDto;
import guru.springframework.apifirst.model.ImageDto;
import jakarta.persistence.*;
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
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "char(36)", updatable = false, nullable = false)
    private UUID id;
    private String description;

    @Embedded
    private Dimension dimensions;

    //private List<Category> categories;

    @OneToMany(mappedBy = "product")
    private List<Image> images;
    private String price;
    private String cost;

    @CreationTimestamp
    private OffsetDateTime dateCreated;

    @UpdateTimestamp
    private OffsetDateTime dateUpdated;
}






