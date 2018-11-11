package spring.test.variants.domain;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import spring.test.variants.domain.model.Variant;
import spring.test.variants.domain.repository.VariantRepository;
import spring.test.variants.infrastructure.aop.AopConfig;
import spring.test.variants.infrastructure.aop.ValidVariantAspect;
import spring.test.variants.infrastructure.exception.IllegalVariantException;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        VariantService.class,
        AopConfig.class,
        ValidVariantAspect.class
})

class VariantServiceTest {

    @Autowired VariantService classUnderTest;

    @MockBean VariantRepository repository;

    @Test
    void validation_aspect_thrown_exception_when_name_is_empty() {
        //when:
        final val notValidVariant = Variant.builder()
                .id(1)
                .name("")
                .create();

        // then :
        assertThatExceptionOfType(IllegalVariantException.class)
                .isThrownBy(() -> classUnderTest.createVariant(notValidVariant));
    }
}