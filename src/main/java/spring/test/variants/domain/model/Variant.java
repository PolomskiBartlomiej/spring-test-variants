package spring.test.variants.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
@AllArgsConstructor
public class Variant {
    Variant() {
        //hibernate
    }

    @Id
    @GeneratedValue
    int id;

    String name;
}
