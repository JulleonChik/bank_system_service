package pro.julleon.bank_system.validation.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import pro.julleon.bank_system.repositories.JpaClientRepository;
import pro.julleon.bank_system.validation.annotations.ClientName;

public final class ClientUniqueNameValidator implements ConstraintValidator<ClientName, String> {

    private final JpaClientRepository jpaClientRepository;

    @Autowired
    public ClientUniqueNameValidator(JpaClientRepository jpaClientRepository) {
        this.jpaClientRepository = jpaClientRepository;
    }


    @Override
    public void initialize(ClientName constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !jpaClientRepository.existsByUsername(value);
    }
}
