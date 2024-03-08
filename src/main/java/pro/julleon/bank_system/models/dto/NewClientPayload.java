package pro.julleon.bank_system.models.dto;

import org.springframework.validation.annotation.Validated;
import pro.julleon.bank_system.validation.annotations.ClientName;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Validated
public record NewClientPayload(
        @ClientName
        String username,

        @ClientPassword
        String password,

        @ClientInitialBalance
        BigDecimal initialBalance,

        @ClientFirstName
        String firstName,

        @ClientMiddleName
        String middleName,

        @ClientSurName
        String surname,

        @ClientBirthDate
        LocalDate birthDate,

        @ClientPhones
        List<String> phones,

        @ClientEmails
        List<String> emails
) {
}
