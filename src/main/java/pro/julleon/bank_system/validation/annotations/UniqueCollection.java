package pro.julleon.bank_system.validation.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import pro.julleon.bank_system.validation.validators.UniqueCollectionValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueCollectionValidator.class)
public @interface UniqueCollection {
    String message() default "Value must be unique";

    Class<?>[] groups() default {}; // Добавленный параметр groups
    Class<? extends Payload>[] payload() default {};
    String field();
}

