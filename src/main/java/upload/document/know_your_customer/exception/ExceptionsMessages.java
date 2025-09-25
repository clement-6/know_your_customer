package upload.document.know_your_customer.exception;


import java.util.ResourceBundle;

public enum ExceptionsMessages implements ExceptionMessage {

    M_INVALID_DATA("exception.invalid.data", "400-1"),
    M_RESOURCE_NOT_FOUND("exception.resource.notFound", "404-1"),
    M500_1("exception.internal", "500-1");

    private static final ResourceBundle messsage = ResourceBundle.getBundle("message") ;

    private final String description;

    private final String code;

    ExceptionsMessages(String description, String code) {
        this.description = description;
        this.code = "MANAGEMENT" + "-" + code;
    }

    @Override
    public String getDescription() {
        return ExceptionMessage.super.getDescription();
    }

    @Override
    public String getResourceKey() {
        return this.description;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public ResourceBundle getMessageBundle() {
        return ExceptionsMessages.messsage;
    }
}
