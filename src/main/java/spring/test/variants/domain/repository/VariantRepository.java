package spring.test.variants.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.test.variants.domain.model.Variant;

@Repository
public interface VariantRepository extends JpaRepository<Variant,Integer> {
}
