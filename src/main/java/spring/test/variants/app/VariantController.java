package spring.test.variants.app;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring.test.variants.domain.VariantService;
import spring.test.variants.domain.model.Variant;

@RestController
@RequestMapping("variants")
@RequiredArgsConstructor
class VariantController {

    private final VariantService service;

    @GetMapping("/{id}")
    Variant getVariant(@PathVariable int id) {
        return service.getVariant(id);
    }

    @PostMapping("/")
    Variant createVariant(Variant variant) {
        return service.createVariant(variant);
    }
}
