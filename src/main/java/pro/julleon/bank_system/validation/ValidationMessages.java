package pro.julleon.bank_system.validation;

public final class ValidationMessages {

    public static final String NOT_UNIQUE_USERNAME = "validation.newClientPayload.username.notUnique";
    public static final String NOT_UNIQUE_EMAIL = "validation.newClientPayload.email.notUnique";
    public static final String NOT_UNIQUE_PHONE = "validation.newClientPayload.phone.notUnique";
    public static final String INVALID_USERNAME = "validation.newClientPayload.username.notBlank";
    public static final String INVALID_PASSWORD = "validation.newClientPayload.password.notBlank";
    public static final String INVALID_PASSWORD_SIZE = "validation.newClientPayload.password.size";
    public static final String INVALID_INITIAL_BALANCE = "validation.newClientPayload.initialBalance.decimalMin";
    public static final String INVALID_FIRST_NAME = "validation.newClientPayload.firstName.notBlank";
    public static final String INVALID_MIDDLE_NAME = "validation.newClientPayload.middleName.notBlank";
    public static final String INVALID_SURNAME = "validation.newClientPayload.surname.notBlank";
    public static final String INVALID_BIRTH_DATE = "validation.newClientPayload.birthDate.past";
    public static final String INVALID_PHONES_EMPTY = "validation.newClientPayload.phones.notEmpty";
    public static final String INVALID_PHONE_PATTERN = "validation.newClientPayload.phones.pattern";
    public static final String INVALID_EMAILS_EMPTY = "validation.newClientPayload.emails.notEmpty";
    public static final String INVALID_EMAIL = "validation.newClientPayload.emails.email";

}
