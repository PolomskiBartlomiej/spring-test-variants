package spring.test.variants.domain.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Builder(buildMethodName = "create")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "variants")
public class Variant {
    @Id
    @GeneratedValue
    Integer id;

    @Column(name = "name")
    @Getter
    @NotBlank String name;
}
