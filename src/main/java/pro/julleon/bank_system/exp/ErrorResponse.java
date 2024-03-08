package pro.julleon.bank_system.exp;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ErrorResponse {
    private final int code;
    private final String status;
    private final List<FieldErrorDetails> errors;

    public ErrorResponse(int code, String status, List<FieldErrorDetails> errors) {
        this.code = code;
        this.status = status;
        this.errors = errors;
    }

    @Getter
    @Setter
    public static class FieldErrorDetails {
        private String field;
        private String data;
        private List<String> messages;

        public FieldErrorDetails(String field, String data, List<String> messages) {
            this.field = field;
            this.data = data;
            this.messages = messages;
        }
    }
}
