package spring.test.variants.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.test.variants.domain.model.Variant;
import spring.test.variants.domain.repository.VariantRepository;
import spring.test.variants.infrastructure.aop.ValidVariant;

import javax.persistence.NoResultException;

@Service
@RequiredArgsConstructor
public class VariantService {

    private final VariantRepository repository;

    public Variant getVariant(String name) {
        return repository.findByName(name).orElseThrow(NoResultException::new);
    }

    @ValidVariant
    public Variant createVariant(Variant variant) {
        return repository.saveAndFlush(variant);
    }
}
