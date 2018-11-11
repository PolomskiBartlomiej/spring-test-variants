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

    @GetMapping("/{name}")
    Variant getVariant(@PathVariable String name) {
        return service.getVariant(name);
    }

    @PostMapping("/")
    Variant createVariant(Variant variant) {
        return service.createVariant(variant);
    }
}
