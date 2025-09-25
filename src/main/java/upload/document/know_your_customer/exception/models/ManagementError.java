package upload.document.know_your_customer.exception.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class ManagementError implements Serializable {

    private String i18nKey;
    private String value;
    private Map<String, Objects> values = new HashMap<>();

    public ManagementError(String i18nKey, String sources) {this(i18nKey,sources, new HashMap<>());}

    public ManagementError(String i18nKey, String value, Map<String, Objects> values) {
        this.i18nKey = i18nKey;
        this.value = value;
        this.values = values;
    }

}
