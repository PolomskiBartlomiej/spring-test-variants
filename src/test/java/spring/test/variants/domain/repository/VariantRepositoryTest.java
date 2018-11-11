package spring.test.variants.domain.repository;

import lombok.val;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import spring.test.variants.domain.model.Variant;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class VariantRepositoryTest {

    @Autowired
    private VariantRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    void variant_should_be_save_to_database() {
        //given
        val _name = "Variant";
        val variant = createVariant().name(_name).create();
        //when:
        repository.saveAndFlush(variant);
        //then
        assertEquals(
          repository.findByName(_name).orElseThrow(EntityNotFoundException::new), variant
        );
    }

    @ParameterizedTest
    @MethodSource("notValidVariants")
    void save_to_database_variant_without_name_throw_error(Variant notValidVariant) {
        //when:
        ThrowingCallable codeUnderTest = () -> {
            repository.save(notValidVariant);
            entityManager.flush();
        };

        //then:
        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(codeUnderTest);
    }

    private static Stream<Arguments> notValidVariants() {
        return Stream.of(
                Arguments.of(createVariant().name("").create()),
                Arguments.of(createVariant().name(" ").create()),
                Arguments.of(createVariant().name(null).create())
        );
    }

    private static Variant.VariantBuilder createVariant() {
        return Variant.builder();
    }

}