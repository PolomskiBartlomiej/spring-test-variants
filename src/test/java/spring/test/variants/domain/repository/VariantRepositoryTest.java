package spring.test.variants.domain.repository;

import lombok.var;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import spring.test.variants.domain.model.Variant;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VariantRepositoryTest {

    @Autowired
    private VariantRepository repository;

    @PersistenceContext
    private EntityManager entityManager;


    @Test
    public void variant_should_be_save_to_database() {
        //given:
        var _id = 2;
        var _name = "Variant 2";

        var variant = Variant.builder()
                .id(_id)
                .name(_name)
                .build();

        repository.save(variant);

        assertEquals(repository.getOne(_id), variant);
    }

    @Test(expected = ConstraintViolationException.class)
    public void save_to_database_variant_without_name_throw_error() {
        var _id = 1;
        var _name = "";
        //given:
        var variant = Variant.builder()
                .id(_id)
                .name(_name)
                .build();
        //then:
        repository.save(variant);
        entityManager.flush();
    }

}