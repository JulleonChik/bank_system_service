package pro.julleon.bank_system.exp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pro.julleon.bank_system.validation.validators.UniqueCollectionValidationContext;

import java.util.*;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final MessageSource messageSource;

    @Autowired
    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(MethodArgumentNotValidException ex) {



        Map<String, List<String>> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.groupingBy(
                        FieldError::getField,
                        Collectors.mapping(
                                fieldError -> Objects.requireNonNull(fieldError.getDefaultMessage()),
                                Collectors.toList()
                        )
                ));

        List<FieldError> fieldErrors1 = ex.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors1) {
            Object[] arguments = fieldError.getArguments();
            System.out.println(Arrays.toString(arguments));
        }

        System.out.println(fieldErrors);
        List<ErrorResponse.FieldErrorDetails> errors = fieldErrors.entrySet().stream()
                .map(entry -> {
                    String fieldName = entry.getKey();
                    List<String> errorMessages = entry.getValue().stream().map(message -> messageSource.getMessage(
                            Objects.requireNonNull(message),
                            new Object[]{},
                            Locale.ENGLISH)).toList();
                    List<String> invalidValues = UniqueCollectionValidationContext.getInvalidValues(fieldName);

                    return new ErrorResponse.FieldErrorDetails(
                            fieldName,
                            invalidValues.isEmpty()
                                    ? Objects.requireNonNull(ex.getBindingResult().getFieldValue(fieldName)).toString()
                                    : invalidValues.toString(),
                            errorMessages);
                })
                .collect(Collectors.toList());


        UniqueCollectionValidationContext.clear();

        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), errors);
    }
}