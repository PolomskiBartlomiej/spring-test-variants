package spring.test.variants.domain;

import lombok.var;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import spring.test.variants.domain.model.Variant;
import spring.test.variants.domain.repository.VariantRepository;
import spring.test.variants.infrastructure.aop.AopConfig;
import spring.test.variants.infrastructure.aop.ValidVariantAspect;
import spring.test.variants.infrastructure.exception.IllegalVariantException;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
        VariantService.class,
        AopConfig.class,
        ValidVariantAspect.class
})

public class VariantServiceTest {

    @Autowired VariantService classUnderTest;

    @MockBean VariantRepository repository;

    @Test
    public void validation_aspect_thrown_exception_when_name_is_empty() {
        //when:
        var notValidVariant = new Variant(1, "");
        // then :.
        assertThatExceptionOfType(IllegalVariantException.class)
                .isThrownBy(() -> classUnderTest.createVariant(notValidVariant));
    }
}