package spring.test.variants.infrastructure.exception;

public class IllegalVariantException extends RuntimeException {

    public IllegalVariantException() {
        super("Variant is not valid : name cant be empty");
    }
}
