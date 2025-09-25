package upload.document.know_your_customer.utils;

import lombok.SneakyThrows;

import java.io.InputStream;
import java.security.MessageDigest;
import java.util.HexFormat;
import java.util.Set;

public class KycUtils {

    public static final Set<String> EXTENSIONS_AUTORISEES = Set.of(
            "application/pdf",
            "image/png",
            "image/jpeg"
    );

    @SneakyThrows
    public static String checkSumValue(InputStream input)  {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] buffer = new byte[8192];
        int bytesRead;
        while ((bytesRead = input.read(buffer)) != -1) {
            digest.update(buffer, 0, bytesRead);
        }
        return HexFormat.of().formatHex(digest.digest());
    }
}
