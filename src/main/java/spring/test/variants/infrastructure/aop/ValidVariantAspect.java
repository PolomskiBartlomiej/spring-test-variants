package spring.test.variants.infrastructure.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import spring.test.variants.domain.model.Variant;
import spring.test.variants.infrastructure.exception.IllegalVariantException;

@Aspect
@Component
public class ValidVariantAspect {

    @Pointcut("@annotation(spring.test.variants.infrastructure.aop.ValidVariant) && args (variant)")
    void validVariant(Variant variant){}

    @Around(value = "validVariant(variant)", argNames = "method,variant")
    Object validVariant(final ProceedingJoinPoint method, final Variant variant) throws Throwable {

        if(variant.getName().isEmpty()) {
            throw new IllegalVariantException();
        }

        return method.proceed(new Object[] {variant});
    }
}
