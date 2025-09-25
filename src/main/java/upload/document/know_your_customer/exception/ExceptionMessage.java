package upload.document.know_your_customer.exception;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public interface ExceptionMessage {

    default String getDescription() {
        try {
            return this.getMessageBundle().getString(this.getResourceKey());
        } catch (MissingResourceException exc) {
            return null;
        }
    }
    default String getDescription(String... replacements) {
        return String.format(this.getDescription(), (Object[]) replacements);
    }
    String getResourceKey();

    String getCode();
    ResourceBundle getMessageBundle();
}
