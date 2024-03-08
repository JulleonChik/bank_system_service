package pro.julleon.bank_system.validation.validators;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.Getter;
import pro.julleon.bank_system.repositories.JpaEmailRepository;
import pro.julleon.bank_system.repositories.JpaPhoneRepository;
import pro.julleon.bank_system.validation.annotations.UniqueCollection;

import java.util.List;

public class UniqueCollectionValidator implements ConstraintValidator<UniqueCollection, List<String>> {
    private final JpaEmailRepository jpaEmailRepository;
    private final JpaPhoneRepository jpaPhoneRepository;
    @Getter
    private String field;

    public UniqueCollectionValidator(JpaEmailRepository jpaEmailRepository, JpaPhoneRepository jpaPhoneRepository) {
        this.jpaEmailRepository = jpaEmailRepository;
        this.jpaPhoneRepository = jpaPhoneRepository;
    }

    @Override
    public void initialize(UniqueCollection constraintAnnotation) {
        this.field = constraintAnnotation.field();
    }

    @Override
    public boolean isValid(List<String> strings, ConstraintValidatorContext context) {
        switch (field) {
            case "phones":
                List<String> existingPhones = jpaPhoneRepository.findExistingNumbers(strings);
                if (!existingPhones.isEmpty()) {
                    UniqueCollectionValidationContext.setInvalidValues(field, existingPhones);
                    return false;
                }
                break;
            case "emails":
                List<String> existingEmails = jpaEmailRepository.findExistingEmails(strings);
                if (!existingEmails.isEmpty()) {
                    UniqueCollectionValidationContext.setInvalidValues(field, existingEmails);
                    return false;
                }
                break;
        }

        return true;
    }

}

//
//public class UniqueCollectionValidator implements ConstraintValidator<UniqueCollection, List<String>> {
//    private final JpaEmailRepository jpaEmailRepository;
//    private final JpaPhoneRepository jpaPhoneRepository;
//
//    private String field;
//
//    public UniqueCollectionValidator(JpaEmailRepository jpaEmailRepository, JpaPhoneRepository jpaPhoneRepository) {
//        this.jpaEmailRepository = jpaEmailRepository;
//        this.jpaPhoneRepository = jpaPhoneRepository;
//    }
//
//    @Override
//    public void initialize(UniqueCollection constraintAnnotation) {
//        this.field = constraintAnnotation.field();
//    }
//
//    @Override
//    public boolean isValid(List<String> strings, ConstraintValidatorContext context) {
//        switch (field) {
//            case "phones":
//                List<String> existingPhones = jpaPhoneRepository.findExistingNumbers(strings);
//                if (!existingPhones.isEmpty()) {
//                    existingPhones.forEach(phone -> {
//                        context
//                                .buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
//                                .addConstraintViolation();
//
//                    });
//                    return false;
//                }
//                break;
//            case "emails":
//                List<String> existingEmails = jpaEmailRepository.findExistingEmails(strings);
//                if (!existingEmails.isEmpty()) {
//                    existingEmails.forEach(email -> {
//                        context
//                                .buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
//                                .addConstraintViolation();
//                    });
//                    return false;
//                }
//                break;
//        }
//
//        return true;
//    }
//}
