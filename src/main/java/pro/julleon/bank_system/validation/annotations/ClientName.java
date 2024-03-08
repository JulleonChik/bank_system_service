package pro.julleon.bank_system.validation.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotEmpty;
import pro.julleon.bank_system.validation.ValidationMessages;
import pro.julleon.bank_system.validation.validators.ClientUniqueNameValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {
        ClientUniqueNameValidator.class,
})
@NotEmpty(message = ValidationMessages.INVALID_USERNAME)
public @interface ClientName {
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}