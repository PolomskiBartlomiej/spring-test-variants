package spring.test.variants.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor

@Entity
@Table(name = "variants")
public class Variant {
    @Id
    @GeneratedValue
    int id;

    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name = "name")
    String name;

    private Variant() {
        //hibernate
    }
}
