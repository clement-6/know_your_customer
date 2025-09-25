package upload.document.know_your_customer.exception;

import lombok.Getter;

@Getter
public class ManagementRuntimeException extends RuntimeException{

    private final String code;

    private final String[] replacements;

    public ManagementRuntimeException(ExceptionMessage message) {
        super(message.getDescription());
        this.code = message.getCode();
        this.replacements = new String[]{};
    }

    public ManagementRuntimeException(ExceptionMessage message, String... replacements) {
        super(message.getDescription(replacements));
        this.code = message.getCode();
        this.replacements = replacements;
    }

    public ManagementRuntimeException(ExceptionMessage message, Throwable throwable) {
        super(message.getDescription(), throwable);
        this.code = message.getCode();
        this.replacements = new String[]{};
    }
}
